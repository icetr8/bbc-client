import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.halt;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import javax.servlet.http.Part;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import static spark.Spark.delete;
import static spark.Spark.path;

public class Main {
	public static final String PAGE_ACCESS_TOKEN="<PAGE_ACCESS_TOKEN>";
	private static final String something = null;
    public static void main(String[] args) {
    	
        port(getHerokuAssignedPort());
        path("/webhook", () -> {
        	get("",       (request, response) -> {
        		// Your verify token. Should be a random string.
        		String VERIFY_TOKEN = "<YOUR_VERIFY_TOKEN>";
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
            	return "adasd:";
            });
            post("",       (request, response) -> {           	
            	String body = request.body();;
            	ObjectMapper mapper = new ObjectMapper();
            	Map<String,Object> map = mapper.readValue(body, Map.class);
            	String page = (String) map.get("object");
        		if (page.equalsIgnoreCase("page")) {
        			JsonObject messagingObject = getMessageObject(request.body());
        			String psid = getPSID(messagingObject);
        			if (messagingObject.get("message_postback") != null) {
        				
        			}
        			else if (messagingObject.get("message") != null) {
        				System.out.println(messagingObject.toString());
        				
        			}
        			
        		}
        		else
        			halt(404);
            	return "EVENT_RECEIVED";
            	
            });
        });
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
    static String getMessage(JsonObject messagingobject) {
    	JsonObject message = messagingobject.getAsJsonObject("message");
    	String text = message.get("text").getAsString();
    	return text;
    }
    static String getPSID(JsonObject messagingobject) {
    	JsonObject message = messagingobject.getAsJsonObject("sender");
    	String psid = message.get("id").getAsString();
    	return psid;
    }
    // Handles messages events
	static String handleMessage(String sender_psid, JsonObject received_message) {
		return something;

		}

		// Handles messaging_postbacks events
	static String handlePostback(String sender_psid, String received_postback) {
		return something;

		}

		// Sends response messages via the Send API
	static String callSendAPI(String sender_psid, String response) {
		return something;
		  
		}
    	
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }

}