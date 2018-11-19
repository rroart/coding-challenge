package io.bankbridge;
import static spark.Spark.get;
import static spark.Spark.port;

import spark.Service;

public class MockRemotes {

    public static void main(String[] args) throws Exception {
        Service http = Service.ignite();

        http.port(1234);

        http.get("/rbb", (request, response) -> "{\n" + 
                "\"bic\":\"1234\",\n" + 
                "\"name\":\"Royal Bank of Boredom\",\n" +
                "\"countryCode\":\"GB\",\n" + 
                "\"auth\":\"OAUTH\"\n" + 
                "}");
        http.get("/cs", (request, response) -> "{\n" + 
                "\"bic\":\"5678\",\n" + 
                "\"name\":\"Credit Sweets\",\n" +
                "\"countryCode\":\"CH\",\n" + 
                "\"auth\":\"OpenID\"\n" + 
                "}");
        http.get("/bes", (request, response) -> "{\n" + 
                "\"bic\":\"9870\",\n" + 
                "\"name\":\"Banco de espiritu santo\",\n" + 
                "\"countryCode\":\"PT\",\n" + 
                "\"auth\":\"SSL\"\n" + 
                "}");
    }
}