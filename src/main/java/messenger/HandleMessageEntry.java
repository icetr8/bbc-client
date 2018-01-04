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
		JsonElement postback = received_postback.get("payload");
		Replies replies = new Replies(this.sender_psid);
		String s = postback.getAsString();
		System.out.println(received_postback.toString());
		JsonObject payload = fix_payload(s);
		String type = payload.get("type").getAsString();
		if ( payload.get("msg")==null) {
			return;
		}
		String msg = payload.get("msg").getAsString();
		
		if (type.equalsIgnoreCase("main_menu")) {
			switch(msg) {
			 case "get_started": replies.get_started(payload); 
	        	break;
			default : System.out.println("Unrecognized msg"+msg+" with type of "+"type");;
			}
		}else if (type.equalsIgnoreCase("coinsph")) {
			switch(msg) {
			case "load": replies.main_menu_replies();
        		break;
	        case "balance": replies.main_menu_replies();
	    		break;
	        case "php_to_btc": replies.php_to_btc();
	    		break;
	        case "btc_to_php": replies.main_menu_replies();
	    		break;
			default : System.out.println("Unrecognized msg"+msg+" with type of "+type);
			}
		}else if (type.equalsIgnoreCase("binance")) {
			switch (msg) {
	        
	        case "view_funds": replies.main_menu_replies();
	    		break;
	        case "trade_with_a_cryptocurrency": replies.main_menu_replies();
	    		break;
	        case "trade_history": replies.main_menu_replies();
	    		break;
	        default: System.out.println("Unrecognized msg"+msg+" with type of "+type);;
	        	break;
			}
		}else if (type.equalsIgnoreCase("cryptocompare")) {
			switch (msg) {
	        case "select_a_cryptocurrency": replies.main_menu_replies();
	    		break;
	        case "notifications": replies.main_menu_replies();
	    		break;
	        default: System.out.println("Unrecognized msg"+msg+" with type of "+type);;
	        	break;
			}
		}
	}
	private void handle_quick_reply(JsonElement quick_reply) throws Exception {
		Replies replies = new Replies(this.sender_psid);
		System.out.println(quick_reply.toString());
		JsonObject obj = (JsonObject) quick_reply;
		String s = obj.get("payload").getAsString();
		String payload_str = obj.toString();
		System.out.println(s);
		JsonObject payload = fix_payload(s);
		String type = payload.get("type").getAsString();
		if ( payload.get("msg")==null) {
			return;
		}
		String msg = payload.get("msg").getAsString();
		
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
			default : System.out.println("Unrecognized msg"+msg+" with type of "+type);;
			}
		}else if (type.equalsIgnoreCase("coinsph")) {
			switch(msg) {
			case "watch_price": replies.coinsph_replies();  
				break;
			case "load": replies.coinsph_replies();
				break;
			case "balance": replies.coinsph_replies();
				break;
			case "php_to_btc": replies.php_to_btc();
				break;
			case "btc_to_php": replies.coinsph_replies();
				break;
			case "transfer": replies.coinsph_replies();
				break;
			default : System.out.println("Unrecognized msg"+msg+" with type of "+type);;
			}
		}
	}

	// fix java shitty json parser return a payload object
	private JsonObject fix_payload(String payload_str) {
		String str = payload_str;
	    String _str_ = str.replaceAll("'", "\"");
	    System.out.println(_str_);
	    JsonObject _payload_obj = (JsonObject) new JsonParser().parse(_str_);
		//JsonObject payload_obj = (JsonObject) _payload_obj.get("payload");
	    return _payload_obj;
	}

	void handleMessage(JsonObject received_message) throws Exception {
		JsonObject reply = new JsonObject();
		if (received_message.get("text") != null) {
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
