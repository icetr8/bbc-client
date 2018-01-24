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
	
	public void php_to_btc_send(int percent) throws Exception {
		// check balance
		
		// send request
		
		// if error != null then execute
		
		// if error show error_message
		
		JsonObject state = new JsonObject();
		state.addProperty("state", "php_to_btc_send");
		update_state(state);
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
		
		String json = this.predefined.btc_to_php;
		JsonElement reply1 = new JsonParser().parse(json);
		messenger_send.callSendAPI(this.sender_psid, reply1);
		
		JsonObject state = new JsonObject();
		state.addProperty("state", "php_btc");
		update_state(state);
		
	}
	
	public void btc_to_php_send(int percent) throws Exception {
		// check balance
		
		// send request
				
		// if error != null then execute
				
		// if error show error_messag
		
		JsonObject state = new JsonObject();
		state.addProperty("state", "btc_to_php_send");
		update_state(state);
	}
	
	public void btc_to_php() throws Exception {
		MessengerSend messenger_send = new MessengerSend();
		JsonObject data = this.coinsph.check_balance();
		double bal = data.get("btc_balance").getAsDouble();
		
		NumberFormat formatter = new DecimalFormat("#0.00000000");  
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
		
		String json = this.predefined.btc_to_php;
		JsonElement reply1 = new JsonParser().parse(json);
		messenger_send.callSendAPI(this.sender_psid, reply1);
		
		JsonObject state = new JsonObject();
		state.addProperty("state", "btc_php");
		update_state(state);
		
	}
	
	public void load_number() throws Exception {
		
		JsonObject reply = new JsonObject();
		reply.addProperty("text", "Please enter the 11 digit number starting with +63");
		this.messenger_send.callSendAPI(this.sender_psid, reply);
		
		JsonObject state = new JsonObject();
		state.addProperty("state", "load_number");
		update_state(state);
	}
	
	public void load_amount() throws Exception {
		
		String load_str = this.predefined.load;
		// add values base on balance
		String load = load_str.replaceAll("%balance", "10Milliion");
		JsonElement reply = new JsonParser().parse(load);
		MessengerSend messenger_send = new MessengerSend();
		messenger_send.callSendAPI(this.sender_psid, reply);
		
		JsonObject state = new JsonObject();
		state.addProperty("state", "load_amount");
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
		String s1 = this.predefined.btc_to_php.replaceAll("%100", "100");
		String s2 = s1.replaceAll("%75", "75");
		String s3 = s2.replaceAll("%50", "50");
		String s4 = s3.replaceAll("%25", "25");
		String s5 = s4.replaceAll("%php", "10M");
		String s6 = s5.replaceAll("%btc", "25btc");
		JsonElement reply = new JsonParser().parse(s6);

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
	
	private void update_state(JsonObject state) throws Exception {
		JsonObject data = new JsonObject();
		data.addProperty("state", state.get("state").getAsString());
		if (state.get("load_number")!= null ) {
		data.addProperty("load_number", state.get("load_number").getAsString());
		}
		Utils.raw_post_request(Settings.MESSENGER_STATE_DB+this.sender_psid, data.toString());
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
}
