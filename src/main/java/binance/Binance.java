package binance;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

import com.binance.api.client.BinanceApiRestClient;
import com.binance.api.client.domain.account.Account;
import com.binance.api.client.domain.account.AssetBalance;
import com.binance.api.client.domain.market.TickerStatistics;
import com.binance.api.client.exception.*;
import configuration.Settings;

public class Binance {
	public void view_funds() throws Exception {
		Settings settings = new Settings();
		BinanceApiRestClient client =Settings.client;
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
		System.out.println(formatter.format(total_bitcoin) + "bitcoin");
		System.out.println(assets);
	}
	
	public void trade_history() throws Exception {
		
	}
}
