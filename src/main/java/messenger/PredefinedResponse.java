

package messenger;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class PredefinedResponse {
	String img_url = "https://coinsbot-client.herokuapp.com/";
	/* Copy past this
	  {
    	"text": "Select a option!",
    	"quick_replies":[
	      {
	        "content_type":"text",
	        "title":"Coins.ph",
			"image_url":"https://coinsbot-client.herokuapp.com/images/coins_ph_.png",
	        "payload":"{'type':'main_menu', 'msg':'coins.ph'}"
	      },
	      {
	        "content_type":"text",
	        "title":"binance",
	        "image_url":"https://coinsbot-client.herokuapp.com/images/binance.png",
	        "payload":"{'type':'main_menu', 'msg':'binance'}"
	      },
	      {
	        "content_type":"text",
	        "title":"Cryptocompare",
	        "image_url":"https://coinsbot-client.herokuapp.com/images/cryptocompare.png",
	        "payload":"{'type':'main_menu', 'msg':'cryptocompare'}"
	      },
	      {
	        "content_type":"text",
	        "title":"About",
	        "image_url":"https://coinsbot-client.herokuapp.com/images/about.png",
	        "payload":"{'type':'main_menu', 'msg':'about'}"
	      },
	      {
	        "content_type":"location"
	      }
      	]
  	}
	 */
	private String main_menu = 
			"{\n" + 
			"    	\"text\": \"Select a option!\",\n" + 
			"    	\"quick_replies\":[\n" + 
			"	      {\n" + 
			"	        \"content_type\":\"text\",\n" + 
			"	        \"title\":\"Coins.ph\",\n" + 
			"			\"image_url\":\"https://coinsbot-client.herokuapp.com/images/coins_ph_.png\",\n" + 
			"	        \"payload\":\"{'type':'main_menu', 'msg':'coins.ph'}\"\n" + 
			"	      },\n" + 
			"	      {\n" + 
			"	        \"content_type\":\"text\",\n" + 
			"	        \"title\":\"binance\",\n" + 
			"	        \"image_url\":\"https://coinsbot-client.herokuapp.com/images/binance.png\",\n" + 
			"	        \"payload\":\"{'type':'main_menu', 'msg':'binance'}\"\n" + 
			"	      },\n" + 
			"	      {\n" + 
			"	        \"content_type\":\"text\",\n" + 
			"	        \"title\":\"Cryptocompare\",\n" + 
			"	        \"image_url\":\"https://coinsbot-client.herokuapp.com/images/cryptocompare.png\",\n" + 
			"	        \"payload\":\"{'type':'main_menu', 'msg':'cryptocompare'}\"\n" + 
			"	      },\n" + 
			"	      {\n" + 
			"	        \"content_type\":\"text\",\n" + 
			"	        \"title\":\"About\",\n" + 
			"	        \"image_url\":\"https://coinsbot-client.herokuapp.com/images/about.png\",\n" + 
			"	        \"payload\":\"{'type':'main_menu', 'msg':'about'}\"\n" + 
			"	      },\n" + 
			"	      {\n" + 
			"	        \"content_type\":\"location\"\n" + 
			"	      }\n" + 
			"      	]\n" + 
			"  	}" + 
			"";
	public JsonElement MAIN_MENU = new JsonParser().parse(main_menu);
	/*
	 {
    	"text": "Here's a what can you do with your coins.ph account!",
    	"quick_replies":[
	       {
	        "content_type":"text",
	        "title":"Watch Price",
	        "image_url":"https://coinsbot-client.herokuapp.com/images/watch.png",
	        "payload":"{'type':'coinsph', 'msg':'watch_price'}"
	      },
	      {
	        "content_type":"text",
	        "title":"LOAD",
	        "image_url":"https://coinsbot-client.herokuapp.com/images/load.PNG",
	        "payload":"{'type':'coinsph', 'msg':'load'}"
	      },
	      {
	        "content_type":"text",
	        "title":"BALANCE",
	        "image_url":"https://coinsbot-client.herokuapp.com/images/wallet.PNG",
	        "payload":"{'type':'coins.ph', 'msg':'balance'}"
	      },
	      {
	        "content_type":"text",
	        "title":"Convert PHP to BTC",
	        "image_url":"https://coinsbot-client.herokuapp.com/images/about.png",
	        "payload":"{'type':'coins.ph', 'msg':'php_to_btc'}"
	      },
	      {
	        "content_type":"text",
	        "title":"Convert BTC to PHP",
	        "payload":"{'type':'coins.ph', 'msg':'btc_to_php'}"
	      },
	      {
	        "content_type":"text",
	        "title":"Transfer to Binance",
	        "image_url":"https://coinsbot-client.herokuapp.com/images/transfer.png",
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
			"	        \"image_url\":\"https://coinsbot-client.herokuapp.com/images/watch.png\",\n" + 
			"	        \"payload\":\"{'type':'coinsph', 'msg':'watch_price'}\"\n" + 
			"	      },\n" + 
			"	      {\n" + 
			"	        \"content_type\":\"text\",\n" + 
			"	        \"title\":\"LOAD\",\n" + 
			"	        \"image_url\":\"https://coinsbot-client.herokuapp.com/images/load.PNG\",\n" + 
			"	        \"payload\":\"{'type':'coinsph', 'msg':'load'}\"\n" + 
			"	      },\n" + 
			"	      {\n" + 
			"	        \"content_type\":\"text\",\n" + 
			"	        \"title\":\"BALANCE\",\n" + 
			"	        \"image_url\":\"https://coinsbot-client.herokuapp.com/images/wallet.PNG\",\n" + 
			"	        \"payload\":\"{'type':'coins.ph', 'msg':'balance'}\"\n" + 
			"	      },\n" + 
			"	      {\n" + 
			"	        \"content_type\":\"text\",\n" + 
			"	        \"title\":\"Convert PHP to BTC\",\n" + 
			"	        \"image_url\":\"https://coinsbot-client.herokuapp.com/images/about.png\",\n" + 
			"	        \"payload\":\"{'type':'coins.ph', 'msg':'php_to_btc'}\"\n" + 
			"	      },\n" + 
			"	      {\n" + 
			"	        \"content_type\":\"text\",\n" + 
			"	        \"title\":\"Convert BTC to PHP\",\n" + 
			"	        \"payload\":\"{'type':'coins.ph', 'msg':'btc_to_php'}\"\n" + 
			"	      },\n" + 
			"	      {\n" + 
			"	        \"content_type\":\"text\",\n" + 
			"	        \"title\":\"Transfer to Binance\",\n" + 
			"	        \"image_url\":\"https://coinsbot-client.herokuapp.com/images/transfer.png\",\n" + 
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
	/*
	 {
    "attachment": {
      "type": "template",
      "payload": {
        "template_type": "generic",
        "elements": [{
            "title": "Select a percentage of conversion",                   
            "buttons": [ {
                "type":"postback",
                "title":"100% == %100 php",
                "payload":"coinsph_btc_to_php_100%"
            },
            {
                "type": "postback",
                "title": "75% == %75 php",
				"payload":"coinsph_btc_to_php_100%"
            }]
          },
          {
            "title": "Select a percentage of conversion",
            "buttons": [{
                "type": "postback",
                "title": "50% == %50 php",
                "payload":"coinsph_btc_to_php_100%"
            },
            {
                "type": "postback",
                "title": "25% == %25 php",
                "payload":"coinsph_btc_to_php_100%"
            }]
          }]
        }
	  }
  	}
	 */
	public String PHP_TO_BTC = ""
			+ "{\n" + 
			"    \"attachment\": {\n" + 
			"      \"type\": \"template\",\n" + 
			"      \"payload\": {\n" + 
			"        \"template_type\": \"generic\",\n" + 
			"        \"elements\": [{\n" + 
			"            \"title\": \"Select a percentage of conversion\",                   \n" + 
			"            \"buttons\": [ {\n" + 
			"                \"type\":\"postback\",\n" + 
			"                \"title\":\"100% == %100 php\",\n" + 
			"                \"payload\":\"coinsph_btc_to_php_100%\"\n" + 
			"            },\n" + 
			"            {\n" + 
			"                \"type\": \"postback\",\n" + 
			"                \"title\": \"75% == %75 php\",\n" + 
			"				\"payload\":\"coinsph_btc_to_php_100%\"\n" + 
			"            }]\n" + 
			"          },\n" + 
			"          {\n" + 
			"            \"title\": \"Select a percentage of conversion\",\n" + 
			"            \"buttons\": [{\n" + 
			"                \"type\": \"postback\",\n" + 
			"                \"title\": \"50% == %50 php\",\n" + 
			"                \"payload\":\"coinsph_btc_to_php_100%\"\n" + 
			"            },\n" + 
			"            {\n" + 
			"                \"type\": \"postback\",\n" + 
			"                \"title\": \"25% == %25 php\",\n" + 
			"                \"payload\":\"coinsph_btc_to_php_100%\"\n" + 
			"            }]\n" + 
			"          }]\n" + 
			"        }\n" + 
			"	  }\n" + 
			"  	}";
	/*
	 {
   "attachment": {
     "type": "template",
     "payload": {
       "template_type": "generic",
       "elements": [{
           "title": "Select a percentage of conversion",                    
           "buttons": [ {
               "type":"postback",
               "title":"100% == %s php",
               "payload":"{'type':'menu','msg':'100%'}"
           },
           {
               "type": "postback",
               "title": "75% == %s php",
				"payload":"{'type':'menu','msg':'75%'}"
           }]
         },
         {
           "title": "Select a percentage of conversion",
           "buttons": [{
               "type": "postback",
               "title": "50% == %s php",
               "payload":"{'type':'menu','msg':'50%'}"
           },
           {
               "type": "postback",
               "title": "25% == %s php",
               "payload":"{'type':'menu','msg':'25%'}"
           }]
         }]
       }
	  }
 	}
	 */
}
