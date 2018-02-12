package Cryptocompare;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import utils.Utils;

public class cryptocompare {
	public static JsonObject ticker(String symbol) throws Exception{
		String url = "https://api.coinmarketcap.com/v1/ticker/";
		
		//System.out.println(Utils.raw_get_request(url));
		CloseableHttpClient client = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);

		CloseableHttpResponse response = client.execute(httpGet);
		String responseAsString = EntityUtils.toString(response.getEntity());
		//System.out.println(responseAsString);
		//System.out.println(response.getStatusLine() + "  in SENDING");
		client.close();
		
		JsonObject data = new JsonObject();
		JsonArray arr= new JsonParser().parse(responseAsString).getAsJsonArray();
		for(int x = 0; x<arr.size(); x++) {
			JsonObject crypto = arr.get(x).getAsJsonObject();
			if(symbol.equalsIgnoreCase(crypto.get("symbol").getAsString())){
				data.addProperty("name", crypto.get("name").getAsString());
				data.addProperty("rank", crypto.get("rank").getAsString());
				data.addProperty("price_usd", crypto.get("price_usd").getAsString());
				data.addProperty("24h_volume_usd", crypto.get("24h_volume_usd").getAsString());
				data.addProperty("market_cap_usd", crypto.get("market_cap_usd").getAsString());
				data.addProperty("available_supply", crypto.get("available_supply").getAsString());
				data.addProperty("total_supply", crypto.get("total_supply").getAsString());
				data.addProperty("max_supply", crypto.get("max_supply").getAsString());
				data.addProperty("percent_change_1h", crypto.get("rank").getAsString());
				data.addProperty("percent_change_24h", crypto.get("percent_change_24h").getAsString());
				data.addProperty("percent_change_7d", crypto.get("percent_change_7d").getAsString());
			}	
		}
		if (data.get("name")==null) {
			data.addProperty("error", "Crypto symbol does not exist");
		}
		return data;
	}
}
