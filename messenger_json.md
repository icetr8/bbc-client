
- GET STARTED EVENT

```
{
	"object": "page",
	"entry": [
		{
			"id": "326577454490506",
			"time": 1514718404920,
			"messaging": [
				{
					"recipient": {
						"id": "326577454490506"
					},
					"timestamp": 1514718404920,
					"sender": {
						"id": "1817289878344392"
					},
					"postback": {
						"payload": "GET_STARTED",
						"title": "Get Started"
					}
				}
			]
		}
	]
}
```
- messenger_profile

```
{
  "persistent_menu":[
    {
      "locale":"default",
      "composer_input_disabled": false,
      "call_to_actions":[
        {
          "title":"💶 Coins.ph",
          "type":"nested",
          "call_to_actions":[
            {
              "title":"📲 Load",
              "type":"postback",
              "payload":"{'type':'coinsph', 'msg':'load'}"
            },
            {
               "title":"📘 Balance",
              "type":"postback",
              "payload":"{'type':'coinsph', 'msg':'balance'}"
            },
            {
              "title":"🔃 PHP to BTC",
              "type":"postback",
              "payload":"{'type':'coinsph', 'msg':'php_to_btc'}"
            },
            {
               "title":"🔄 BTC to PHP",
              "type":"postback",
              "payload":"{'type':'coinsph', 'msg':'btc_to_php'}"
            },
            {
               "title":"🔀 Transfer to Binance",
              "type":"postback",
              "payload":"{'type':'coinsph', 'msg':'transfer_to_binance'}"
            }
          ]
        },
        {
          "title":"💲 Binance",
          "type":"nested",
          "call_to_actions":[
            {
              "title":"📒 View Funds",
              "type":"postback",
              "payload":"{'type':'binance', 'msg':'view_funds'}"
            },
            {
              "title":"🔁 Trade with a cryptocurrency",
              "type":"postback",
              "payload":"{'type':'binance', 'msg':'trade_with_a_cryptocurrency'}"
            },
            {
               "title":"Buy",
              "type":"postback",
              "payload":"{'type':'binance', 'msg':'buy'}"
            },
            {
               "title":"Sell",
              "type":"postback",
              "payload":"{'type':'binance', 'msg':'sell'}"
            },
            {
               "title":"📚 Trade History",
              "type":"postback",
              "payload":"{'type':'binance', 'msg':'trade_history'}"
            }
          ]
        },
        {
          "title":"📈 Other",
          "type":"nested",
          "call_to_actions":[
            {
              "title":"💰 Select a Cryptocurrency",
              "type":"postback",
              "payload":"{'type':'cryptocompare', 'msg':'select_a_cryptocurrency'}"
            },
            {
               "title":"⏰ Send me notif of a Crypto",
              "type":"postback",
              "payload":"{'type':'cryptocompare', 'msg':'notifications'}"
            },
            {
               "title":"💸 Cash-in via 7-11 Barcode",
              "type":"postback",
              "payload":"{'type':'cryptocompare', 'msg':'seven_eleven'}"
            }
          ]
        }
      ]
    }
  ],
  "get_started":{
    "payload":"{'type':'main_menu', 'msg':'get_started'}"
  },
  "whitelisted_domains":[
    "https://coinsbot-client.herokuapp.com/",
    "https://347461cb.ngrok.io"
  ],
  
  "greeting":[
  {
    "locale":"default",
    "text":"Hello {{user_first_name}}! Welcome to BIT BLOCK CHAT. Convert your PHP to BTC and start trading your BTC to other Cryptocurrencies. "
  }
]
}
```