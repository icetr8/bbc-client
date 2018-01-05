package messenger;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class Replies {
	private String sender_psid;

	Replies(String psid){
		this.sender_psid = psid;
	}
	public void main_menu_replies() throws Exception {
		PredefinedResponse predefined = new PredefinedResponse();
		JsonElement reply = predefined.MAIN_MENU;
		MessengerSend messenger_send = new MessengerSend();
		messenger_send.callSendAPI(this.sender_psid, reply);
		
	}
	public void coinsph_replies() throws Exception {
		PredefinedResponse predefined = new PredefinedResponse();
		JsonElement reply = predefined.COINS_PH;
		MessengerSend messenger_send = new MessengerSend();
		messenger_send.callSendAPI(this.sender_psid, reply);
	}
	public void binance_replies() throws Exception {
		PredefinedResponse predefined = new PredefinedResponse();
		JsonElement reply = predefined.BINANCE;
		MessengerSend messenger_send = new MessengerSend();
		messenger_send.callSendAPI(this.sender_psid, reply);
	}
	public void get_started(JsonElement payload) throws Exception {
		main_menu_replies();
		
	}
	public void php_to_btc() throws Exception {
		PredefinedResponse predefined = new PredefinedResponse();
		
		// add values base on balance
		String s1 = predefined.PHP_TO_BTC.replaceAll("%100", "100");
		String s2 = s1.replaceAll("%75", "75");
		String s3 = s2.replaceAll("%50", "50");
		String str = s3.replaceAll("%25", "25");
		JsonElement reply = new JsonParser().parse(str);

		MessengerSend messenger_send = new MessengerSend();
		messenger_send.callSendAPI(this.sender_psid, reply);
		
	}
	public void btc_to_php() throws Exception {
		PredefinedResponse predefined = new PredefinedResponse();
		
		// add values base on balance
		String s1 = predefined.btc_to_php.replaceAll("%100", "100");
		String s2 = s1.replaceAll("%75", "75");
		String s3 = s2.replaceAll("%50", "50");
		String s4 = s3.replaceAll("%25", "25");
		String s5 = s4.replaceAll("%php", "10M");
		String s6 = s5.replaceAll("%btc", "25btc");
		JsonElement reply = new JsonParser().parse(s6);

		MessengerSend messenger_send = new MessengerSend();
		messenger_send.callSendAPI(this.sender_psid, reply);
		
	}
	public void load() throws Exception {
		PredefinedResponse predefined = new PredefinedResponse();
		String load_str = predefined.load;
		// add values base on balance
		String load = load_str.replaceAll("%balance", "10Milliion");
		JsonElement reply = new JsonParser().parse(load);
		MessengerSend messenger_send = new MessengerSend();
		messenger_send.callSendAPI(this.sender_psid, reply);
	}
	public void coinsph_balance() throws Exception {
		PredefinedResponse predefined = new PredefinedResponse();
		
		// add values base on balance
		String s1 = predefined.coinsph_balance.replaceAll("%balance", "100MIllion");
		String balance = s1.replaceAll("%bitcoin", "75btc");

		JsonElement reply = new JsonParser().parse(balance);
		MessengerSend messenger_send = new MessengerSend();
		messenger_send.callSendAPI(this.sender_psid, reply);
		
	}
	public void transfer_to_binance() throws Exception {
		PredefinedResponse predefined = new PredefinedResponse();
		
		// add values base on balance
		String s1 = predefined.btc_to_php.replaceAll("%100", "100");
		String s2 = s1.replaceAll("%75", "75");
		String s3 = s2.replaceAll("%50", "50");
		String s4 = s3.replaceAll("%25", "25");
		String s5 = s4.replaceAll("%php", "10M");
		String s6 = s5.replaceAll("%btc", "25btc");
		JsonElement reply = new JsonParser().parse(s6);

		MessengerSend messenger_send = new MessengerSend();
		messenger_send.callSendAPI(this.sender_psid, reply);
		
	}
	public void view_funds() throws Exception {
		PredefinedResponse predefined = new PredefinedResponse();
		
		// add values base on balance
		String s1 = predefined.VIEW_FUNDS.replaceAll("%bitcoin", "100");
		String funds= s1.replaceAll("%usd", "75trump");
		JsonElement reply_1 = new JsonParser().parse(funds);
		MessengerSend messenger_send = new MessengerSend();
		messenger_send.callSendAPI(this.sender_psid, reply_1);
		
		
		String assets = predefined.VIEW_ASSETS.replaceAll("%assets", "This is your assets \n 100iot \n 40trx");
		JsonElement reply_2 = new JsonParser().parse(assets);
		MessengerSend messenger_send_2 = new MessengerSend();
		messenger_send_2.callSendAPI(this.sender_psid, reply_2);
	}
	
}
