package messenger;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import configuration.Settings;

public class MessengerSend {
	MessengerSend() throws Exception{
		Settings settings = new Settings();
		String messenger_send_url = Settings.MESSENGER_SEND_URL;
		String page_access_token = Settings.PAGE_ACCESS_TOKEN;
	}
	void callSendAPI(String sender_psid, JsonElement message) throws Exception {
		JsonObject request_body = new JsonObject();
		JsonObject id = new JsonObject();
		id.addProperty("id", sender_psid);
		request_body.add("recipient", id);
		request_body.add("message", message);
		String json = request_body.toString();

		Settings settings = new Settings();
		String uri = Settings.MESSENGER_SEND_URL + Settings.PAGE_ACCESS_TOKEN;

		CloseableHttpClient client = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(uri);
		StringEntity entity = new StringEntity(json);
		String responseString = EntityUtils.toString(entity, "UTF-8");
		httpPost.setEntity(entity);
		//System.out.println(responseString);
		httpPost.setHeader("Content-type", "application/json");

		CloseableHttpResponse response = client.execute(httpPost);
		String responseAsString = EntityUtils.toString(response.getEntity());
		//System.out.println(responseAsString);
		System.out.println(response.getStatusLine() + "  in SENDING");
		client.close();
	}
}
