import static spark.Spark.get;

import static spark.Spark.port;
import static spark.Spark.post;

import configuration.Settings;

import messenger.Messenger;
import spark.Spark;
import coinsph.Coinsph;
public class Main {
	private static final String something = null;

	public static void main(String[] args) throws Exception {
		Coinsph coinsph = new Coinsph();
		coinsph.check_balance();
		//coinsph.load("+639953274805", "10");
		//coinsph.rates();
		//coinsph.transfer("testing", "1");
		port(getHerokuAssignedPort());
		
		Spark.staticFiles.location("/public");
        // Static files caching is disabled by default
        // staticFiles.expireTime(600L);

		get("/secureHello", (req, res) -> "Hello Secure World");
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