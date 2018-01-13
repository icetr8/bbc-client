package coinsph;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import configuration.Settings;

public class CoinsphSend {
	public String send_request(String url, String body) throws Exception {
		String nonce = String.valueOf(System.currentTimeMillis());
		String message = nonce + url + (body != null ? body : "");
		
		Mac mac = Mac.getInstance("HmacSHA256");
		mac.init(new SecretKeySpec(Settings.COINS_PH_SECRET.getBytes(), "HmacSHA256"));
		String signature = new String(Hex.encodeHex(mac.doFinal(message.getBytes())));
		
		CloseableHttpClient client = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader("ACCESS_KEY", Settings.COINS_PH_API_KEY);
		httpGet.setHeader("ACCESS_SIGNATURE", signature);
		httpGet.setHeader("ACCESS_NONCE", nonce);
		httpGet.setHeader("Content-Type", "application/json");
		httpGet.setHeader("Accept", "application/json");
		
		CloseableHttpResponse response = client.execute(httpGet);
		String responseAsString = EntityUtils.toString(response.getEntity());
		//System.out.println(responseAsString);
		System.out.println(response.getStatusLine() + "  in SENDING");
		client.close();
		return responseAsString;
	}
}
