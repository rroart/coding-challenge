package io.bankbridge;

import static org.junit.Assert.assertEquals;

import java.util.Collections;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.bankbridge.model.ApiResult;

public class CommonTest {

    public void commonTest(String url) throws Exception {
        Client client = ClientBuilder.newClient();
        Response response = client.target(url)
                .request(MediaType.APPLICATION_JSON)
                .get();
        assertEquals(200, response.getStatus());
        String entity = response.readEntity(String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        List<ApiResult> banks = objectMapper.readValue(entity, new TypeReference<List<ApiResult>>() {});
        Collections.sort(banks, (bank1, bank2) -> bank1.getId().compareTo(bank2.getId()));
        assertEquals(3, banks.size());
        assertEquals("Royal Bank of Boredom", banks.get(0).getName());
        assertEquals("1234", banks.get(0).getId());
        assertEquals("Credit Sweets", banks.get(1).getName());
        assertEquals("5678", banks.get(1).getId());
        assertEquals("Banco de espiritu santo", banks.get(2).getName());
        assertEquals("9870", banks.get(2).getId());
    }        
}
