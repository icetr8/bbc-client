package coinsph;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Coinsph {
	public JsonObject check_balance() throws Exception{
		JsonArray accounts = get_crypto_accounts();
		JsonObject data = new JsonObject();
		
		String peso_balance = accounts.get(0).getAsJsonObject().get("balance").getAsString();
		data.addProperty("peso_balance", peso_balance);
		String btc_balance = accounts.get(1).getAsJsonObject().get("balance").getAsString();
		data.addProperty("btc_balance", btc_balance);

		return data;
	}
	
	public String transfer(String address, int amount) throws Exception {
		String url = "https://coins.ph/api/v3/transfers/";
		JsonObject body = new JsonObject();
		body.addProperty("target_address", address);
		body.addProperty("amount", amount);
		
		JsonArray accounts = get_crypto_accounts();
		String id = accounts.get(1).getAsJsonObject().get("id").getAsString();
		body.addProperty("account", id);
		
		CoinsphSend send = new CoinsphSend();
		String response_data = send.post_request(url, body.toString());
		JsonObject data = new JsonParser().parse(response_data).getAsJsonObject();
	
		String message = "";
		if(data.get("errors")!=null) {
			JsonObject errors = data.getAsJsonObject().get("errors").getAsJsonObject();
			Set set = errors.keySet();
			List list = new ArrayList(set);
			message = (String) list.get(0);
			message += " " + errors.get(message).getAsJsonArray().get(0);
		}else {
			message = "You have succesfully transfer with an amount of " + amount + " btc to " + address ;
		}
		
		return message;
	}

	public JsonObject load(String number, String load_value) throws Exception {
		String url = "https://coins.ph/api/v2/sellorder";
		JsonObject body = new JsonObject();
		double load= Integer.parseInt(load_value != null ? load_value : "10");
		double rate = get_rate().get("rate").getAsDouble();
		body.addProperty("payment_outlet", "load-globe");
		body.addProperty("btc_amount", rate);
		body.addProperty("currency", "PHP");
		body.addProperty("currency_amount_locked", Integer.parseInt(load_value != null ? load_value : "10") );
		body.addProperty("pay_with_wallet", "PBTC");
		body.addProperty("phone_number_load", (number != null ? number : "+639953274805"));
		
		CoinsphSend send = new CoinsphSend();
		String response_data = send.post_request(url, body.toString());
		
		JsonObject data = new JsonParser().parse(response_data).getAsJsonObject();
		
		JsonObject message = new JsonObject();
		if(data.get("errors")!=null) {
			message.addProperty("error", data.getAsJsonArray("errors").get(0).getAsString());
		}else {
			JsonObject order = data.get("order").getAsJsonObject();
			String amount = order.get("amount").getAsString();
			message.addProperty("amount", amount);
		}

		return message; 
	}
	
	public void payin_outlets() throws Exception {
		String url = "https://coins.ph/d/api/payin-outlets/";
		String body = "";
		CoinsphSend send = new CoinsphSend();
		
		String response_data = send.get_request(url, body);
	}
	private JsonObject get_rate() throws Exception {
		String url = "https://quote.coins.ph/v1/markets/BTC-PHP";
		String body = "";
		
		CoinsphSend send = new CoinsphSend();
		String response_data = send.raw_get_request(url, body.toString());
		JsonObject data = new JsonParser().parse(response_data).getAsJsonObject();
		JsonObject market = data.get("market").getAsJsonObject();
		int bid = market.get("bid").getAsInt();
		int ask = market.get("ask").getAsInt();
		
		JsonObject response = new JsonObject();
		response.addProperty("bid", bid);
		response.addProperty("ask", ask);
		response.addProperty("rate", bid / ask);
		return response;
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

	public JsonObject php_to_btc(double source_amount) throws Exception {
		String url = "https://coins.ph/api/v3/crypto-exchanges/";
		JsonObject body = new JsonObject();
		
		body.addProperty("source_amount", source_amount);
		
		JsonArray accounts = get_crypto_accounts();
		String peso_id = accounts.get(0).getAsJsonObject().get("id").getAsString();
		body.addProperty("source_account", peso_id);
		String btc_id = accounts.get(1).getAsJsonObject().get("id").getAsString();
		body.addProperty("target_account", btc_id);
		
		CoinsphSend send = new CoinsphSend();
		String response_data = send.post_request(url, body.toString());
		JsonObject response = new JsonParser().parse(response_data).getAsJsonObject();
		
		
		JsonObject data = new JsonObject();
		if(response.get("errors")!=null) {
			JsonObject errors = response.getAsJsonObject().get("errors").getAsJsonObject();
			Set set = errors.keySet();
			List list = new ArrayList(set);
			data.addProperty("error_type", (String) list.get(0));
			String error_type = data.get("error_type").getAsString();
			data.addProperty("error", errors.get(error_type).getAsJsonArray().getAsString() );
			
		}else {
			JsonObject exchange = response.get("crypto-exchange").getAsJsonObject();
			data.addProperty("source_amount", source_amount);
			data.addProperty("target_amount", exchange.get("target_amount").getAsString());
			data.addProperty("rate", exchange.get("rate").getAsString());
		}

		return data;
		
	}
	public JsonObject btc_to_php(double source_amount) throws Exception {
		String url = "https://coins.ph/api/v3/crypto-exchanges/";
		JsonObject body = new JsonObject();
		
		body.addProperty("source_amount", source_amount);
		
		JsonArray accounts = get_crypto_accounts();
		String peso_id = accounts.get(1).getAsJsonObject().get("id").getAsString();
		body.addProperty("source_account", peso_id);
		String btc_id = accounts.get(0).getAsJsonObject().get("id").getAsString();
		body.addProperty("target_account", btc_id);
		
		CoinsphSend send = new CoinsphSend();
		String response_data = send.post_request(url, body.toString());
		JsonObject response = new JsonParser().parse(response_data).getAsJsonObject();
		
		
		JsonObject data = new JsonObject();
		if(response.get("errors")!=null) {
			JsonObject errors = response.getAsJsonObject().get("errors").getAsJsonObject();
			Set set = errors.keySet();
			List list = new ArrayList(set);
			data.addProperty("error_type", (String) list.get(0));
			String error_type = data.get("error_type").getAsString();
			data.addProperty("error", errors.get(error_type).getAsJsonArray().getAsString() );
			
		}else {
			JsonObject exchange = response.get("crypto-exchange").getAsJsonObject();
			data.addProperty("source_amount", source_amount);
			data.addProperty("target_amount", exchange.get("target_amount").getAsString());
			data.addProperty("rate", exchange.get("rate").getAsString());
		}

		return data;
		
	}
}
