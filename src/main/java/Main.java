import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;

import configuration.Settings;

import messenger.Messenger;

public class Main {
	private static final String something = null;

	public static void main(String[] args) throws Exception {

		port(getHerokuAssignedPort());

		get("/webhook", (request, response) -> {
			Messenger messenger = new Messenger();
			String Challenge = Messenger.verify_webhook(request, response);
			return Challenge;
		});
		post("/webhook", (request, response) -> {
			Messenger messenger = new Messenger();
			String res = Messenger.event_reciever(request, response);
			return res;
		});
	}
	static int getHerokuAssignedPort() {
		ProcessBuilder processBuilder = new ProcessBuilder();
		if (processBuilder.environment().get("PORT") != null) {
			return Integer.parseInt(processBuilder.environment().get("PORT"));
		}
		return Settings.PORT; // return default port if heroku-port isn't set (i.e. on localhost)
	}

}