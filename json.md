
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
              "payload":"PAYBILL_PAYLOAD"
            },
            {
               "title":"📘 Balance",
              "type":"postback",
              "payload":"PAYBILL_PAYLOAD"
            },
            {
              "title":"🔃 PHP to BTC",
              "type":"postback",
              "payload":"PAYBILL_PAYLOAD"
            },
            {
               "title":"🔄 BTC to PHP",
              "type":"postback",
              "payload":"PAYBILL_PAYLOAD"
            },
            {
               "title":"🔀 Transfer to Binance",
              "type":"postback",
              "payload":"PAYBILL_PAYLOAD"
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
              "payload":"PAYBILL_PAYLOAD"
            },
            {
              "title":"🔁 Trade with cryptocurrency",
              "type":"postback",
              "payload":"PAYBILL_PAYLOAD"
            },
            {
               "title":"📚 Trade History",
              "type":"postback",
              "payload":"PAYBILL_PAYLOAD"
            }
          ]
        },
        {
          "title":"📈 Cryptocompare",
          "type":"nested",
          "call_to_actions":[
            {
              "title":"💰 Select Cryptocurrency",
              "type":"postback",
              "payload":"PAYBILL_PAYLOAD"
            },
            {
               "title":"⏰ Send me notif of a Crypto",
              "type":"postback",
              "payload":"PAYBILL_PAYLOAD"
            }
          ]
        }
      ]
    }
  ],
  "get_started":{
    "payload":"GET_STARTED"
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