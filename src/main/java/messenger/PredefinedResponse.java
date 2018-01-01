package messenger;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class PredefinedResponse {
	/* Copy past this
	  {
    	"text": "Select a option!",
    	"quick_replies":[
	      {
	        "content_type":"text",
	        "title":"Coins.ph",
	        "payload":"{'type':'main_menu', 'msg':'coins.ph'}"
	      },
	      {
	        "content_type":"text",
	        "title":"binance",
	        "payload":"{'type':'main_menu', 'msg':'binance'}"
	      },
	      {
	        "content_type":"text",
	        "title":"Cryptocompare",
	        "payload":"{'type':'main_menu', 'msg':'cryptocompare'}"
	      },
	      {
	        "content_type":"text",
	        "title":"About",
	        "payload":"{'type':'main_menu', 'msg':'about'}"
	      },
	      {
	        "content_type":"location"
	      }
      	]
  	}
	 */
	private String main_menu = 
			" {\n" + 
			"    	\"text\": \"Select a option!\",\n" + 
			"    	\"quick_replies\":[\n" + 
			"	      {\n" + 
			"	        \"content_type\":\"text\",\n" + 
			"	        \"title\":\"Coins.ph\",\n" + 
			"	        \"payload\":\"{'type':'main_menu', 'msg':'coins.ph'}\"\n" + 
			"	      },\n" + 
			"	      {\n" + 
			"	        \"content_type\":\"text\",\n" + 
			"	        \"title\":\"binance\",\n" + 
			"	        \"payload\":\"{'type':'main_menu', 'msg':'binance'}\"\n" + 
			"	      },\n" + 
			"	      {\n" + 
			"	        \"content_type\":\"text\",\n" + 
			"	        \"title\":\"Cryptocompare\",\n" + 
			"	        \"payload\":\"{'type':'main_menu', 'msg':'cryptocompare'}\"\n" + 
			"	      },\n" + 
			"	      {\n" + 
			"	        \"content_type\":\"text\",\n" + 
			"	        \"title\":\"About\",\n" + 
			"	        \"payload\":\"{'type':'main_menu', 'msg':'about'}\"\n" + 
			"	      },\n" + 
			"	      {\n" + 
			"	        \"content_type\":\"location\"\n" + 
			"	      }\n" + 
			"      	]\n" + 
			"  	}";
	public JsonElement MAIN_MENU = new JsonParser().parse(main_menu);
	/*
	 {
    	"text": "Here's a what can you do with your coins.ph account!",
    	"quick_replies":[
	       {
	        "content_type":"text",
	        "title":"Watch Price",
	        "payload":"{'type':'coinsph', 'msg':'watch_price'}"
	      },
	      {
	        "content_type":"text",
	        "title":"LOAD",
	        "payload":"{'type':'coinsph', 'msg':'load'}"
	      },
	      {
	        "content_type":"text",
	        "title":"BALANCE",
	        "payload":"{'type':'coins.ph', 'msg':'balance'}"
	      },
	      {
	        "content_type":"text",
	        "title":"Convert PHP to BTC",
	        "payload":"{'type':'coins.ph', msg':'php_to_btc'}"
	      },
	      {
	        "content_type":"text",
	        "title":"Convert BTC to PHP",
	        "payload":"{'type':'coins.ph', 'msg':'btc_to_php'}"
	      },
	      {
	        "content_type":"text",
	        "title":"Transfer to Binance",
	        "payload":"{'type':'coins.ph', 'msg':'transfer'}"
	      }
      	]
  	}
	 */
	private String coins_ph = 
			"{\n" + 
			"    	\"text\": \"Here's a what can you do with your coins.ph account!\",\n" + 
			"    	\"quick_replies\":[\n" + 
			"	       {\n" + 
			"	        \"content_type\":\"text\",\n" + 
			"	        \"title\":\"Watch Price\",\n" + 
			"	        \"payload\":\"{'type':'coinsph', 'msg':'watch_price'}\"\n" + 
			"	      },\n" + 
			"	      {\n" + 
			"	        \"content_type\":\"text\",\n" + 
			"	        \"title\":\"LOAD\",\n" + 
			"	        \"payload\":\"{'type':'coinsph', 'msg':'load'}\"\n" + 
			"	      },\n" + 
			"	      {\n" + 
			"	        \"content_type\":\"text\",\n" + 
			"	        \"title\":\"BALANCE\",\n" + 
			"	        \"payload\":\"{'type':'coins.ph', 'msg':'balance'}\"\n" + 
			"	      },\n" + 
			"	      {\n" + 
			"	        \"content_type\":\"text\",\n" + 
			"	        \"title\":\"Convert PHP to BTC\",\n" + 
			"	        \"payload\":\"{'type':'coins.ph', msg':'php_to_btc'}\"\n" + 
			"	      },\n" + 
			"	      {\n" + 
			"	        \"content_type\":\"text\",\n" + 
			"	        \"title\":\"Convert BTC to PHP\",\n" + 
			"	        \"payload\":\"{'type':'coins.ph', 'msg':'btc_to_php'}\"\n" + 
			"	      },\n" + 
			"	      {\n" + 
			"	        \"content_type\":\"text\",\n" + 
			"	        \"title\":\"Transfer to Binance\",\n" + 
			"	        \"payload\":\"{'type':'coins.ph', 'msg':'transfer'}\"\n" + 
			"	      }\n" + 
			"      	]\n" + 
			"  	}";
	public final JsonElement COINS_PH = new JsonParser().parse(coins_ph);
	/* Copy past this
	  {
	  	"text": "Here's what can you do with your Binance Account",
	  	"quick_replies":[
	      {
	        "content_type":"text",
	        "title":"View Funds",
	        "payload":"{'type':'binance', 'msg':'view_funds'}"
	      },
	      {
	        "content_type":"text",
	        "title":"Select A cryptocurrency",
	        "payload":"{'type':'binance', 'msg':'select'}"
	      },
	      {
	        "content_type":"text",
	        "title":"trade_history",
	        "payload":"{'type':'binance', 'msg':'history'}"
	      }
    	]
	}
	 */
	private String binance = 
			"{\n" + 
			"	  	\"text\": \"Here's what can you do with your Binance Account\",\n" + 
			"	  	\"quick_replies\":[\n" + 
			"	      {\n" + 
			"	        \"content_type\":\"text\",\n" + 
			"	        \"title\":\"View Funds\",\n" + 
			"	        \"payload\":\"{'type':'binance', 'msg':'view_funds'}\"\n" + 
			"	      },\n" + 
			"	      {\n" + 
			"	        \"content_type\":\"text\",\n" + 
			"	        \"title\":\"Select A cryptocurrency\",\n" + 
			"	        \"payload\":\"{'type':'binance', 'msg':'select'}\"\n" + 
			"	      },\n" + 
			"	      {\n" + 
			"	        \"content_type\":\"text\",\n" + 
			"	        \"title\":\"trade_history\",\n" + 
			"	        \"payload\":\"{'type':'binance', 'msg':'history'}\"\n" + 
			"	      }\n" + 
			"    	]\n" + 
			"	}";
	public final JsonElement BINANCE = new JsonParser().parse(binance);
	/*
	 {
	  	"text": "Here's what can you do with your Cryptocompare",
	  	"quick_replies":[
	      {
	        "content_type":"text",
	        "title":"View Funds",
	        "payload":"{'type':'binance', 'msg':'view_funds'}"
	      },
	      {
	        "content_type":"text",
	        "title":"Select A cryptocurrency",
	        "payload":"{'type':'binance', 'msg':'select'}"
	      },
	      {
	        "content_type":"text",
	        "title":"trade_history",
	        "payload":"{'type':'binance', 'msg':'history'}"
	      }
    	]
	 }
	 */
	private String cryptocompare = "";
	public final JsonElement CRYPTOCOMPARE = new JsonParser().parse(cryptocompare);
}
