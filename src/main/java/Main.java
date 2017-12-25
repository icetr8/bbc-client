import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.halt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import javax.servlet.http.Part;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
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
    public static void main(String[] args) throws Exception {
    	
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
        			String sender_psid = getPSID(messagingObject);
        			System.out.println(sender_psid);
        			if (messagingObject.get("message_postback") != null) {
        				
        			}
        			else if (messagingObject.get("message") != null) {
        				JsonObject messageObject = messagingObject.getAsJsonObject("message");
        				handleMessage(sender_psid, messageObject);
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
    // Handles messages events
	static void handleMessage(String sender_psid, JsonObject received_message) throws Exception {
		JsonObject reply = new JsonObject();


		if (received_message.get("text") != null) {
			System.out.println(received_message.get("text"));
			reply.addProperty("text", "You sent the message: "+received_message.get("text")+". Now send me an image!");
		}
		else if (received_message.get("attachments")!= null) {
			  
		    // Gets the URL of the message attachment
		    String attachment_url = "";
		  
		  } 
		
			callSendAPI(sender_psid, reply);
		

		}

		// Handles messaging_postbacks events
	static String handlePostback(String sender_psid, String received_postback) {
		return something;

		}

		// Sends response messages via the Send API
	static void callSendAPI(String sender_psid, JsonObject reply) throws Exception {
		JsonObject request_body = new JsonObject();
		JsonObject id = new JsonObject();
		id.addProperty("id", sender_psid);
		request_body.add("recipient", id);
		request_body.add("message", reply);
		String json = request_body.toString();
		String uri = "https://graph.facebook.com/v2.6/me/messages?access_token=EAABZBJdRFAIQBAK17VVKvx0wJp7wookLbEiWl0V2sRoDhZBkukxjnmk2FooQ11GJmZCFCIfsl7Fwb76miiwf1lNyOynCYVWZBKorBzfQFHlxEpB8KJPxja1h9ZBUQd3ZCEemZCdTddVTdHcagxoIPQ2HTJxkX1FU4ZBdu0hzUulfPyizz15lImzs";
		System.out.println(json);
		CloseableHttpClient client = HttpClients.createDefault();
	    HttpPost httpPost = new HttpPost(uri);
	    StringEntity entity = new StringEntity(json);
	    httpPost.setEntity(entity);
	    httpPost.setHeader("Content-type", "application/json");
	 
	    CloseableHttpResponse response = client.execute(httpPost);
	    System.out.println(response.getStatusLine());
	    client.close();
	}
    	
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }

}