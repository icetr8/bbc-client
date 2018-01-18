package configuration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiRestClient;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Settings {
	
	public static String PAGE_ACCESS_TOKEN= "";
	public static String VERIFY_TOKEN = "";
	public static final int PORT = 4567;
	public static final String MESSENGER_SEND_URL = "https://graph.facebook.com/v2.6/me/messages?access_token=";
	
	// Coins.ph
	public static String COINS_PH_API_KEY = "";
	public static String COINS_PH_SECRET = "";
	public static final String COINS_PH_URL = "https://coins.ph/api/v3/";
	
	//binance
	public static BinanceApiRestClient client;
	public Settings() throws Exception{
		InputStream in = getClass().getResourceAsStream("/keys.json");
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		String json_string = ReadBigStringIn(reader);
		JsonObject json= new JsonParser().parse(json_string).getAsJsonObject();;
		Settings.PAGE_ACCESS_TOKEN= json.get("page_access_token").getAsString();
		Settings.VERIFY_TOKEN = json.get("verify_token").getAsString();
		Settings.COINS_PH_API_KEY = json.get("coinsph_api_key").getAsString();
		Settings.COINS_PH_SECRET = json.get("coinsph_secret").getAsString();
		
		String api_key = json.get("binance_api_key").getAsString();
		String binance_secret = json.get("binance_secret").getAsString();
		BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance(api_key, binance_secret);
		this.client = factory.newRestClient();
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
