package io.bankbridge;
import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.bankbridge.model.BankModel;
import io.bankbridge.model.BankModelList;

public class NotFoundTest {
    @Before
    public void setup() throws Exception {
        Main.main(null);
    }
    
    @Test
    public void notFoundTest() throws Exception {
        Client client = ClientBuilder.newClient();
        Response response = client.target("http://localhost:8080/banks/all")
                .request(MediaType.APPLICATION_JSON)
                .get();
        assertEquals(404, response.getStatus());
    }
    
    @After
    public void shutdown() throws InterruptedException {
        Main.stop();
    }
}
