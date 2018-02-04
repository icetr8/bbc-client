package binance;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

import com.binance.api.client.BinanceApiAsyncRestClient;
import com.binance.api.client.BinanceApiRestClient;
import com.binance.api.client.BinanceApiWebSocketClient;
import com.binance.api.client.domain.TimeInForce;
import com.binance.api.client.domain.account.Account;
import com.binance.api.client.domain.account.AssetBalance;
import com.binance.api.client.domain.account.NewOrder;
import com.binance.api.client.domain.account.NewOrderResponse;
import com.binance.api.client.domain.event.AccountUpdateEvent;
import com.binance.api.client.domain.event.AggTradeEvent;
import com.binance.api.client.domain.event.OrderTradeUpdateEvent;
import com.binance.api.client.domain.event.UserDataUpdateEvent.UserDataUpdateEventType;
import com.binance.api.client.domain.general.ExchangeInfo;
import com.binance.api.client.domain.general.SymbolFilter;
import com.binance.api.client.domain.general.SymbolInfo;
import com.binance.api.client.domain.general.FilterType;
import com.binance.api.client.domain.market.CandlestickInterval;
import com.binance.api.client.domain.market.OrderBook;
import com.binance.api.client.domain.market.OrderBookEntry;
import com.binance.api.client.domain.market.TickerStatistics;
import com.binance.api.client.exception.*;
import com.google.gson.JsonObject;

import configuration.Settings;
import utils.Utils;

import static com.binance.api.client.domain.account.NewOrder.marketBuy;
import static com.binance.api.client.domain.account.NewOrder.limitBuy;

public class Binance {
	public JsonObject view_funds() throws Exception {
		Settings settings = new Settings();
		BinanceApiRestClient client =settings.client;
		Account account = client.getAccount();
		List<AssetBalance> balances = account.getBalances();
		String assets = "";
		float total_bitcoin = 0;
		for(AssetBalance assetbalance : balances) {
			double free = Float.parseFloat(assetbalance.getFree());
			String asset = assetbalance.getAsset();
			if ( free > 0) {
			assets+= assetbalance.getFree() + " " +  asset + "\n";

			try {
				TickerStatistics tickerStatistics = client.get24HrPriceStatistics(asset+"BTC");
				
				double last_price = Float.parseFloat(tickerStatistics.getLastPrice());
				
				double bitcoin_value = free * last_price;
				total_bitcoin += bitcoin_value;
			}catch (BinanceApiException e) {
			    
			}
			}
		}
		TickerStatistics tickerStatistics = client.get24HrPriceStatistics("BTCUSDT");
		double last_price = Float.parseFloat(tickerStatistics.getLastPrice());
		double to_round = total_bitcoin * last_price;
		double usdt_value = Math.round(to_round * 100.0) / 100.0;
		
		System.out.println(usdt_value);
		NumberFormat formatter = new DecimalFormat("#0.00000000");     
		String btc_value = formatter.format(total_bitcoin);
		JsonObject data = new JsonObject();
		data.addProperty("usdt_value", usdt_value);
		data.addProperty("btc_value", btc_value);
		data.addProperty("assets", assets);
		return data;
	}
	
	public void trade_history() throws Exception {
		
	}
	public void sell_market_order(String trade_pair, String quantity ) throws Exception {
		
		Settings settings = new Settings();
		 BinanceApiRestClient client = settings.client;
		boolean test = false; //settings.binance_test_order;
		
		if (test==true) {
			//test orders
			NewOrder order = marketBuy(trade_pair, quantity);
			client.newOrderTest(order);
			// insert balance is sufficient
		}
		else {
			//real orders
			ExchangeInfo exchangeInfo = client.getExchangeInfo();
		    SymbolInfo symbolInfo = exchangeInfo.getSymbolInfo(trade_pair);
			SymbolFilter priceFilter = symbolInfo.getSymbolFilter(FilterType.PRICE_FILTER);
			System.out.println(priceFilter.getMinPrice());
		    System.out.println(priceFilter.getTickSize());
		}
	}
	public void buy_market_order(String trade_pair, String quantity) throws Exception {
		Settings settings = new Settings();
		 BinanceApiRestClient client = settings.client;
		boolean test = false; //settings.binance_test_order;
		
		if (test==true) {
			//test orders
			
			ExchangeInfo exchangeInfo = client.getExchangeInfo();
		    SymbolInfo symbolInfo = exchangeInfo.getSymbolInfo(trade_pair);
			SymbolFilter priceFilter = symbolInfo.getSymbolFilter(FilterType.PRICE_FILTER);
			System.out.println(priceFilter.getMinPrice());
		    System.out.println(priceFilter.getTickSize());
		    
		    // amount > getminprice then proceed if not show min price error
			NewOrder order = marketBuy(trade_pair, quantity);
			client.newOrderTest(order);
			// insert balance is sufficient
		}
		else {
			//real orders
			ExchangeInfo exchangeInfo = client.getExchangeInfo();
		    SymbolInfo symbolInfo = exchangeInfo.getSymbolInfo(trade_pair);
			SymbolFilter priceFilter = symbolInfo.getSymbolFilter(FilterType.PRICE_FILTER);
			System.out.println(priceFilter.getMinPrice());
		    System.out.println(priceFilter.getTickSize());
		    
		    // amount > getminprice then proceed if not show min price error
		    
		}
	}
	
	public void user_data_stream() throws Exception {
		Settings settings = new Settings();
		BinanceApiRestClient client = settings.client;
		
	    String listenKey = client.startUserDataStream();

	    // Then, we open a new web socket client, and provide a callback that is called on every update
	    BinanceApiWebSocketClient webSocketClient = settings.factory.newWebSocketClient();

	    // Listen for changes in the account
	    webSocketClient.onUserDataUpdateEvent(listenKey, response -> {
	      if (response.getEventType() == UserDataUpdateEventType.ACCOUNT_UPDATE) {
	        AccountUpdateEvent accountUpdateEvent = response.getAccountUpdateEvent();
	        // Print new balances of every available asset
	        System.out.println(accountUpdateEvent.getBalances());
	      } else {
	        OrderTradeUpdateEvent orderTradeUpdateEvent = response.getOrderTradeUpdateEvent();
	        // Print details about an order/trade
	        System.out.println(orderTradeUpdateEvent);

	        // Print original quantity
	        System.out.println(orderTradeUpdateEvent.getOriginalQuantity());

	        // Or price
	        System.out.println(orderTradeUpdateEvent.getPrice());
	      }
	    });
	    System.out.println("Waiting for events...");
	}
	
	public void market_data_stream(String sender_psid, String symbol, double base_value, double target_value, double percentage, boolean greater) throws Exception {
		Settings settings = new Settings();
		BinanceApiWebSocketClient client = Settings.websocket_client;
		// double percentage = ((base_value / target_value) * 100) - base_value;
		// boolean greater = false;
		
		client.onCandlestickEvent("ethbtc", CandlestickInterval.ONE_MINUTE, response-> {
		if(response.getBarFinal()) {
			System.out.println(response);
			double close = Double.parseDouble(response.getClose()); 
			if (greater) {
				if (close > target_value) {
					System.out.println("greater");
					JsonObject data = new JsonObject();
					data.addProperty("symbol", symbol);
					data.addProperty("sender_psid", sender_psid);
					data.addProperty("percentage", percentage);
					data.addProperty("base_value", base_value);
					data.addProperty("target_value", target_value);
					try {
						ProcessBuilder processBuilder = new ProcessBuilder();
						if (processBuilder.environment().get("PORT") != null) {
							Utils.raw_post_request(Settings.HEROKU_URL+"/custom", data.toString());
						}else {
							Utils.raw_post_request(Settings.LOCAL_URL+"/custom", data.toString());
						}
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						System.out.println("ERERREREPR");
					}
				}
			}else {
			 	if (close < target_value) {
				  
			 	}
			}
		  }
		}
		);
	}
}
