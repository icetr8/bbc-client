

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
            "subtitle":"You're total balance is %php php & %btc btc",
            "image_url":"https://coinsbot-client.herokuapp.com/images/coins_ph_cover.jpg",
            "buttons": [ {
                "type":"postback",
                "title":"100% == %100 php",
                "payload":"{'type':'coinsph_php_to_btc', 'msg':'100%'}"
            },
            {
                "type": "postback",
                "title": "75% == %75 php",
				"payload":"{'type':'coinsph_php_to_btc', 'msg':'75%'}"
            }]
          },
          {
            "title": "Select a percentage of conversion",
            "image_url":"https://coinsbot-client.herokuapp.com/images/coins_ph_cover.jpg",
            "buttons": [{
                "type": "postback",
                "title": "50% == %50 php",
                "payload":"{'type':'coinsph_php_to_btc', 'msg':'50%'}"
            },
            {
                "type": "postback",
                "title": "25% == %25 php",
                "payload":"{'type':'coinsph_php_to_btc', 'msg':'25%'}"
            }]
          }]
        }
	  }
  	}
	 */
	public String PHP_TO_BTC = "{\n" + 
			"    \"attachment\": {\n" + 
			"      \"type\": \"template\",\n" + 
			"      \"payload\": {\n" + 
			"        \"template_type\": \"generic\",\n" + 
			"        \"elements\": [{\n" + 
			"            \"title\": \"Select a percentage of conversion\", \n" + 
			"            \"subtitle\":\"You're total balance is %php php & %btc btc\",\n" + 
			"            \"image_url\":\"https://coinsbot-client.herokuapp.com/images/coins_ph_cover.jpg\",\n" + 
			"            \"buttons\": [ {\n" + 
			"                \"type\":\"postback\",\n" + 
			"                \"title\":\"100% == %100 php\",\n" + 
			"                \"payload\":\"{'type':'coinsph_php_to_btc', 'msg':'100%'}\"\n" + 
			"            },\n" + 
			"            {\n" + 
			"                \"type\": \"postback\",\n" + 
			"                \"title\": \"75% == %75 php\",\n" + 
			"				\"payload\":\"{'type':'coinsph_php_to_btc', 'msg':'75%'}\"\n" + 
			"            }]\n" + 
			"          },\n" + 
			"          {\n" + 
			"            \"title\": \"Select a percentage of conversion\",\n" + 
			"            \"image_url\":\"https://coinsbot-client.herokuapp.com/images/coins_ph_cover.jpg\",\n" + 
			"            \"buttons\": [{\n" + 
			"                \"type\": \"postback\",\n" + 
			"                \"title\": \"50% == %50 php\",\n" + 
			"                \"payload\":\"{'type':'coinsph_php_to_btc', 'msg':'50%'}\"\n" + 
			"            },\n" + 
			"            {\n" + 
			"                \"type\": \"postback\",\n" + 
			"                \"title\": \"25% == %25 php\",\n" + 
			"                \"payload\":\"{'type':'coinsph_php_to_btc', 'msg':'25%'}\"\n" + 
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
            "subtitle":"You're total balance is %php php & %btc btc",
            "image_url":"https://coinsbot-client.herokuapp.com/images/coins_ph_cover.jpg",    
            "buttons": [ {
                "type":"postback",
                "title":"100% == %100 php",
                "payload":"{'type':'coinsph_btc_to_php', 'msg':'100%'}"
            },
            {
                "type": "postback",
                "title": "75% == %75 php",
				"payload":"{'type':'coinsph_btc_to_php', 'msg':'75%'}"
            }]
          },
          {
            "title": "Select a percentage of conversion",
            "image_url":"https://coinsbot-client.herokuapp.com/images/coins_ph_cover.jpg",    
            "buttons": [{
                "type": "postback",
                "title": "50% == %50 php",
                "payload":"{'type':'coinsph_btc_to_php', 'msg':'50%'}"
            },
            {
                "type": "postback",
                "title": "25% == %25 php",
                "payload":"{'type':'coinsph_btc_to_php', 'msg':'25%'}"
            }]
          }]
        }
	  }
  	}
	 */
	public String btc_to_php = "{\n" + 
			"    \"attachment\": {\n" + 
			"      \"type\": \"template\",\n" + 
			"      \"payload\": {\n" + 
			"        \"template_type\": \"generic\",\n" + 
			"        \"elements\": [{\n" + 
			"            \"title\": \"Select a percentage of conversion\",\n" + 
			"            \"subtitle\":\"You're total balance is %php php & %btc btc\",\n" + 
			"            \"image_url\":\"https://coinsbot-client.herokuapp.com/images/coins_ph_cover.jpg\",    \n" + 
			"            \"buttons\": [ {\n" + 
			"                \"type\":\"postback\",\n" + 
			"                \"title\":\"100% == %100 php\",\n" + 
			"                \"payload\":\"{'type':'coinsph_btc_to_php', 'msg':'100%'}\"\n" + 
			"            },\n" + 
			"            {\n" + 
			"                \"type\": \"postback\",\n" + 
			"                \"title\": \"75% == %75 php\",\n" + 
			"				\"payload\":\"{'type':'coinsph_btc_to_php', 'msg':'75%'}\"\n" + 
			"            }]\n" + 
			"          },\n" + 
			"          {\n" + 
			"            \"title\": \"Select a percentage of conversion\",\n" + 
			"            \"image_url\":\"https://coinsbot-client.herokuapp.com/images/coins_ph_cover.jpg\",    \n" + 
			"            \"buttons\": [{\n" + 
			"                \"type\": \"postback\",\n" + 
			"                \"title\": \"50% == %50 php\",\n" + 
			"                \"payload\":\"{'type':'coinsph_btc_to_php', 'msg':'50%'}\"\n" + 
			"            },\n" + 
			"            {\n" + 
			"                \"type\": \"postback\",\n" + 
			"                \"title\": \"25% == %25 php\",\n" + 
			"                \"payload\":\"{'type':'coinsph_btc_to_php', 'msg':'25%'}\"\n" + 
			"            }]\n" + 
			"          }]\n" + 
			"        }\n" + 
			"	  }\n" + 
			"  	}";
	/*
	 {
	  	"text": "Great! You have %balance PHP in your wallet. Please choose an amount.",
	  	"quick_replies":[
	      {
	        "content_type":"text",
	        "title":"₱ 10",
	        "payload":"{'type':'coinsph_load', 'msg':'10L'}"
	      },
	      {
	        "content_type":"text",
	        "title":"₱ 15",
	        "payload":"{'type':'coinsph_load', 'msg':'15L'}"
	      },
	      {
	        "content_type":"text",
	        "title":"₱ 20",
	        "payload":"{'type':'coinsph_load', 'msg':'20L'}"
	      },
	      {
	        "content_type":"text",
	        "title":"₱ 25",
	        "payload":"{'type':'coinsph_load', 'msg':'25L'}"
	      },
	      {
	        "content_type":"text",
	        "title":"₱ 30",
	        "payload":"{'type':'coinsph_load', 'msg':'30L'}"
	      },
	      {
	        "content_type":"text",
	        "title":"₱ 50",
	        "payload":"{'type':'coinsph_load', 'msg':'50L'}"
	      },
	      {
	        "content_type":"text",
	        "title":"₱ 70",
	        "payload":"{'type':'coinsph_load', 'msg':'70L'}"
	      },
	      {
	        "content_type":"text",
	        "title":"₱ 100",
	        "payload":"{'type':'coinsph_load', 'msg':'100L'}"
	      },
	      {
	        "content_type":"text",
	        "title":"₱ 300",
	        "payload":"{'type':'coinsph_load', 'msg':'300L'}"
	      },
	      {
	        "content_type":"text",
	        "title":"₱ 500",
	        "payload":"{'type':'coinsph_load', 'msg':'500L'}"
	      },
	      {
	        "content_type":"text",
	        "title":"₱ 1000",
	        "payload":"{'type':'coinsph_load', 'msg':'1000L'}"
	      }
   	]
	 }
	 */
	public String load = "{\n" + 
			"	  	\"text\": \"Great! You have %balance PHP in your wallet. Please choose an amount.\",\n" + 
			"	  	\"quick_replies\":[\n" + 
			"	      {\n" + 
			"	        \"content_type\":\"text\",\n" + 
			"	        \"title\":\"₱ 10\",\n" + 
			"	        \"payload\":\"{'type':'coinsph_load', 'msg':'10L'}\"\n" + 
			"	      },\n" + 
			"	      {\n" + 
			"	        \"content_type\":\"text\",\n" + 
			"	        \"title\":\"₱ 15\",\n" + 
			"	        \"payload\":\"{'type':'coinsph_load', 'msg':'15L'}\"\n" + 
			"	      },\n" + 
			"	      {\n" + 
			"	        \"content_type\":\"text\",\n" + 
			"	        \"title\":\"₱ 20\",\n" + 
			"	        \"payload\":\"{'type':'coinsph_load', 'msg':'20L'}\"\n" + 
			"	      },\n" + 
			"	      {\n" + 
			"	        \"content_type\":\"text\",\n" + 
			"	        \"title\":\"₱ 25\",\n" + 
			"	        \"payload\":\"{'type':'coinsph_load', 'msg':'25L'}\"\n" + 
			"	      },\n" + 
			"	      {\n" + 
			"	        \"content_type\":\"text\",\n" + 
			"	        \"title\":\"₱ 30\",\n" + 
			"	        \"payload\":\"{'type':'coinsph_load', 'msg':'30L'}\"\n" + 
			"	      },\n" + 
			"	      {\n" + 
			"	        \"content_type\":\"text\",\n" + 
			"	        \"title\":\"₱ 50\",\n" + 
			"	        \"payload\":\"{'type':'coinsph_load', 'msg':'50L'}\"\n" + 
			"	      },\n" + 
			"	      {\n" + 
			"	        \"content_type\":\"text\",\n" + 
			"	        \"title\":\"₱ 70\",\n" + 
			"	        \"payload\":\"{'type':'coinsph_load', 'msg':'70L'}\"\n" + 
			"	      },\n" + 
			"	      {\n" + 
			"	        \"content_type\":\"text\",\n" + 
			"	        \"title\":\"₱ 100\",\n" + 
			"	        \"payload\":\"{'type':'coinsph_load', 'msg':'100L'}\"\n" + 
			"	      },\n" + 
			"	      {\n" + 
			"	        \"content_type\":\"text\",\n" + 
			"	        \"title\":\"₱ 300\",\n" + 
			"	        \"payload\":\"{'type':'coinsph_load', 'msg':'300L'}\"\n" + 
			"	      },\n" + 
			"	      {\n" + 
			"	        \"content_type\":\"text\",\n" + 
			"	        \"title\":\"₱ 500\",\n" + 
			"	        \"payload\":\"{'type':'coinsph_load', 'msg':'500L'}\"\n" + 
			"	      },\n" + 
			"	      {\n" + 
			"	        \"content_type\":\"text\",\n" + 
			"	        \"title\":\"₱ 1000\",\n" + 
			"	        \"payload\":\"{'type':'coinsph_load', 'msg':'1000L'}\"\n" + 
			"	      }\n" + 
			"   	]\n" + 
			"	 }";
	/*
	 {
	  	"text": "You have %balance php and %bitcoin btc. If you want to use some of these funds, just load and get 10% rebate or transfer and start trading it with binance exchange"
	 }
	 */
	public String coinsph_balance = " {\n" + 
			"	  	\"text\": \"You have %balance php and %bitcoin btc. If you want to use some of these funds, just load and get 10% rebate or transfer and start trading it with binance exchange\"\n" + 
			"	 }";
	/*
	 {
   "attachment": {
     "type": "template",
     "payload": {
       "template_type": "generic",
       "elements": [{
           "title": "Select a percentage of Transfer",
           "subtitle":"You're total balance is %php php & %btc btc",
           "image_url":"https://coinsbot-client.herokuapp.com/images/coins_ph_cover.png",    
           "buttons": [ {
               "type":"postback",
               "title":"100% == %100 php",
               "payload":"{'type':'coinsph_btc_to_php', 'msg':'100%'}"
           },
           {
               "type": "postback",
               "title": "75% == %75 php",
				"payload":"{'type':'coinsph_btc_to_php', 'msg':'75%'}"
           }]
         },
         {
           "title": "Select a percentage of Transfer",
           "subtitle":"You're total balance is %php php & %btc btc",
           "image_url":"https://coinsbot-client.herokuapp.com/images/coins_ph_cover.png",    
           "buttons": [{
               "type": "postback",
               "title": "50% == %50 php",
               "payload":"{'type':'coinsph_btc_to_php', 'msg':'50%'}"
           },
           {
               "type": "postback",
               "title": "25% == %25 php",
               "payload":"{'type':'coinsph_btc_to_php', 'msg':'25%'}"
           }]
         }]
       }
	  }
 	}
	 */
	public String transfer_to_binance = ""
			+ "{\n" + 
			"   \"attachment\": {\n" + 
			"     \"type\": \"template\",\n" + 
			"     \"payload\": {\n" + 
			"       \"template_type\": \"generic\",\n" + 
			"       \"elements\": [{\n" + 
			"           \"title\": \"Select a percentage of Transfer\",\n" + 
			"           \"subtitle\":\"You're total balance is %php php & %btc btc\",\n" + 
			"           \"image_url\":\"https://coinsbot-client.herokuapp.com/images/coins_ph_cover.png\",    \n" + 
			"           \"buttons\": [ {\n" + 
			"               \"type\":\"postback\",\n" + 
			"               \"title\":\"100% == %100 php\",\n" + 
			"               \"payload\":\"{'type':'coinsph_btc_to_php', 'msg':'100%'}\"\n" + 
			"           },\n" + 
			"           {\n" + 
			"               \"type\": \"postback\",\n" + 
			"               \"title\": \"75% == %75 php\",\n" + 
			"				\"payload\":\"{'type':'coinsph_btc_to_php', 'msg':'75%'}\"\n" + 
			"           }]\n" + 
			"         },\n" + 
			"         {\n" + 
			"           \"title\": \"Select a percentage of Transfer\",\n" + 
			"           \"subtitle\":\"You're total balance is %php php & %btc btc\",\n" + 
			"           \"image_url\":\"https://coinsbot-client.herokuapp.com/images/coins_ph_cover.png\",    \n" + 
			"           \"buttons\": [{\n" + 
			"               \"type\": \"postback\",\n" + 
			"               \"title\": \"50% == %50 php\",\n" + 
			"               \"payload\":\"{'type':'coinsph_btc_to_php', 'msg':'50%'}\"\n" + 
			"           },\n" + 
			"           {\n" + 
			"               \"type\": \"postback\",\n" + 
			"               \"title\": \"25% == %25 php\",\n" + 
			"               \"payload\":\"{'type':'coinsph_btc_to_php', 'msg':'25%'}\"\n" + 
			"           }]\n" + 
			"         }]\n" + 
			"       }\n" + 
			"	  }\n" + 
			" 	}";
	/*
	 {
	  	"text": "You have total balance of %bitcoin btc(%balance usd), Start trading now by selecting a cryptocurrency"
	 }
	 */
	public final String VIEW_FUNDS = ""
			+ "{\n" + 
			"	  	\"text\": \"You have total balance of %bitcoin btc(%usd usd), Start trading now by selecting a cryptocurrency\"\n" + 
			"	 }";
	/*
	 {
	  	"text": "You have assets in this following coins/cryptos
	  				%assets"
	 }
	 */
	 public final String VIEW_ASSETS = ""
	 		+ "{\n" + 
	 		"	  	\"text\": \"You have assets in this following coins/cryptos\n" + 
	 		"	  				%assets\"\n" + 
	 		"	 }";
	 /*
	 {
   "attachment": {
     "type": "template",
     "payload": {
       "template_type": "generic",
       "elements": [{
           "title": "Select a Base currency",
           "subtitle":"Balance: %usd , %btc btc",
           "image_url":"https://coinsbot-client.herokuapp.com/images/coins_ph_cover.jpg",    
           "buttons": [ {
               "type":"postback",
               "title":"US DOLLAR",
               "payload":"{'type':'binance', 'msg':'base_buy_usdt'}"
           },
           {
               "type": "postback",
               "title": "BITCOIN",
				"payload":"{'type':'binance', 'msg':'base_buy_btc'}"
           }]
         },
         {
           "title": "Select a base currency",
           "subtitle":"Balance: %bnb bnb, %eth eth",
           "image_url":"https://coinsbot-client.herokuapp.com/images/coins_ph_cover.jpg",    
           "buttons": [{
               "type": "postback",
               "title": "BINANCE COINS",
               "payload":"{'type':'binance', 'msg':'base_buy_bnb'}"
           },
           {
               "type": "postback",
               "title": "ETHERUEM",
               "payload":"{'type':'binance', 'msg':'base_buy_eth'}"
           }]
         }]
       }
	  }
 	}
	 */
	 public final String BASE = "{\n" + 
	 		"   \"attachment\": {\n" + 
	 		"     \"type\": \"template\",\n" + 
	 		"     \"payload\": {\n" + 
	 		"       \"template_type\": \"generic\",\n" + 
	 		"       \"elements\": [{\n" + 
	 		"           \"title\": \"Select a Base currency\",\n" + 
	 		"           \"subtitle\":\"Balance: %usd , %btc btc\",\n" + 
	 		"           \"image_url\":\"https://coinsbot-client.herokuapp.com/images/coins_ph_cover.png\",    \n" + 
	 		"           \"buttons\": [ {\n" + 
	 		"               \"type\":\"postback\",\n" + 
	 		"               \"title\":\"US DOLLAR\",\n" + 
	 		"               \"payload\":\"{'type':'binance', 'msg':'base_usdt'}\"\n" + 
	 		"           },\n" + 
	 		"           {\n" + 
	 		"               \"type\": \"postback\",\n" + 
	 		"               \"title\": \"BITCOIN\",\n" + 
	 		"				\"payload\":\"{'type':'binance', 'msg':'base_btc'}\"\n" + 
	 		"           }]\n" + 
	 		"         },\n" + 
	 		"         {\n" + 
	 		"           \"title\": \"Select a base currency\",\n" + 
	 		"           \"subtitle\":\"Balance: %bnb bnb, %eth eth\",\n" + 
	 		"           \"image_url\":\"https://coinsbot-client.herokuapp.com/images/coins_ph_cover.png\",    \n" + 
	 		"           \"buttons\": [{\n" + 
	 		"               \"type\": \"postback\",\n" + 
	 		"               \"title\": \"BINANCE COINS\",\n" + 
	 		"               \"payload\":\"{'type':'binance', 'msg':'base_bnb'}\"\n" + 
	 		"           },\n" + 
	 		"           {\n" + 
	 		"               \"type\": \"postback\",\n" + 
	 		"               \"title\": \"ETHERUEM\",\n" + 
	 		"               \"payload\":\"{'type':'binance', 'msg':'base_eth'}\"\n" + 
	 		"           }]\n" + 
	 		"         }]\n" + 
	 		"       }\n" + 
	 		"	  }\n" + 
	 		" 	}";
}
