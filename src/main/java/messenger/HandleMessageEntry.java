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
	void handlePostback( JsonObject received_postback) throws Exception {
		JsonElement payload = received_postback.get("payload");
		Replies replies = new Replies(this.sender_psid);
		switch (payload.getAsString()) {
        case "GET_STARTED": replies.get_started(payload); 
        	break;
        case "MAIN_MENU": replies.main_menu_replies();
            break;
        case "LOAD": replies.main_menu_replies();
        	break;
        case "BALANCE": replies.main_menu_replies();
    		break;
        case "PHP_TO_BTC": replies.main_menu_replies();
    		break;
        case "BTC_TO_PHP": replies.main_menu_replies();
    		break;
        case "VIEW_FUNDS": replies.main_menu_replies();
    		break;
        case "TRADE_WITH_A_CRYPTOCURRENCY": replies.main_menu_replies();
    		break;
        case "TRADE_HISTORY": replies.main_menu_replies();
    		break;
        case "SELECT A CRYPTOCURRENCY": replies.main_menu_replies();
    		break;
        case "NOTIFICATIONS": replies.main_menu_replies();
    		break;
        default: System.out.println("Unrecognized Postback Payload" + payload);;
        	break;
		}
	}
	private void handle_quick_reply(JsonElement quick_reply) throws Exception {
		Replies replies = new Replies(this.sender_psid);
		JsonObject payload = fix_payload(quick_reply);
		String type = payload.get("type").getAsString();
		String msg = payload.get("msg").getAsString();
		System.out.println(type);
		if (type.equalsIgnoreCase("main_menu")) {
			switch(msg) {
			case "coins.ph": replies.coinsph_replies();  
				break;
			case "binance": replies.binance_replies();  
				break;
			case "cryptocompare": replies.coinsph_replies();
				break;
			case "about": replies.coinsph_replies();
				break;
			default : System.out.println("Unrecognized msg"+msg+" with type of "+"type");;
			}
		}else if (type.equalsIgnoreCase("coinsph")) {
			switch(msg) {
			case "watch_price": replies.coinsph_replies();  
				break;
			case "load": replies.coinsph_replies();
				break;
			case "balance": replies.coinsph_replies();
				break;
			case "php_to_btc": replies.coinsph_replies();
				break;
			case "btc_to_php": replies.coinsph_replies();
				break;
			case "transfer": replies.coinsph_replies();
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

	void handleMessage(JsonObject received_message) throws Exception {
		JsonObject reply = new JsonObject();
		if (received_message.get("text") != null) {
			System.out.println(received_message.toString());
			if (received_message.get("quick_reply") != null) {
				handle_quick_reply(received_message.get("quick_reply"));
			}else {
				if (received_message.get("text").getAsString().toLowerCase().equals("menu")) {
					Replies replies = new Replies(this.sender_psid);
					replies.main_menu_replies();
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
}
