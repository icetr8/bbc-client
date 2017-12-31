package messenger;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class PredefinedResponse {
	/* Copy past this
	  {
    	"text": "Here's a quick reply!",
    	"quick_replies":[
	      {
	        "content_type":"text",
	        "title":"Coins.ph",
	        "payload":"{"type":"menu", "msg":"coins.ph"}"
	      },
	      {
	        "content_type":"text",
	        "title":"binance",
	        "payload":"{"type":"menu", "msg":"binance"}"
	      },
	      {
	        "content_type":"text",
	        "title":"Cryptocompare",
	        "payload":"{"type":"menu", "msg":"cryptocompare"}"
	      },
	      {
	        "content_type":"text",
	        "title":"About",
	        "payload":"{"type":"menu", "msg":"about"}"
	      }, 
	      {
	        "content_type":"location"
	      }
      	]
  	}
	 */
	private String main_menu = 
			"{\n" + 
			"    	\"text\": \"Here's a quick reply!\",\n" + 
			"    	\"quick_replies\":[\n" + 
			"	      {\n" + 
			"	        \"content_type\":\"text\",\n" + 
			"	        \"title\":\"Coins.ph\",\n" + 
			"	        \"payload\":\"coins.ph\"\n" + 
			"	      },\n" + 
			"	      {\n" + 
			"	        \"content_type\":\"text\",\n" + 
			"	        \"title\":\"binance\",\n" + 
			"	        \"payload\":\"binance\"\n" + 
			"	      },\n" + 
			"	      {\n" + 
			"	        \"content_type\":\"text\",\n" + 
			"	        \"title\":\"cryptocurrencies\",\n" + 
			"	        \"payload\": \"crpytocurrencies\"\n" + 
			"	      }, \n" + 
			"	      {\n" + 
			"	        \"content_type\":\"location\"\n" + 
			"	      }\n" + 
			"      	]\n" + 
			"  	}";
	public JsonElement MAIN_MENU = new JsonParser().parse(main_menu);
	/*
	 {
    	"text": "Here's a quick reply!",
    	"quick_replies":[
	       {
	        "content_type":"text",
	        "title":"Watch Price",
	        "payload":"{"type":"menu", "msg":"watch_price"}"
	      },
	      {
	        "content_type":"text",
	        "title":"LOAD",
	        "payload":"{"type":"menu", "msg":"load"}"
	      },
	      {
	        "content_type":"text",
	        "title":"BALANCE",
	        "payload":"{"type":"menu", "msg":"balance"}"
	      },
	      {
	        "content_type":"text",
	        "title":"Convert PHP to BTC",
	        "payload":"{"type":"menu", "msg":"php_to_btc"}"
	      },
	      {
	        "content_type":"text",
	        "title":"Convert BTC to PHP",
	        "payload":"{"type":"menu", "msg":"btc_to_php"}"
	      },
	      {
	        "content_type":"text",
	        "title":"Transfer to Binance",
	        "payload":"{"type":"menu", "msg":"transfer"}"
	      }
      	]
  	}
	 */
	private String coins_ph = 
			"{\n" + 
			"    	\"text\": \"Here's a quick reply!\",\n" + 
			"    	\"quick_replies\":[\n" + 
			"    	  {\n" + 
			"	        \"content_type\":\"text\",\n" + 
			"	        \"title\":\"Watch Price\",\n" + 
			"	        \"payload\":\"WATCH_PRICE\"\n" + 
			"	      },\n" + 
			"	      {\n" + 
			"	        \"content_type\":\"text\",\n" + 
			"	        \"title\":\"LOAD\",\n" + 
			"	        \"payload\":\"LOAD\"\n" + 
			"	      },\n" + 
			"	      {\n" + 
			"	        \"content_type\":\"text\",\n" + 
			"	        \"title\":\"BALANCE\",\n" + 
			"	        \"payload\":\"BALANCE\"\n" + 
			"	      },\n" + 
			"	      {\n" + 
			"	        \"content_type\":\"text\",\n" + 
			"	        \"title\":\"Convert PHP to BTC\",\n" + 
			"	        \"payload\": \"PHP>BTC\"\n" + 
			"	      },\n" + 
			"	      {\n" + 
			"	        \"content_type\":\"text\",\n" + 
			"	        \"title\":\"Convert BTC to PHP\",\n" + 
			"	        \"payload\": \"BTC>PHP\"\n" + 
			"	      },\n" + 
			"	      {\n" + 
			"	        \"content_type\":\"text\",\n" + 
			"	        \"title\":\"Convert PHP to BTC\",\n" + 
			"	        \"payload\": \"cryptoCompare\"\n" + 
			"	      }\n" + 
			"      	]\n" + 
			"  	  }";
	public final JsonElement COINS_PH = new JsonParser().parse(coins_ph);
	/* Copy past this
	  {
  	"text": "Here's what can you do with your Binance Account",
  	"quick_replies":[
	      {
	        "content_type":"text",
	        "title":"View Funds",
	        "payload":"{"type":"Binance", "msg":"view_funds"}"
	      },
	      {
	        "content_type":"text",
	        "title":"Select A cryptocurrency",
	        "payload":"{"type":"Binance", "msg":"select"}"
	      },
	      {
	        "content_type":"Trade History",
	        "title":"trade_history",
	        "payload":"{"type":"Binance", "msg":"history"}"
	      }, 
    	]
	}
	 */
}
