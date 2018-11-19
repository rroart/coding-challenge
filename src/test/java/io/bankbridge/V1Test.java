package io.bankbridge;
import static org.junit.Assert.assertEquals;

import java.util.Collections;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class V1Test extends CommonTest {
    @Before
    public void setup() throws Exception {
        Main.main(null);
    }
    
    @Test
    public void v1Test() throws Exception {
        commonTest("http://localhost:8080/v1/banks/all");
    }
    
    @After
    public void shutdown() throws InterruptedException {
        Main.stop();
    }
    
}
