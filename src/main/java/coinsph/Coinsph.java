package coinsph;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Coinsph {
	public static void check_balance() throws Exception{
		String body = "";
		String url = "https://coins.ph/api/v3/crypto-accounts/";
		CoinsphSend send = new CoinsphSend();
		
		String response_data = send.get_request(url, body);
		;
	}
	public static void payin_outlets() throws Exception {
		String url = "https://coins.ph/d/api/payin-outlets/";
		String body = "";
		CoinsphSend send = new CoinsphSend();

		String response_data = send.get_request(url, body);
	}
	public static String load(String number, String load_value) throws Exception {
		String url = "https://coins.ph/api/v2/sellorder";
		JsonObject body = new JsonObject();
		double load= Integer.parseInt(load_value != null ? load_value : "10");
		double rate = get_rate();
		body.addProperty("payment_outlet", "load-globe");
		body.addProperty("btc_amount", rate);
		body.addProperty("currency", "PHP");
		body.addProperty("currency_amount_locked", Integer.parseInt(load_value != null ? load_value : "10") );
		body.addProperty("pay_with_wallet", "PBTC");
		body.addProperty("phone_number_load", (number != null ? number : "+639953274805"));
		
		CoinsphSend send = new CoinsphSend();
		String response_data = send.post_request(url, body.toString());
		System.out.println(response_data);
		
		JsonObject data = new JsonParser().parse(response_data).getAsJsonObject();
		
		String message = "";
		if(data.get("errors")!=null) {
			message = data.getAsJsonArray("errors").get(0).getAsString();
		}else {
			
		}

		return message;
		 
	}
	public static int get_rate() throws Exception {
		String url = "https://quote.coins.ph/v1/markets/BTC-PHP";
		String body = "";
		
		CoinsphSend send = new CoinsphSend();
		String response_data = send.raw_get_request(url, body.toString());
		JsonObject data = new JsonParser().parse(response_data).getAsJsonObject();
		JsonObject market = data.get("market").getAsJsonObject();
		int bid = market.get("bid").getAsInt();
		int ask = market.get("ask").getAsInt();
		
		return bid / ask;
	}
}
