package messenger;

import static spark.Spark.halt;

import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import configuration.Settings;
import spark.Request;
import spark.Response;

public class Messenger {
	private static final String something = null;
	
	public static String event_reciever(Request request, Response response) throws Exception {
		String body = request.body();
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map = mapper.readValue(body, Map.class);
		String page = (String) map.get("object");
		if (page.equalsIgnoreCase("page")) {
			JsonObject messagingObject = getMessageObject(request.body());
			String sender_psid = getPSID(messagingObject);
			HandleMessageEntry handle_message_entry = new HandleMessageEntry(sender_psid);
			if (messagingObject.get("postback") != null) {
				
				JsonObject messageObject = messagingObject.getAsJsonObject("postback");
				handle_message_entry.handlePostback(messageObject);

			} else if (messagingObject.get("message") != null) {
				JsonObject messageObject = messagingObject.getAsJsonObject("message");
				handle_message_entry.handleMessage( messageObject);
			}

		} else
			halt(404);
		return "NOTHING";
		
	}
	private static JsonObject getMessageObject(String body) {
		JsonElement jelement = new JsonParser().parse(body);
		JsonObject entryobject = jelement.getAsJsonObject();
		JsonArray entryarray = entryobject.getAsJsonArray("entry");
		JsonObject messagingobject = entryarray.get(0).getAsJsonObject();
		JsonArray messagingarray = messagingobject.getAsJsonArray("messaging");
		JsonObject messageObject = messagingarray.get(0).getAsJsonObject();

		return messageObject;
	}

	private static String getPSID(JsonObject messagingobject) {
		JsonObject message = messagingobject.getAsJsonObject("sender");
		String psid = message.get("id").getAsString();
		return psid;
	}

	// Sends response messages via the Send API
	public static String verify_webhook(Request request, Response response) {
		String VERIFY_TOKEN = Settings.VERIFY_TOKEN;
		// Parse the query params

		String mode = request.queryParams("hub.mode");
		String token = request.queryParams("hub.verify_token");
		String challenge = request.queryParams("hub.challenge");
		if (mode!=null && token!= null) {
			if(mode.equals("subscribe")) {
			 // Responds with the challenge token from the request
		      System.out.println("Webhook Verified");
		      return challenge;
			}
			else {
				// Responds with '403 Forbidden' if verify tokens do not match
				halt(403); 
			}
		}
		return "NOTHING";
	}
	
}
