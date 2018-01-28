package binance;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

import com.binance.api.client.BinanceApiAsyncRestClient;
import com.binance.api.client.BinanceApiRestClient;
import com.binance.api.client.domain.TimeInForce;
import com.binance.api.client.domain.account.Account;
import com.binance.api.client.domain.account.AssetBalance;
import com.binance.api.client.domain.account.NewOrder;
import com.binance.api.client.domain.account.NewOrderResponse;
import com.binance.api.client.domain.general.ExchangeInfo;
import com.binance.api.client.domain.general.SymbolFilter;
import com.binance.api.client.domain.general.SymbolInfo;
import com.binance.api.client.domain.general.FilterType;
import com.binance.api.client.domain.market.OrderBook;
import com.binance.api.client.domain.market.OrderBookEntry;
import com.binance.api.client.domain.market.TickerStatistics;
import com.binance.api.client.exception.*;
import com.google.gson.JsonObject;

import configuration.Settings;

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
}
