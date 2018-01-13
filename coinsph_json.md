- balance

```
{
	"meta": {
		"total_count": 3,
		"next_page": null,
		"previous_page": null
	},
	"crypto-accounts": [
		{
			"id": "eadc41eeddcc468393cd27992267aec1",
			"name": "Default Account",
			"currency": "PBTC",
			"balance": "0.33",
			"pending_balance": "0",
			"total_received": "4478.24",
			"default_address": "9207bcca26de4fada27e25804cc5ae96",
			"is_default": true
		},
		{
			"id": "5c92f79dd53c4c388fcf5f9f6a06d2bf",
			"name": "Default Account",
			"currency": "BTC",
			"balance": "0.00268165",
			"pending_balance": "0",
			"total_received": "0.00545661",
			"default_address": "3NVMUhCdf9tTrNnD4fmxivJWbr82nVMjuy",
			"is_default": true
		},
		{
			"id": "81ec77dd5a7b4840a5cb8e74e1b02e46",
			"name": "Default Account",
			"currency": "CLP",
			"balance": "0",
			"pending_balance": "0",
			"total_received": "0",
			"default_address": "0702249a28fa49a6824fa97a26203e41",
			"is_default": true
		}
	]
}
```

- payin-outlets

```
{
	"payin-outlets": [
		{
			"id": "bdo_deposit_cashcard",
			"outlet_category": "bank_deposit",
			"payment_outlet_type": {
				"id": "bank_deposit",
				"name": "Over-The-Counter Banking",
				"fields": [
					{
						"id": "payment_confirmation_code",
						"name": "Payment confirmation code",
						"field_type": "",
						"help_text": "",
						"help_link": ""
					}
				],
				"optional_fields": [],
				"remittance_fields": [],
				"fee_info": "",
				"payout_duration": "Within 24 hours",
				"outlets": [
					"dragonpay_rcbx_deposit",
					"dragonpay_cbcx_deposit",
					"union_deposit",
					"dragonpay_bpix_deposit",
					"bank_of_commerce_deposit"
				],
				"outlet_names_subset": [
					"RCBC (via DragonPay)",
					"Chinabank (via DragonPay)"
				],
				"is_express": false,
				"is_commodity_outlet": false
			},
			"amount_limits": [
				{
					"currency": "PHP",
					"minimum": 15,
					"maximum": 100000000,
					"increment": 0.00001
				}
			],
			"denominations": [],
			"name": "BDO Cash Card",
			"region": "PH",
			"verification_level_requirement": -1,
			"help_text": "Enjoy a cash-in fee rebate of P10 to P40 when you add money to your peso wallet with BDO. Limited time only.",
			"help_link": "",
			"disabled_notice": "",
			"disclaimer_notice": "",
			"logo_url": "",
			"instructions": "<ol>\r\n  <li>Please deposit the exact total due amount (<strong>{{ order.payment_due | currency }}</strong>) at any BDO branch to the following account:\r\n  <pre style=\"font-size:small\">\r\n    Account name: BETUR INC.\r\n    Account number: 008178000421\r\n    Account type: CURRENT</pre>\r\n  For help finding a BDO branch near you, use this\r\n  <a href=\"https://www.bdo.com.ph/branches-atms-locator\"> branch locator</a>.\r\n  </li>\r\n  <li>Once you have deposited the full amount, please <strong>mark your order as paid</strong>.</li>\r\n<li>We will confirm your payment and release the funds to your account. <br/> <br/>Processing may take up to 1 hour for regular working days (Mondays to Fridays, between 10 AM to 6 PM).  Orders placed outside our working hours will be processed on the next working day.</li>\r\n</ol>",
			"payout_duration": null,
			"is_express": false,
			"is_custom_denomination_allowed": true,
			"is_email_required": false,
			"importance": null
		},
		{
			"id": "bitcoin_prepaid_deposit",
			"outlet_category": "bitcoin_prepaid_deposit",
			"payment_outlet_type": {
				"id": "bitcoin_prepaid_deposit",
				"name": "Bitcoin Prepaid",
				"fields": [],
				"optional_fields": [],
				"remittance_fields": [],
				"fee_info": null,
				"payout_duration": null,
				"outlets": [],
				"outlet_names_subset": [],
				"is_express": false,
				"is_commodity_outlet": false
			},
			"amount_limits": [
				{
					"currency": "PHP",
					"minimum": 0,
					"maximum": 100000000,
					"increment": 0.00001
				}
			],
			"denominations": [],
			"name": "Bitcoin Prepaid",
			"region": "PH",
			"verification_level_requirement": 0,
			"help_text": "",
			"help_link": "",
			"disabled_notice": "",
			"disclaimer_notice": "",
			"logo_url": "",
			"instructions": "",
			"payout_duration": null,
			"is_express": null,
			"is_custom_denomination_allowed": true,
			"is_email_required": false,
			"importance": null
		},
		{
			"id": "bpiatm_deposit",
			"outlet_category": "atm_deposit",
			"payment_outlet_type": {
				"id": "atm_deposit",
				"name": "Bank ATM Cash In",
				"fields": [
					{
						"id": "payment_confirmation_code",
						"name": "Payment confirmation code",
						"field_type": "",
						"help_text": "",
						"help_link": ""
					}
				],
				"optional_fields": [],
				"remittance_fields": [],
				"fee_info": "",
				"payout_duration": "",
				"outlets": [],
				"outlet_names_subset": [],
				"is_express": false,
				"is_commodity_outlet": false
			},
			"amount_limits": [
				{
					"currency": "PHP",
					"minimum": 15,
					"maximum": 100000000,
					"increment": 0.00001
				}
			],
			"denominations": [],
			"name": "BPI 24-hour ATM Deposit",
			"region": "PH",
			"verification_level_requirement": 0,
			"help_text": "Make a cash deposit via a BPI ATM. Please note that the machines do not dispense change and only accept bills in the following denominations: PHP100, PHP500 and PHP1,000",
			"help_link": "https://coinsph.zendesk.com/hc/en-us/articles/202637070",
			"disabled_notice": "",
			"disclaimer_notice": "",
			"logo_url": "",
			"instructions": "<ol>\r\n  <li>Deposit the exact total cash amount ({{ order.payment_due | currency }}) at any BPI 24-hour ATM to the following account:\r\n  <pre style=\"font-size:small\">\r\n    Account name: Betur Inc. \r\n    Account number: 8371-0022-36 \r\n    Account type: Current\r\n    Deposit amount: {{ order.payment_due | currency }}\r\n  </pre>\r\n  </li>\r\n  <li>Please note that the ATMs only accept bills in the following denominations: PHP100, PHP500 and PHP1000. The machines do not dispense change. You may choose to round up or round down your deposit to the nearest hundred, and we will adjust the amount of Bitcoin based on what you deposit. \r\n  </li>\r\n  <li><strong>Important:</strong> Once you have deposited the full amount, mark your order as paid by following this <a href=\"{{ url_for('exchange.order', order_id=order.order_id, _external=True) }}\">link.</a> -- you must do this within {{ order.expires_time | minutes_until }} minutes or your order will automatically expire!</li>\r\n  <li>Once you’ve marked your order as paid, we will confirm your payment and release the Bitcoin.</li>\r\n\r\n</ol>",
			"payout_duration": null,
			"is_express": false,
			"is_custom_denomination_allowed": true,
			"is_email_required": false,
			"importance": null
		},
		{
			"id": "coins_deposit_btc",
			"outlet_category": "coins_deposit_fee_outlet",
			"payment_outlet_type": {
				"id": "coins_deposit_fee_outlet",
				"name": "Coins Deposit Outlet",
				"fields": [],
				"optional_fields": [],
				"remittance_fields": [],
				"fee_info": null,
				"payout_duration": null,
				"outlets": [
					"coins_cebuana_lhuillier_deposit_php",
					"coins_coins_pera_padala_deposit_php",
					"coins_seven_connect_cliqq_deposit_php",
					"coins_seven_connect_deposit_php"
				],
				"outlet_names_subset": [
					"Coins Deposit PHP: Cebuana Instant",
					"Coins Deposit PHP: Coins Pera Padala"
				],
				"is_express": false,
				"is_commodity_outlet": false
			},
			"amount_limits": [],
			"denominations": [],
			"name": "Coins Deposit BTC",
			"region": "PH",
			"verification_level_requirement": 0,
			"help_text": "",
			"help_link": "",
			"disabled_notice": "",
			"disclaimer_notice": "",
			"logo_url": "",
			"instructions": "",
			"payout_duration": null,
			"is_express": null,
			"is_custom_denomination_allowed": true,
			"is_email_required": false,
			"importance": null
		},
		{
			"id": "coins_deposit_fee",
			"outlet_category": "coins_deposit_fee_outlet",
			"payment_outlet_type": {
				"id": "coins_deposit_fee_outlet",
				"name": "Coins Deposit Outlet",
				"fields": [],
				"optional_fields": [],
				"remittance_fields": [],
				"fee_info": null,
				"payout_duration": null,
				"outlets": [
					"coins_cebuana_lhuillier_deposit_php",
					"coins_coins_pera_padala_deposit_php",
					"coins_seven_connect_cliqq_deposit_php",
					"coins_seven_connect_deposit_php"
				],
				"outlet_names_subset": [
					"Coins Deposit PHP: Cebuana Instant",
					"Coins Deposit PHP: Coins Pera Padala"
				],
				"is_express": false,
				"is_commodity_outlet": false
			},
			"amount_limits": [],
			"denominations": [],
			"name": "Default Coins Deposit Fee",
			"region": "GLOBAL",
			"verification_level_requirement": 0,
			"help_text": "",
			"help_link": "",
			"disabled_notice": "",
			"disclaimer_notice": "",
			"logo_url": "",
			"instructions": "",
			"payout_duration": null,
			"is_express": null,
			"is_custom_denomination_allowed": true,
			"is_email_required": false,
			"importance": null
		},
		{
			"id": "coins_deposit_php",
			"outlet_category": "coins_deposit_fee_outlet",
			"payment_outlet_type": {
				"id": "coins_deposit_fee_outlet",
				"name": "Coins Deposit Outlet",
				"fields": [],
				"optional_fields": [],
				"remittance_fields": [],
				"fee_info": null,
				"payout_duration": null,
				"outlets": [
					"coins_cebuana_lhuillier_deposit_php",
					"coins_coins_pera_padala_deposit_php",
					"coins_seven_connect_cliqq_deposit_php",
					"coins_seven_connect_deposit_php"
				],
				"outlet_names_subset": [
					"Coins Deposit PHP: Cebuana Instant",
					"Coins Deposit PHP: Coins Pera Padala"
				],
				"is_express": false,
				"is_commodity_outlet": false
			},
			"amount_limits": [],
			"denominations": [],
			"name": "Coins Deposit PHP",
			"region": "PH",
			"verification_level_requirement": 0,
			"help_text": "",
			"help_link": "",
			"disabled_notice": "",
			"disclaimer_notice": "",
			"logo_url": "",
			"instructions": "",
			"payout_duration": null,
			"is_express": null,
			"is_custom_denomination_allowed": true,
			"is_email_required": false,
			"importance": null
		},
		{
			"id": "coins_globe_prepaid_deposit_btc",
			"outlet_category": "coins_deposit_fee_outlet",
			"payment_outlet_type": {
				"id": "coins_deposit_fee_outlet",
				"name": "Coins Deposit Outlet",
				"fields": [],
				"optional_fields": [],
				"remittance_fields": [],
				"fee_info": null,
				"payout_duration": null,
				"outlets": [
					"coins_cebuana_lhuillier_deposit_php",
					"coins_coins_pera_padala_deposit_php",
					"coins_seven_connect_cliqq_deposit_php",
					"coins_seven_connect_deposit_php"
				],
				"outlet_names_subset": [
					"Coins Deposit PHP: Cebuana Instant",
					"Coins Deposit PHP: Coins Pera Padala"
				],
				"is_express": false,
				"is_commodity_outlet": false
			},
			"amount_limits": [],
			"denominations": [],
			"name": "Coins Deposit BTC: Globe Share-a-Load",
			"region": "PH",
			"verification_level_requirement": 0,
			"help_text": "",
			"help_link": "",
			"disabled_notice": "",
			"disclaimer_notice": "",
			"logo_url": "",
			"instructions": "",
			"payout_duration": null,
			"is_express": null,
			"is_custom_denomination_allowed": true,
			"is_email_required": false,
			"importance": null
		},
		{
			"id": "coins_smart_prepaid_deposit_btc",
			"outlet_category": "coins_deposit_fee_outlet",
			"payment_outlet_type": {
				"id": "coins_deposit_fee_outlet",
				"name": "Coins Deposit Outlet",
				"fields": [],
				"optional_fields": [],
				"remittance_fields": [],
				"fee_info": null,
				"payout_duration": null,
				"outlets": [
					"coins_cebuana_lhuillier_deposit_php",
					"coins_coins_pera_padala_deposit_php",
					"coins_seven_connect_cliqq_deposit_php",
					"coins_seven_connect_deposit_php"
				],
				"outlet_names_subset": [
					"Coins Deposit PHP: Cebuana Instant",
					"Coins Deposit PHP: Coins Pera Padala"
				],
				"is_express": false,
				"is_commodity_outlet": false
			},
			"amount_limits": [],
			"denominations": [],
			"name": "Coins Deposit BTC: Smart PasaLoad",
			"region": "PH",
			"verification_level_requirement": 0,
			"help_text": "",
			"help_link": "",
			"disabled_notice": "",
			"disclaimer_notice": "",
			"logo_url": "",
			"instructions": "",
			"payout_duration": null,
			"is_express": null,
			"is_custom_denomination_allowed": true,
			"is_email_required": false,
			"importance": null
		},
		{
			"id": "dragonpay_ewbx_deposit",
			"outlet_category": "bank_deposit",
			"payment_outlet_type": {
				"id": "bank_deposit",
				"name": "Over-The-Counter Banking",
				"fields": [
					{
						"id": "payment_confirmation_code",
						"name": "Payment confirmation code",
						"field_type": "",
						"help_text": "",
						"help_link": ""
					}
				],
				"optional_fields": [],
				"remittance_fields": [],
				"fee_info": "",
				"payout_duration": "Within 24 hours",
				"outlets": [
					"dragonpay_rcbx_deposit",
					"dragonpay_cbcx_deposit",
					"union_deposit",
					"dragonpay_bpix_deposit",
					"bank_of_commerce_deposit"
				],
				"outlet_names_subset": [
					"RCBC (via DragonPay)",
					"Chinabank (via DragonPay)"
				],
				"is_express": false,
				"is_commodity_outlet": false
			},
			"amount_limits": [
				{
					"currency": "PHP",
					"minimum": 15,
					"maximum": 100000000,
					"increment": 0.00001
				}
			],
			"denominations": [],
			"name": "EastWest Bank",
			"region": "PH",
			"verification_level_requirement": 0,
			"help_text": "",
			"help_link": "",
			"disabled_notice": "",
			"disclaimer_notice": "",
			"logo_url": "",
			"instructions": "",
			"payout_duration": null,
			"is_express": false,
			"is_custom_denomination_allowed": true,
			"is_email_required": false,
			"importance": null
		},
		{
			"id": "dragonpay_otc_none_bank_deposit",
			"outlet_category": "nonbank-otc",
			"payment_outlet_type": {
				"id": "nonbank-otc",
				"name": "Non-bank OTC",
				"fields": [
					{
						"id": "recipient_full_name",
						"name": "Recipient's full name",
						"field_type": "",
						"help_text": "Please provide first name and surname (ex. John Santos)",
						"help_link": ""
					},
					{
						"id": "full_address",
						"name": "Recipient's Full Address / ID",
						"field_type": "",
						"help_text": "Name and address should exactly match recipient's valid ID",
						"help_link": ""
					}
				],
				"optional_fields": [],
				"remittance_fields": [],
				"fee_info": null,
				"payout_duration": null,
				"outlets": [],
				"outlet_names_subset": [],
				"is_express": false,
				"is_commodity_outlet": false
			},
			"amount_limits": [
				{
					"currency": "PHP",
					"minimum": 0,
					"maximum": 100000000,
					"increment": 0.00001
				}
			],
			"denominations": [],
			"name": "Over the counter deposit (via Dragonpay)",
			"region": "PH",
			"verification_level_requirement": 0,
			"help_text": "Pay through any of Dragonpay's 4,000+ partner over-the-counter payment outlets (including SM Bills Payment Center, Cebuana Lhuillier, LBC Express and other accredited Bayad Centers). NOTE: If payment is made after the 90-minute payment window, rate will be based on the time payment is confirmed.",
			"help_link": "https://coinsph.zendesk.com/hc/en-us/articles/202901544-How-do-I-pay-for-bitcoin-through-an-SM-Bills-Payment-Center-",
			"disabled_notice": "",
			"disclaimer_notice": "",
			"logo_url": "",
			"instructions": "<p style=\"color:red\"><strong>Important!</strong></p>\r\n<ul style=\"color:red\">\r\n<li>Please note that DragonPay may take up to 24 hours to confirm you payment from the time you have paid.</li><br />\r\n<li>Final Bitcoin amount for DragonPay orders is computed based on the rate at the time DragonPay confirms your payment.</li></ul>\r\n<p>\r\n Please follow DragonPay's instruction for completing payment.</p>\r\n<a class=\"btn btn-warning pull-right\" href=\"https://gw.dragonpay.ph/Validate.aspx\">Check Dragonpay Status</a><br /><br /><br />\r\n<p>\r\n Your coins.ph order will be automatically marked as paid once DragonPay confirms your payment.</p>",
			"payout_duration": null,
			"is_express": false,
			"is_custom_denomination_allowed": true,
			"is_email_required": false,
			"importance": null
		}
	],
	"meta": {
		"total_count": 68,
		"next_page": 2,
		"previous_page": null
	}
}
```