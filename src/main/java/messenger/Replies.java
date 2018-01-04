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
}
