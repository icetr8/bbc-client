import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.halt;



import java.util.Map;
 
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import static spark.Spark.delete;
import static spark.Spark.path;

public class Main {

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
        		if (mode != null && token!= null) {
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
        		if (page.equalsIgnoreCase("page"))
        			System.out.println("ASDASD");
        		else
        			halt(404);
            	return "EVENT_RECEIVED";
            	
            });
        });
    }
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }

}