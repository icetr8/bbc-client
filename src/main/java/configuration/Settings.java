package configuration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Settings {
	
	public static String PAGE_ACCESS_TOKEN="EAABZBJdRFAIQBAK17VVKvx0wJp7wookLbEiWl0V2sRoDhZBkukxjnmk2FooQ11GJmZCFCIfsl7Fwb76miiwf1lNyOynCYVWZBKorBzfQFHlxEpB8KJPxja1h9ZBUQd3ZCEemZCdTddVTdHcagxoIPQ2HTJxkX1FU4ZBdu0hzUulfPyizz15lImzs";
	public static String VERIFY_TOKEN = "<YOUR_VERIFY_TOKEN>";
	public static final int PORT = 4567;
	public static final String MESSENGER_SEND_URL = "https://graph.facebook.com/v2.6/me/messages?access_token=";
	
	// Coins.ph
	public static String COINS_PH_API_KEY = "YTMYEShD6cXUjdnYK0y1krlCX4l9aIEr6AsAFDUN";
	public static String COINS_PH_SECRET = "JO0qf16zQSDTrLVE0A1jOhlrGc5BCy7KvIdHJTMcHPzyUJYrtp";
	public static final String COINS_PH_URL = "https://coins.ph/api/v3/";
	public Settings() throws Exception{
		InputStream in = getClass().getResourceAsStream("/keys.json");
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		String json_string = ReadBigStringIn(reader);
		JsonObject json= new JsonParser().parse(json_string).getAsJsonObject();;
		this.PAGE_ACCESS_TOKEN= json.get("page_access_token").getAsString();
		this.VERIFY_TOKEN = json.get("verify_token").getAsString();
		this.COINS_PH_API_KEY = json.get("coinsph_api_key").getAsString();
		this.COINS_PH_SECRET = json.get("coinsph_secret").getAsString();
	}

	private String ReadBigStringIn(BufferedReader buffIn) throws IOException {
	    StringBuilder everything = new StringBuilder();
	    String line;
	    while( (line = buffIn.readLine()) != null) {
	       everything.append(line);
	    }
	    return everything.toString();
	}
}
