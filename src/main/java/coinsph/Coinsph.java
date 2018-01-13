package coinsph;

public class Coinsph {
	public static void check_balance() throws Exception{
		CoinsphSend send = new CoinsphSend();
		String body = "";
		String response_data = send.send_request("https://coins.ph/api/v3/crypto-accounts/", body);
		System.out.println(response_data);
	}
	public static void payin_outlets() throws Exception {
		CoinsphSend send = new CoinsphSend();
		String body = "";
		String response_data = send.send_request("https://coins.ph/d/api/payin-outlets/", body);
	}
}
