package coinsph;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import configuration.Settings;

public class CoinsphSend {
	public String get_request(String url, String body) throws Exception {
		Settings settings = new Settings();
		String nonce = String.valueOf(System.currentTimeMillis());
		String message = nonce + url + (body != null ? body : "");
		
		Mac mac = Mac.getInstance("HmacSHA256");
		mac.init(new SecretKeySpec(settings.COINS_PH_SECRET.getBytes(), "HmacSHA256"));
		String signature = new String(Hex.encodeHex(mac.doFinal(message.getBytes())));
		
		CloseableHttpClient client = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader("ACCESS_KEY", settings.COINS_PH_API_KEY);
		httpGet.setHeader("ACCESS_SIGNATURE", signature);
		httpGet.setHeader("ACCESS_NONCE", nonce);
		httpGet.setHeader("Content-Type", "application/json;charset=UTF-8");
		httpGet.setHeader("Accept", "application/json");
		
		CloseableHttpResponse response = client.execute(httpGet);
		String responseAsString = EntityUtils.toString(response.getEntity());
		//System.out.println(responseAsString);
		System.out.println(response.getStatusLine() + "  in SENDING");
		client.close();
		return responseAsString;
	}
	
	public String post_request(String url, String body) throws Exception {
		Settings settings = new Settings();
		String nonce = String.valueOf(System.currentTimeMillis());
		String message = nonce + url + (body != null ? body : "");
		
		Mac mac = Mac.getInstance("HmacSHA256");
		mac.init(new SecretKeySpec(settings.COINS_PH_SECRET.getBytes(), "HmacSHA256"));
		String signature = new String(Hex.encodeHex(mac.doFinal(message.getBytes())));
		
		CloseableHttpClient client = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("ACCESS_KEY", settings.COINS_PH_API_KEY);
		httpPost.setHeader("ACCESS_SIGNATURE", signature);
		httpPost.setHeader("ACCESS_NONCE", nonce);
		httpPost.setHeader("Content-Type", "application/json");
		httpPost.setHeader("Accept", "application/json");
		StringEntity entity = new StringEntity(body);
		httpPost.setEntity(entity);
		String responseString = EntityUtils.toString(entity, "UTF-8");
		httpPost.setEntity(entity);
		//System.out.println(responseString);

		CloseableHttpResponse response = client.execute(httpPost);
		String responseAsString = EntityUtils.toString(response.getEntity());
		//System.out.println(responseAsString);
		System.out.println(response.getStatusLine() + "  in SENDING");
		client.close();
		return responseAsString;
	}
	public String raw_get_request(String url, String body) throws Exception {
		CloseableHttpClient client = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);

		CloseableHttpResponse response = client.execute(httpGet);
		String responseAsString = EntityUtils.toString(response.getEntity());
		//System.out.println(responseAsString);
		System.out.println(response.getStatusLine() + "  in SENDING");
		client.close();
		return responseAsString;
	}
}
