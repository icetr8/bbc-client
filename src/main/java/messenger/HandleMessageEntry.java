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
        case "MAIN_MENU": main_menu(payload);
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
			reply.addProperty("text",
					"You sent the message: " + received_message.get("text") + ". Now send me an image!");
			MessengerSend messenger_send = new MessengerSend();
			
			messenger_send.callSendAPI(sender_psid, reply);
			}
		} else if (received_message.get("attachments") != null) {

			// Gets the URL of the message attachment
			String attachment_url = "";
		}
	}

	private void route_quick_reply(JsonElement quick_reply) throws Exception {
		PredefinedResponse predefined = new PredefinedResponse();
		JsonElement reply = predefined.COINS_PH;
		MessengerSend messenger_send = new MessengerSend();
		messenger_send.callSendAPI(this.sender_psid, reply);
	}
	private void main_menu(JsonElement payload) throws Exception {
		PredefinedResponse predefined = new PredefinedResponse();
		JsonElement reply = predefined.MAIN_MENU;
		MessengerSend messenger_send = new MessengerSend();
		messenger_send.callSendAPI(this.sender_psid, reply);
		
	}

	private  void get_started(JsonElement payload) throws Exception {
		main_menu(payload);
		
	}
}
