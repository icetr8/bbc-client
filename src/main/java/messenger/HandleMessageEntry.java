package messenger;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class HandleMessageEntry {
	private String sender_psid;

	HandleMessageEntry(String psid){
		this.sender_psid = psid;
	}
	// Handles messaging_postbacks events
	void handlePostback(String sender_psid, JsonObject received_postback) throws Exception {
		JsonElement payload = received_postback.get("payload");
		switch (payload.getAsString()) {
        case "GET_STARTED": get_started(payload); 
        	break;
        case "MAIN_MENU": main_menu_replies();
            break;
        default: System.out.println("Unrecognized Payload" + payload);;
        	break;
		}
	}

	void handleMessage(String sender_psid, JsonObject received_message) throws Exception {
		JsonObject reply = new JsonObject();
		if (received_message.get("text") != null) {
			if (received_message.get("quick_reply") != null) {
				route_quick_reply(received_message.get("quick_reply"));
			}else {
				if (received_message.get("text").getAsString().toLowerCase().equals("menu")) {
					main_menu_replies();
				}else {
			reply.addProperty("text",
					"You sent the message: " + received_message.get("text") + ". Now send me an image!");
			MessengerSend messenger_send = new MessengerSend();
			
			messenger_send.callSendAPI(sender_psid, reply);
				}
			}
		} else if (received_message.get("attachments") != null) {

			// Gets the URL of the message attachment
			String attachment_url = "";
		}
	}

	private void route_quick_reply(JsonElement quick_reply) throws Exception {
		PredefinedResponse predefined = new PredefinedResponse();
		JsonObject payload = fix_payload(quick_reply);
		String type = payload.get("type").getAsString();
		String msg = payload.get("msg").getAsString();
		System.out.println(type);
		if (type.equalsIgnoreCase("main_menu")) {
			switch(msg) {
			case "coins.ph": coinsph_replies();  
				break;
			case "binance": binance_replies();  
				break;
			case "cryptocompare": coinsph_replies();
				break;
			case "about": coinsph_replies();
				break;
			default : System.out.println("Unrecognized msg"+msg+" with type of "+"type");;
			}
		}else if (type.equalsIgnoreCase("coinsph")) {
			switch(msg) {
			case "watch_price": coinsph_replies();  
				break;
			case "load": coinsph_replies();
				break;
			case "balance": coinsph_replies();
				break;
			case "php_to_btc": coinsph_replies();
				break;
			case "btc_to_php": coinsph_replies();
				break;
			case "transfer": coinsph_replies();
				break;
			default : System.out.println("Unrecognized msg"+msg+" with type of "+"type");;
			}
		}
	}

	// fix java shitty json parser return a payload object
	private JsonObject fix_payload(JsonElement element) {
		JsonObject obj = (JsonObject) element;
		String str = obj.toString();
	    if (str != null && str.length() > 0) {
	        str = str.substring(0, str.length() - 2) + "}";
	    }
	    String str_= new StringBuilder(str).deleteCharAt(11).toString();

	    String _str_ = str_.replaceAll("'", "\"");
	    JsonObject _payload_obj = (JsonObject) new JsonParser().parse(_str_);
		JsonObject payload_obj = (JsonObject) _payload_obj.get("payload");
	    return payload_obj;
	}
	private void main_menu_replies() throws Exception {
		PredefinedResponse predefined = new PredefinedResponse();
		JsonElement reply = predefined.MAIN_MENU;
		MessengerSend messenger_send = new MessengerSend();
		messenger_send.callSendAPI(this.sender_psid, reply);
		
	}
	private void coinsph_replies() throws Exception {
		PredefinedResponse predefined = new PredefinedResponse();
		JsonElement reply = predefined.COINS_PH;
		MessengerSend messenger_send = new MessengerSend();
		messenger_send.callSendAPI(this.sender_psid, reply);
	}
	private void binance_replies() throws Exception {
		PredefinedResponse predefined = new PredefinedResponse();
		JsonElement reply = predefined.BINANCE;
		MessengerSend messenger_send = new MessengerSend();
		messenger_send.callSendAPI(this.sender_psid, reply);
	}
	private  void get_started(JsonElement payload) throws Exception {
		main_menu_replies();
		
	}
}
