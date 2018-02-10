package messenger;

import java.text.DecimalFormat;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import spark.Request;
import spark.Response;

public class Custom {
	public void notification_receive(Request request, Response response) throws Exception {
		JsonElement jelement = new JsonParser().parse(request.body());
		JsonObject data = jelement.getAsJsonObject();
		String symbol = data.get("symbol").getAsString();
		String sender_psid = data.get("sender_psid").getAsString();
		DecimalFormat df = new DecimalFormat("0.00");
		String percentage = df.format(data.get("sender_psid").getAsDouble());
		String base_value = data.get("base_value").getAsString();
		String target_value = data.get("target_value").getAsString();
		
		String reply = String.format("The trade pair \"%s\" moved %s percent. From %s to %s",
				symbol, percentage, base_value, target_value);
		MessengerSend messenger_send = new MessengerSend();
		JsonObject message = new JsonObject();
		message.addProperty("text", reply);
		messenger_send.callSendAPI(sender_psid, message);
	}
}
