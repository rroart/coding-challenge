package io.bankbridge.handler;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.bankbridge.model.ApiResult;
import io.bankbridge.model.BankModel;
import spark.Request;
import spark.Response;

public class BanksRemoteCalls {

    private static Map<String, String> config;

    public static Map<String, String> getConfig() {
        return config;
    }

    public static void setConfig(Map<String, String> config) {
        BanksRemoteCalls.config = config;
    }

    public static void init() throws Exception {
        config = new ObjectMapper()
                .readValue(Thread.currentThread().getContextClassLoader().getResource("banks-v2.json"), Map.class);
    }

    public static String handle(Request request, Response response) throws IOException {
        Client client = ClientBuilder.newClient();
        ObjectMapper objectMapper = new ObjectMapper();
        List<ApiResult> banks = new ArrayList<>();
        for (Entry<String, String> entry : config.entrySet()) {
            String url = entry.getValue();
            javax.ws.rs.core.Response httpResponse = client.target(url)
                    .request(MediaType.APPLICATION_JSON)
                    .get();
            String entity = httpResponse.readEntity(String.class);
            BankModel model = objectMapper.readValue(entity, BankModel.class);
            banks.add(new ApiResult(model.getBic(), model.getName()));
        }
        return objectMapper.writeValueAsString(banks);
    }

}
