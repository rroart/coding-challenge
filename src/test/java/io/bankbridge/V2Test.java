package io.bankbridge;
import static org.junit.Assert.assertEquals;

import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.bankbridge.handler.BanksRemoteCalls;

public class V2Test extends CommonTest {
    
    @Before
    public void setup() throws Exception {
        BanksRemoteCalls.init();
        Main.main(null);
        MockRemotes.main(null);
    }
    
    @Test
    public void v2Test() throws Exception {
        commonTest("http://localhost:8080/v2/banks/all");
if (true) return;
        Client client = ClientBuilder.newClient();
        Response response = client.target("http://localhost:8080/v2/banks/all")
                .request(MediaType.APPLICATION_JSON)
                .get();
        assertEquals(200, response.getStatus());
        String entity = response.readEntity(String.class);
        //assertEquals("[{\"name\":\"Credit Sweets\",\"id\":\"5678\"},{\"name\":\"Banco de espiritu santo\",\"id\":\"9870\"},{\"name\":\"Royal Bank of Boredom\",\"id\":\"1234\"}]", entity);
        System.out.println(entity);
        ObjectMapper objectMapper = new ObjectMapper();
        Map banks = objectMapper.readValue(entity, Map.class);
        assertEquals(3, banks.size());
        assertEquals("Royal Bank of Boredom", banks.get("1234"));
        assertEquals("Credit Sweets", banks.get("5678"));
        assertEquals("Banco de espiritu santo", banks.get("9870"));
    }
    
    @After
    public void shutdown() throws InterruptedException {
        Main.stop();
    }
}
