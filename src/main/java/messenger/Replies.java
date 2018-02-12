package messenger;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.xml.ws.Response;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import Cryptocompare.cryptocompare;
import binance.Binance;
import coinsph.Coinsph;
import configuration.Settings;
import utils.Utils;

public class Replies {
	private String sender_psid;
	PredefinedResponse predefined;
	Settings settings;
	Binance binance;
	MessengerSend messenger_send;
	Coinsph coinsph;
	Replies(String psid) throws Exception{
		this.sender_psid = psid;
		this.messenger_send = new MessengerSend();
		this.predefined = new PredefinedResponse();
		this.binance = new Binance();
		this.coinsph = new Coinsph();
		
	}
	public void request_symbol() throws Exception {
		JsonObject reply = new JsonObject();
		reply.addProperty("text", "Enter a symbol for a cryptocurrency");
		this.messenger_send.callSendAPI(this.sender_psid, reply);
		
		JsonObject state = new JsonObject();
		state.addProperty("state", "request_symbol");
		update_state(state);
	}
	public void symbol_info(String symbol) throws Exception {
		JsonObject resp = cryptocompare.ticker(symbol);
		JsonObject reply = new JsonObject();
		if(resp.get("error") != null) {
			reply.addProperty("text", resp.get("error").getAsString());
			this.messenger_send.callSendAPI(this.sender_psid, reply);
			return;
		}
		String message = "";
		message+="Name: "+resp.get("name").getAsString() + "\n";
		message+="rank: "+resp.get("rank").getAsString() + "\n";
		message+="price_usd: "+resp.get("price_usd").getAsString() + "\n";
		message+="24h_volume_usd: "+resp.get("24h_volume_usd").getAsString() + "\n";
		message+="market_cap_usd: "+resp.get("market_cap_usd").getAsString() + "\n";
		message+="available_supply: "+resp.get("available_supply").getAsString() + "\n";
		message+="total_supply: "+resp.get("total_supply").getAsString() + "\n";
		message+="max_supply: "+resp.get("max_supply").getAsString() + "\n";
		message+="percent_change_1h: "+resp.get("percent_change_1h").getAsString() + "\n";
		message+="percent_change_24h: "+resp.get("percent_change_24h").getAsString() + "\n";
		reply.addProperty("text", message);
		this.messenger_send.callSendAPI(this.sender_psid, reply);
		
	}
	public void sell_market_order_proceed(String symbol, double percent) throws Exception {
		JsonObject assetBalance = binance.get_asset_balance(symbol);
		
		// quantity lot size #.## or #.### or #.#### or #
		DecimalFormat df = new DecimalFormat(assetBalance.get("decimal_format").getAsString());
		df.setRoundingMode(RoundingMode.FLOOR);
		double balance = Double.parseDouble(assetBalance.get("amount").getAsString());
		String quantity = df.format(balance * (percent / 100.0));
		
		JsonObject response = binance.market_order(symbol, quantity, "sell");
		JsonObject reply = new JsonObject();
		if (response.get("error") != null) {
			String error = response.get("error").getAsString();
			if (percent == 100 && error.equalsIgnoreCase("Filter failure: LOT_SIZE")){
				reply.addProperty("text", "You're total asset is not enough to sell. Please choose another trade pair");
				this.messenger_send.callSendAPI(this.sender_psid, reply);
				sell_market_order_pair();
				return;
			} else if (error.equalsIgnoreCase("Filter failure: LOT_SIZE")) {
				reply.addProperty("text", "You're selected percentage is not enough to sell. Please choose a higher percentage");
				this.messenger_send.callSendAPI(this.sender_psid, reply);
				sell_market_order_amount(symbol);
				return;
			}
		}
		String infos = "\n\n" + response.get("date").getAsString() +"\n" + symbol.toUpperCase();
		reply.addProperty("text", "Sell Success!!!" + infos);
		this.messenger_send.callSendAPI(this.sender_psid, reply);
		
		JsonObject state = new JsonObject();
		state.addProperty("state", "sell_market_order_proceed");
		update_state(state);
	}
	public void buy_market_order_proceed(String symbol, double percent) throws Exception {
		JsonObject assetBaseBalance = binance.get_asset_base_balance(symbol);
		JsonObject assetBalance = binance.get_asset_balance(symbol);
		DecimalFormat df = new DecimalFormat(assetBalance.get("decimal_format").getAsString());
		df.setRoundingMode(RoundingMode.FLOOR);
		
		double converted_amount = Double.parseDouble(assetBaseBalance.get("converted_amount").getAsString());
		String quantity = df.format(converted_amount * (percent/100.0));

		JsonObject response = binance.market_order(symbol, quantity, "buy");
		JsonObject reply = new JsonObject();
		if (response.get("error") != null) {
			String error = response.get("error").getAsString();
			if (percent == 100 && error.equalsIgnoreCase("Filter failure: LOT_SIZE")){
				reply.addProperty("text", "You're total asset is not enough to buy. Please choose another trade pair");
				this.messenger_send.callSendAPI(this.sender_psid, reply);
				sell_market_order_pair();
			} else if (error.equalsIgnoreCase("Filter failure: LOT_SIZE")) {
				reply.addProperty("text", "You're selected percentage is not enough to buy. Please choose a higher percentage");
				this.messenger_send.callSendAPI(this.sender_psid, reply);
				sell_market_order_amount(symbol);
				return;
			}
		}
		String infos = "\n\n" + response.get("date").getAsString() +"\n" + symbol.toUpperCase();
		reply.addProperty("text", "Buy Success!!!" + infos);
		this.messenger_send.callSendAPI(this.sender_psid, reply);
		
		JsonObject state = new JsonObject();
		state.addProperty("state", "buy_market_order_proceed");
		update_state(state);
	}
	public void buy_market_order_amount(String symbol) throws Exception {
		JsonElement reply = PredefinedResponse.BUY_MARKET_ORDER_AMOUNT;
		JsonObject reply1 = new JsonObject();
		
		JsonObject assetBalance = binance.get_asset_base_balance(symbol);
		double balance = assetBalance.get("amount").getAsDouble();
		String asset = assetBalance.get("asset").getAsString();
		if (balance <= 0) {
			reply1.addProperty("text", asset + " balance is zero");
			return;
		}
		String percentages = String.format("\n75%% %f, 50%% %f, 25%% %f", balance * (0.75), balance * (0.50), balance * (0.25));
		reply1.addProperty("text", "You have "+assetBalance.get("amount").getAsString()+assetBalance.get("asset").getAsString()+" Select a percentage above." + percentages );
		this.messenger_send.callSendAPI(this.sender_psid, reply);
		this.messenger_send.callSendAPI(this.sender_psid, reply1);
		

		JsonObject state = new JsonObject();
		state.addProperty("state", "buy_market_order_amount");
		state.addProperty("market_order_pair", symbol);
		update_state(state);

		
		
	}
	public void buy_market_order_pair() throws Exception {
		JsonObject reply = new JsonObject();
		reply.addProperty("text", "Enter a VALID Trade Pair. You may view supported trade pair in this link http://coinsbot-client.herokuapp.com/trade_pairs");
		this.messenger_send.callSendAPI(this.sender_psid, reply);
		
		JsonObject state = new JsonObject();
		state.addProperty("state", "buy_market_order_pair");
		update_state(state);
	}
	
	public void sell_market_order_amount(String symbol) throws Exception {
		JsonElement reply = PredefinedResponse.SELL_MARKET_ORDER_AMOUNT;
		JsonObject reply1 = new JsonObject();
		
		JsonObject assetBalance = binance.get_asset_balance(symbol);
		double balance = assetBalance.get("amount").getAsDouble();
		String asset = assetBalance.get("asset").getAsString();
		if (balance <= 0) {
			reply1.addProperty("text", asset + " balance is zero");
			return;
		}
		
		String percentages = String.format("\n75%% %f, 50%% %f, 25%% %f", balance * (0.75), balance * (0.50), balance * (0.25));
		reply1.addProperty("text", "You have "+assetBalance.get("amount").getAsString()+assetBalance.get("asset").getAsString()+" Select a percentage above." + percentages );
		this.messenger_send.callSendAPI(this.sender_psid, reply);
		this.messenger_send.callSendAPI(this.sender_psid, reply1);
		
		JsonObject state = new JsonObject();
		state.addProperty("state", "sell_market_order_amount");
		state.addProperty("market_order_pair", symbol);
		update_state(state);
		
	}
	public void sell_market_order_pair() throws Exception {
		JsonObject reply = new JsonObject();
		reply.addProperty("text", "Enter a VALID Trade Pair. You may view supported trade pair in this link http://coinsbot-client.herokuapp.com/trade_pairs");
		this.messenger_send.callSendAPI(this.sender_psid, reply);
		
		JsonObject state = new JsonObject();
		state.addProperty("state", "sell_market_order_pair");
		update_state(state);
	}
	public void notification_proceed(String notification_type, String notification_interval,String notification_symbol, String notification_base_value, String notification_target_value) throws Exception {
		double base_value = Double.parseDouble(notification_base_value);
		double target_value = Double.parseDouble(notification_target_value);
		double percentage = (100 - ((base_value / target_value) * 100.00)) / 100;
		boolean greater = false;
		if(percentage > 0) {
			greater = true;
		}
		
		binance.market_data_stream(this.sender_psid, notification_symbol.toLowerCase(), base_value, target_value, percentage, greater);
		
		JsonObject reply = new JsonObject();
		String message = "";
		if (greater == true) 
			message = String.format("I will notify you when %s price hits greater than %s", notification_symbol, target_value);
		else
			message = String.format("I will notify you when %s price hits lower than %s", notification_symbol, target_value);
		reply.addProperty("text", message);
		this.messenger_send.callSendAPI(this.sender_psid, reply);
		reply.addProperty("text", "Meanwhile you can user other chat functions");
		this.messenger_send.callSendAPI(this.sender_psid, reply);
	}
	public void notification_target_value ( String symbol) throws Exception{
		JsonObject response = binance.symbol_statistics(symbol.toUpperCase());
		JsonObject reply = new JsonObject();
		if (response.get("error")!=null) {
			reply.addProperty("text", response.get("error").getAsString());
			messenger_send.callSendAPI(this.sender_psid, reply);
			reply.addProperty("text", "Enter a VALID Trade Pair. You may view supported trade pair in this link http://coinsbot-client.herokuapp.com/trade_pairs");
			messenger_send.callSendAPI(this.sender_psid, reply);
			return;
		}
		String last_price = response.get("last_price").getAsString();
		reply.addProperty("text", "Entering a value that is LOWER means notifying you when the price hits LOWER than the entered value \n\n"+
				"Entering a value that is HIGHER means notifying you when the price hits HIGHER than the entered value");
		messenger_send.callSendAPI(this.sender_psid, reply);
		String message = String.format(("The last price is %s, enter a price that is higher or lower"), last_price);
		reply.addProperty("text", message);
		messenger_send.callSendAPI(this.sender_psid, reply);
		
		JsonObject state = new JsonObject();
		state.addProperty("state", "notification_target_value");
		state.addProperty("notification_symbol", symbol);
		state.addProperty("notification_base_value", last_price);
		update_state(state);
	}
	public void notification_trade_pair(String interval) throws Exception {
		JsonObject state = new JsonObject();
		state.addProperty("state", "notification_trade_pair");
		state.addProperty("notification_interval", interval);
		update_state(state);
		
		JsonObject reply = new JsonObject();
		reply.addProperty("text", "Enter a Trade Pair. You may view supported trade pair in this link http://coinsbot-client.herokuapp.com/trade_pairs");
		messenger_send.callSendAPI(this.sender_psid, reply);
	}
	public void notification_type(String notification_type) throws Exception {
		JsonElement reply = PredefinedResponse.NOTIFICATION_TYPE;
		messenger_send.callSendAPI(this.sender_psid, reply);
		JsonObject state = new JsonObject();
		state.addProperty("state", "notification_type");
		state.addProperty("notification_type", notification_type);
		update_state(state);
	}
	public void trade_history_proceed(String symbol) throws Exception {
		JsonObject response = this.binance.trade_history_str(symbol.toUpperCase());
		JsonObject reply = new JsonObject();
		if (response.get("error")!=null) {
			reply.addProperty("text", response.get("error").getAsString());
		}else
			reply.addProperty("text", response.get("message").getAsString());
		messenger_send.callSendAPI(this.sender_psid, reply);
	}
	public void trade_history_symbol() throws Exception {
		JsonElement reply = this.predefined.PAIRS;
		//JsonObject reply = new JsonObject();
		//reply.addProperty("text", "Enter a Trade Pair. You may view supported trade pair in this link http://coinsbot-client.herokuapp.com/trade_pairs");
		messenger_send.callSendAPI(this.sender_psid, reply);
		
		JsonObject state = new JsonObject();
		state.addProperty("state", "trade_history_symbol");
		update_state(state);
	}
	public void seven_eleven_enter() throws Exception {
		JsonObject reply = new JsonObject();
		reply.addProperty("text", "Enter your amount in PHP");
		messenger_send.callSendAPI(this.sender_psid, reply);
		
		JsonObject state = new JsonObject();
		state.addProperty("state", "seven_eleven_enter");
		update_state(state);
	}
	
	public void seven_eleven_amount(String amount) throws Exception {
		JsonObject response = coinsph.seven_eleven(amount);
		JsonObject reply = new JsonObject();
		if (response.get("error")!=null) {
			reply.addProperty("text", response.get("error").getAsString());
			
		}else {
			reply.addProperty("text", "Success! You will receive the ref no. at your email/phone number \n"
					+ "Copy then paste in this chat the ref no. to recieve your barcode");
		}
		messenger_send.callSendAPI(this.sender_psid, reply);
		JsonObject state = new JsonObject();
		state.addProperty("state", "seven_eleven_amount");
		update_state(state);
	}
	
	public void seven_eleven_barcode(String ref) throws Exception {
		String reply_str = predefined.barcode.replaceAll("%ref", ref);
		JsonObject other_reply = new JsonObject();
		other_reply.addProperty("text", "Here is your Barcode. Present this to a local 7-11 counter. \n"
				+ "Please be advised that the barcode is only valid after 1 hour after message/email sent");
		messenger_send.callSendAPI(this.sender_psid, other_reply);
		JsonElement reply = new JsonParser().parse(reply_str);
		messenger_send.callSendAPI(this.sender_psid, reply);
		JsonObject state = new JsonObject();
		state.addProperty("state", "seven_eleven_barcode");
		update_state(state);
	}
	
	public void php_to_btc_send(double percent) throws Exception {
		JsonObject account = this.coinsph.check_balance();
		Double source_amount = percent * account.get("peso_balance").getAsDouble();
		JsonObject data = this.coinsph.php_to_btc(source_amount);
		
		JsonObject reply = new JsonObject();
		if ( data.get("error")!=null) {
			reply.addProperty("text", "ERROR! " + data.get("error").getAsString());
		}

		reply.addProperty("text", "HOORAY! you've send "+data.get("source_amount").getAsString() +
				" php or "+ data.get("target_amount").getAsString()+ " btc at a buy rate of " 
				+ data.get("rate").getAsString() + " php");
		messenger_send.callSendAPI(this.sender_psid, reply);
		
		// if error show error_messag
		if (data.get("error")!=null) {
			php_to_btc();
		}else {
		JsonObject state = new JsonObject();
		state.addProperty("state", "php_to_btc_success");
		update_state(state);
		}
	}
	
	public void php_to_btc() throws Exception {
		MessengerSend messenger_send = new MessengerSend();
		JsonObject data = this.coinsph.check_balance();
		double bal = data.get("peso_balance").getAsDouble();
		
		NumberFormat formatter = new DecimalFormat("#0.00");  
		String balance = Double.toString(bal);
		String seventy5 = formatter.format(bal * 0.75);
		String fifty = formatter.format(bal * 0.5);
		String twenty5 = formatter.format(bal * 0.25);
		
		JsonObject reply = new JsonObject();
		reply.addProperty("text", "100% = " + balance + "\n" +
				"75% = " + seventy5 + "\n" + 
				"50%% = " + fifty + "\n" + 
				"25% = " + twenty5 + "\n");
		messenger_send.callSendAPI(this.sender_psid, reply);
		
		String json = this.predefined.PHP_TO_BTC;
		JsonElement reply1 = new JsonParser().parse(json);
		messenger_send.callSendAPI(this.sender_psid, reply1);
		
		JsonObject state = new JsonObject();
		state.addProperty("state", "php_btc");
		update_state(state);
		
	}
	
	public void btc_to_php_send(double percent) throws Exception {
		JsonObject account = this.coinsph.check_balance();
		Double source_amount = percent * account.get("btc_balance").getAsDouble();
		JsonObject data = this.coinsph.btc_to_php(source_amount);
		
		JsonObject reply = new JsonObject();
		if ( data.get("error")!=null) {
			reply.addProperty("text", "ERROR! " + data.get("error").getAsString());
		}
		reply.addProperty("text", "HOORAY! you've send "+data.get("source_amount").getAsString() +
				" btc or "+ data.get("target_amount").getAsString()+ " php at a sell rate of " 
				+ data.get("rate").getAsString() + " php");
		messenger_send.callSendAPI(this.sender_psid, reply);
				
		// if error show error_messag
		if (data.get("error")!=null) {
			btc_to_php();
		}else {
		JsonObject state = new JsonObject();
		state.addProperty("state", "btc_to_php_success");
		update_state(state);
		}
	}
	
	public void btc_to_php() throws Exception {
		MessengerSend messenger_send = new MessengerSend();
		JsonObject data = this.coinsph.check_balance();
		double bal = data.get("btc_balance").getAsDouble();
		
		NumberFormat formatter = new DecimalFormat("#0.00000000");  
		String balance = formatter.format(bal);
		String seventy5 = formatter.format(bal * 0.75);
		String fifty = formatter.format(bal * 0.5);
		String twenty5 = formatter.format(bal * 0.25);
		
		JsonObject reply = new JsonObject();
		reply.addProperty("text", "100% = " + balance + "\n" +
				"75% = " + seventy5 + "\n" + 
				"50%% = " + fifty + "\n" + 
				"25% = " + twenty5 + "\n");
		messenger_send.callSendAPI(this.sender_psid, reply);
		
		String json = this.predefined.btc_to_php;
		JsonElement reply1 = new JsonParser().parse(json);
		messenger_send.callSendAPI(this.sender_psid, reply1);
		
		JsonObject state = new JsonObject();
		state.addProperty("state", "btc_php");
		update_state(state);
		
	}
	
	public void load_number(String number) throws Exception {
		JsonObject state = new JsonObject();
		if(number == null) {
			JsonObject reply = new JsonObject();
			reply.addProperty("text", "Please enter the 11 digit number starting with +63");
			this.messenger_send.callSendAPI(this.sender_psid, reply);
			state.addProperty("state", "load_number");
			update_state(state);
		}else {
			
			state.addProperty("state", "load_number");
			state.addProperty("load_number", number);
			update_state(state);
			
			load_amount(number);
			
			
		}
	}
	
	public void load_amount(String number) throws Exception {
		
		String load_str = this.predefined.load;
		// add values base on balance
		String balance = this.coinsph.check_balance().get("peso_balance").getAsString();
		String load = load_str.replaceAll("%balance", balance);
		//String the_string = load.replaceAll("%number", number);
		JsonElement reply = new JsonParser().parse(load);
		MessengerSend messenger_send = new MessengerSend();
		messenger_send.callSendAPI(this.sender_psid, reply);
		
		JsonObject state = new JsonObject();
		state.addProperty("state", "load_amount");
		update_state(state);
	}
	
	public void load_proceed(String number, String amount) throws Exception {
		JsonObject response = this.coinsph.load(number, amount);
		JsonObject reply = new JsonObject();
		if(response.get("error")!= null) {
			reply.addProperty("text", "ERROR! " + response.get("error").getAsString());
		}else {
			reply.addProperty("text", "Success I've recieved your payment. Your load will be credited to " 
					+number +" within 10 minutes. Your rebate will arrrive in your Coins wallet");
		}
		messenger_send.callSendAPI(this.sender_psid, reply);
		JsonObject state = new JsonObject();
		state.addProperty("state", "load_success");
		update_state(state);
	}
	
	public void coinsph_balance() throws Exception {
		Coinsph coinsph = new Coinsph();
		
		JsonObject data = coinsph.check_balance();
		// add values base on balance
		
		String s1 = this.predefined.coinsph_balance.replaceAll("%balance", data.get("peso_balance").getAsString());
		String balance = s1.replaceAll("%bitcoin", data.get("btc_balance").getAsString());

		JsonElement reply = new JsonParser().parse(balance);
		MessengerSend messenger_send = new MessengerSend();
		messenger_send.callSendAPI(this.sender_psid, reply);
		
		JsonObject state = new JsonObject();
		state.addProperty("state", "coinsph_balance");
		update_state(state);
	}
	
	public void transfer_to_binance() throws Exception {
		// add values base on balance
		String str = this.predefined.btc_to_php;
		JsonElement reply = new JsonParser().parse(str);

		MessengerSend messenger_send = new MessengerSend();
		messenger_send.callSendAPI(this.sender_psid, reply);
		
		JsonObject state = new JsonObject();
		state.addProperty("state", "transfer_amount");
		update_state(state);
		
	}
	
	public void transfer_address() throws Exception {
		JsonObject state = new JsonObject();
		state.addProperty("state", "transfer_address");
		update_state(state);
	}
	
	public void transfer_proceed() throws Exception {
		JsonObject state = new JsonObject();
		state.addProperty("state", "transfer_proceed");
		update_state(state);
	}
	
	public void view_funds() throws Exception {
		// add values base on balance
		JsonObject data = binance.view_funds();
		String s1 = this.predefined.VIEW_FUNDS.replaceAll("%bitcoin", data.get("btc_value").getAsString());
		String funds= s1.replaceAll("%usd", data.get("usdt_value").getAsString());
		JsonElement reply_1 = new JsonParser().parse(funds);
		MessengerSend messenger_send = new MessengerSend();
		messenger_send.callSendAPI(this.sender_psid, reply_1);
		
		
		String assets = this.predefined.VIEW_ASSETS.replaceAll("%assets", data.get("assets").getAsString());
		JsonElement reply_2 = new JsonParser().parse(assets);
		MessengerSend messenger_send_2 = new MessengerSend();
		messenger_send_2.callSendAPI(this.sender_psid, reply_2);
		
		JsonObject state = new JsonObject();
		state.addProperty("state", "funds");
		update_state(state);
	}
	
	public void main_menu_replies() throws Exception {
		
		JsonElement reply = this.predefined.MAIN_MENU;
		MessengerSend messenger_send = new MessengerSend();
		messenger_send.callSendAPI(this.sender_psid, reply);
		
		JsonObject state = new JsonObject();
		state.addProperty("state", "main_menu_replies");
		update_state(state);
		
	}

	public void coinsph_replies() throws Exception {
		
		JsonElement reply = this.predefined.COINS_PH;
		MessengerSend messenger_send = new MessengerSend();
		messenger_send.callSendAPI(this.sender_psid, reply);
		
		JsonObject state = new JsonObject();
		state.addProperty("state", "coinsph_replies");
		update_state(state);
	}
	
	public void binance_replies() throws Exception {
		
		JsonElement reply = this.predefined.BINANCE;
		MessengerSend messenger_send = new MessengerSend();
		messenger_send.callSendAPI(this.sender_psid, reply);
		
		JsonObject state = new JsonObject();
		state.addProperty("state", "binance_replies");
		update_state(state);
	}
	
	public void get_started(JsonElement payload) throws Exception {
		main_menu_replies();
		
		JsonObject state = new JsonObject();
		state.addProperty("state", "main_menu_replies");
		update_state(state);
		
	}
	
	private void update_state(JsonObject state) throws Exception {
		//JsonObject data = new JsonObject();
		//data.addProperty("state", state.get("state").getAsString());
		//if (state.get("load_number")!= null ) {
		//data.addProperty("load_number", state.get("load_number").getAsString());
		//}
		ProcessBuilder processBuilder = new ProcessBuilder();
		if (processBuilder.environment().get("PORT") != null) {
			Utils.raw_post_request(Settings.MESSENGER_STATE_DB_HEROKU+this.sender_psid, state.toString());
		}else {
			Utils.raw_post_request(Settings.MESSENGER_STATE_DB+this.sender_psid, state.toString());
		}
		
	}
	
}
