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
			if (msg.equalsIgnoreCase("load")) {
				replies.load_number(null);
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
				replies.trade_history_symbol();
			}else if(msg.equalsIgnoreCase("buy")) {
				replies.buy_market_order_pair();
			}else if(msg.equalsIgnoreCase("sell")) {
				replies.sell_market_order_pair();
			}
		}else if (type.equalsIgnoreCase("cryptocompare")) {
			
			if(msg.equalsIgnoreCase("select_a_cryptocurrency")) {
				replies.main_menu_replies();
			}else if(msg.equalsIgnoreCase("notifications_one_time")) {
				replies.notification_type("one_time");
			}else if(msg.equalsIgnoreCase("notifications_non_stop")) {
				replies.notification_type("non_stop");
			}
			else if(msg.equalsIgnoreCase("seven_eleven")) {
				replies.seven_eleven_enter();
			}
		}else if (type.equalsIgnoreCase("coinsph_php_to_btc") && get_state().get("messenger_state").getAsString().equalsIgnoreCase("php_btc") ) {
			if (msg.equalsIgnoreCase("100")) {
				replies.php_to_btc_send(1);
			}else if (msg.equalsIgnoreCase("75")) {
				replies.php_to_btc_send(0.75);
			}else if (msg.equalsIgnoreCase("50")) {
				replies.php_to_btc_send(0.50);
			}else if (msg.equalsIgnoreCase("25")) {
				replies.php_to_btc_send(0.25);
			}
		}else if (type.equalsIgnoreCase("coinsph_btc_to_php") && get_state().get("messenger_state").getAsString().equalsIgnoreCase("btc_php")) {
			if (msg.equalsIgnoreCase("100")) {
				replies.btc_to_php_send(1);
			}else if (msg.equalsIgnoreCase("75")) {
				replies.btc_to_php_send(0.75);
			}else if (msg.equalsIgnoreCase("50")) {
				replies.btc_to_php_send(0.50);
			}else if (msg.equalsIgnoreCase("25")) {
				replies.btc_to_php_send(0.25);
			}
		}else if (type.equalsIgnoreCase("binance_buy")) {
			String symbol = get_state().get("messenger_market_order_pair").getAsString();
			replies.buy_market_order_proceed(symbol, Double.parseDouble(msg));
		}else if (type.equalsIgnoreCase("binance_sell")) {
			String symbol = get_state().get("messenger_market_order_pair").getAsString();
			replies.sell_market_order_proceed(symbol, Double.parseDouble(msg));
		}
		else {
			MessengerSend messenger_send = new MessengerSend();
			JsonObject reply = new JsonObject();
			reply.addProperty("text", "Oops! Sorry previous transactions are expired");
			messenger_send.callSendAPI(this.sender_psid, reply);
		}
		
	}
	private void handle_quick_reply(JsonElement quick_reply) throws Exception {
		Replies replies = new Replies(this.sender_psid);
		JsonObject obj = (JsonObject) quick_reply;
		String s = obj.get("payload").getAsString();
		String payload_str = obj.toString();
		JsonObject payload = fix_payload(s);
		String type = payload.get("type").getAsString();
		
		if ( payload.get("msg")==null) {
			return;
		}
		String msg = payload.get("msg").getAsString();
		String state = get_state().get("messenger_state").getAsString();
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
		}else if (type.equalsIgnoreCase("coinsph_load")) {
			String number = this.get_state().get("messenger_to_load_number").getAsString();
			
			replies.load_proceed(number, msg);  
			
		}else if (type.equalsIgnoreCase("notifications_type") && state.equalsIgnoreCase("notification_type")) {
			
			replies.notification_trade_pair(msg);
			
		}
	}

	void handleMessage(JsonObject received_message) throws Exception {
		JsonObject reply = new JsonObject();

		if (received_message.get("text") != null) {
			if (received_message.get("quick_reply") != null) {
				handle_quick_reply(received_message.get("quick_reply"));
			}else {
				Replies replies = new Replies(this.sender_psid);
				String text = received_message.get("text").getAsString();
				if (text.toLowerCase().equals("menu")) {
					
					replies.main_menu_replies();
				}else {
					String state = this.state.get("messenger_state").getAsString();
					MessengerSend messenger_send = new MessengerSend();
					
					if ( state.equalsIgnoreCase("load_number"))
						replies.load_number(text);
					else if ( state.equalsIgnoreCase("seven_eleven_enter"))
						replies.seven_eleven_amount(text);
					else if (text.length() ==14)
						replies.seven_eleven_barcode(text);
					else if (state.equalsIgnoreCase("notification_trade_pair"))
						replies.notification_target_value( text);
					else if (state.equalsIgnoreCase("buy_market_order_pair"))
						replies.buy_market_order_amount(text);
					else if (state.equalsIgnoreCase("sell_market_order_pair"))
						replies.sell_market_order_amount(text);
					else if (state.equalsIgnoreCase("trade_history_symbol"))
						replies.trade_history_proceed(text);
					else if (state.equalsIgnoreCase("notification_target_value")) {
						String notification_type = get_state().get("messenger_notification_type").getAsString();
						String notification_interval = get_state().get("messenger_notification_interval").getAsString();
						String notification_base_value = get_state().get("messenger_notification_base_value").getAsString();
						String notification_symbol = get_state().get("messenger_notification_symbol").getAsString();
						replies.notification_proceed(notification_type, notification_interval,  notification_symbol, notification_base_value, text);
						
					}else
					{
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
		ProcessBuilder processBuilder = new ProcessBuilder();
		String url;
		if (processBuilder.environment().get("PORT") != null) {
			url = Settings.MESSENGER_STATE_DB_HEROKU + this.sender_psid;
		}else {
			url = Settings.MESSENGER_STATE_DB + this.sender_psid;
		}
		
		state= Utils.raw_get_request(url).getAsJsonObject();
		return state;
		
	}
	
	// fix java shitty json parser return a payload object
		private JsonObject fix_payload(String payload_str) {
			String str = payload_str;
		    String _str_ = str.replaceAll("'", "\"");
		    JsonObject _payload_obj = (JsonObject) new JsonParser().parse(_str_);
		    return _payload_obj;
		}
}
