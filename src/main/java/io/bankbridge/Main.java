package io.bankbridge;
import io.bankbridge.handler.BanksCacheBased;
import io.bankbridge.handler.BanksRemoteCalls;
import spark.Service;

public class Main {
    
        // We need this to be able to run multiple servers
        private static Service http = null;
    
	public static void main(String[] args) throws Exception {
	        http = Service.ignite();
		http.port(8080);
		BanksCacheBased.init();
		BanksRemoteCalls.init();
		
		http.get("/v1/banks/all", (request, response) -> BanksCacheBased.handle(request, response));
		http.get("/v2/banks/all", (request, response) -> BanksRemoteCalls.handle(request, response));
	}

    // We need this to make sure the service is stopped at the end of each test
    public static void stop() {
        if (http != null) {
            http.stop();
        }
    }
}