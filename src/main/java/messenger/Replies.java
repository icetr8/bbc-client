package messenger;

import com.google.gson.JsonElement;

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
}
