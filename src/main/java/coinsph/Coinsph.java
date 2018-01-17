package coinsph;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Coinsph {
	public JsonObject check_balance() throws Exception{
		String body = "";
		String url = "https://coins.ph/api/v3/crypto-accounts/";
		
		JsonArray accounts = get_crypto_accounts();
		JsonObject data = new JsonObject();
		String peso_balance = accounts.get(0).getAsJsonObject().get("balance").getAsString();
		data.addProperty("peso_balance", peso_balance);
		String btc_balance = accounts.get(1).getAsJsonObject().get("balance").getAsString();
		data.addProperty("btc_balance", btc_balance);
		System.out.println(btc_balance);
		return data;
	}
	
	public String transfer(String address, String amount) throws Exception {
		String url = "https://coins.ph/api/v3/transfers/";
		String body = "";
		
		JsonArray accounts = get_crypto_accounts();
		String btc_balance = accounts.get(1).getAsJsonObject().get("balance").getAsString();
		
		
		return "OK";
	}

	public String load(String number, String load_value) throws Exception {
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
			JsonObject order = data.get("order").getAsJsonObject();
			String amount = order.get("amount").getAsString();
			message = "You have succesfully load " + amount + " php";
		}

		return message;
		 
	}
	public void payin_outlets() throws Exception {
		String url = "https://coins.ph/d/api/payin-outlets/";
		String body = "";
		CoinsphSend send = new CoinsphSend();
		
		String response_data = send.get_request(url, body);
	}
	private int get_rate() throws Exception {
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
	private static JsonArray get_crypto_accounts() throws Exception {
		String body = "";
		String url = "https://coins.ph/api/v3/crypto-accounts/";
		
		CoinsphSend send = new CoinsphSend();
		String response_data = send.get_request(url, body);
		JsonObject data = new JsonParser().parse(response_data).getAsJsonObject();
		JsonArray accounts = data.get("crypto-accounts").getAsJsonArray();
		return accounts;
	}
}
