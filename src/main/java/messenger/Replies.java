package messenger;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

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
	public void buy_market_order_symbol() {
		// save symbol
	}
	public void buy_market_order(String Symbol, String amount) {
		// binance.buy
	}
	
	public void sell_market_order_symbol() {
		// save symbol
	}
	
	public void sell_market_order(String Symbol, String amount) {
		// binance.sell
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
		String the_string = load.replaceAll("%number", number);
		JsonElement reply = new JsonParser().parse(the_string);
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
		JsonObject data = new JsonObject();
		data.addProperty("state", state.get("state").getAsString());
		if (state.get("load_number")!= null ) {
		data.addProperty("load_number", state.get("load_number").getAsString());
		}
		Utils.raw_post_request(Settings.MESSENGER_STATE_DB+this.sender_psid, data.toString());
	}
}
