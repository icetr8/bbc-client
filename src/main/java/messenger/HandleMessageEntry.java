package messenger;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import configuration.Settings;
import utils.Utils;

public class HandleMessageEntry {
	private String sender_psid;
	private JsonObject state;
	HandleMessageEntry(String psid) throws Exception {
		this.sender_psid = psid;
		state = get_state();
	}
	// Handles messaging_postbacks events
	void handlePostback( JsonObject received_postback) throws Exception {
		JsonElement postback = received_postback.get("payload");
		Replies replies = new Replies(this.sender_psid);
		String s = postback.getAsString();
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
			System.out.println(msg);
			if (msg.equalsIgnoreCase("load")) {
				replies.load_number();
			}else if(msg.equalsIgnoreCase("balance")) {
				replies.coinsph_balance();
			}else if(msg.equalsIgnoreCase("php_to_btc")) {
				replies.php_to_btc();
			}else if(msg.equalsIgnoreCase("btc_to_php")) {
				replies.btc_to_php();
			}else if(msg.equalsIgnoreCase("transfer_to_binance")) {
				replies.transfer_to_binance();
			}else {
				System.out.println("Unrecognized msg"+msg+" with type of "+type);
			}
		}else if (type.equalsIgnoreCase("binance")) {
			if (msg.equalsIgnoreCase("view_funds")) {
				replies.view_funds();
			}else if(msg.equalsIgnoreCase("trade_with_a_cryptocurrency")) {
				replies.main_menu_replies();
			}else if(msg.equalsIgnoreCase("trade_history")) {
				replies.main_menu_replies();
			}else if(msg.equalsIgnoreCase("buy")) {
				replies.main_menu_replies();
			}else if(msg.equalsIgnoreCase("sell")) {
				replies.main_menu_replies();
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
			if(msg.equalsIgnoreCase("select_a_cryptocurrency")) {
				replies.main_menu_replies();
			}else if(msg.equalsIgnoreCase("notifications")) {
				replies.main_menu_replies();
			}
		}else if (type.equalsIgnoreCase("coinsph_php_to_btc")) {
			if (msg.equalsIgnoreCase("100")) {
				replies.php_to_btc_send(100);
			}else if (msg.equalsIgnoreCase("75")) {
				replies.php_to_btc_send(75);
			}else if (msg.equalsIgnoreCase("50")) {
				replies.php_to_btc_send(50);
			}else if (msg.equalsIgnoreCase("25")) {
				replies.php_to_btc_send(25);
			}
		}else if (type.equalsIgnoreCase("coinsph_btc_to_php")) {
			if (msg.equalsIgnoreCase("100")) {
				replies.btc_to_php_send(100);
			}else if (msg.equalsIgnoreCase("75")) {
				replies.btc_to_php_send(75);
			}else if (msg.equalsIgnoreCase("50")) {
				replies.btc_to_php_send(50);
			}else if (msg.equalsIgnoreCase("25")) {
				replies.btc_to_php_send(25);
			}
		}
		
	}
	private void handle_quick_reply(JsonElement quick_reply) throws Exception {
		Replies replies = new Replies(this.sender_psid);
		//System.out.println(quick_reply.toString());
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

	void handleMessage(JsonObject received_message) throws Exception {
		JsonObject reply = new JsonObject();
		
		if (received_message.get("text") != null) {
			if (received_message.get("quick_reply") != null) {
				handle_quick_reply(received_message.get("quick_reply"));
			}else {
				Replies replies = new Replies(this.sender_psid);
				if (received_message.get("text").getAsString().toLowerCase().equals("menu")) {
					
					replies.main_menu_replies();
				}else {
					String state = this.state.get("messenger_state").getAsString();
					MessengerSend messenger_send = new MessengerSend();
					
					if ( state.equalsIgnoreCase("load_number")) {
						replies.load_amount();
					}else {
					reply.addProperty("text",
							"You sent the message: " + received_message.get("text") + ". Now send me an image!");				
					messenger_send.callSendAPI(sender_psid, reply);
					}
					
					
				}
			}
		}
	}

	
	private JsonObject get_state() throws Exception {
		JsonObject state = new JsonObject();
		String url = Settings.MESSENGER_STATE_DB + this.sender_psid;
		state= Utils.raw_get_request(url).getAsJsonObject();
		return state;
		
	}
	
	// fix java shitty json parser return a payload object
		private JsonObject fix_payload(String payload_str) {
			String str = payload_str;
		    String _str_ = str.replaceAll("'", "\"");
		    //System.out.println(_str_);
		    JsonObject _payload_obj = (JsonObject) new JsonParser().parse(_str_);
			//JsonObject payload_obj = (JsonObject) _payload_obj.get("payload");
		    return _payload_obj;
		}
}
