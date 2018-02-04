package utils;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Utils {
	public static JsonObject raw_get_request(String url) throws Exception {
		CloseableHttpClient client = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);

		CloseableHttpResponse response = client.execute(httpGet);
		String responseAsString = EntityUtils.toString(response.getEntity());
		//System.out.println(responseAsString);
		//System.out.println(response.getStatusLine() + "  in SENDING");
		client.close();
		JsonArray json= new JsonParser().parse(responseAsString).getAsJsonArray();
		return json.get(0).getAsJsonObject();
	}
	
	public static String raw_post_request(String url, String body) throws Exception {
		CloseableHttpClient client = HttpClients.createDefault();
		
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Content-type", "application/json");
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
}
