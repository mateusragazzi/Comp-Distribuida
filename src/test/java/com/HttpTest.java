package com;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.adapter.rest.Request;
import com.adapter.rest.Response;

public class HttpTest {
    private final Http HTTP = new Http();

    @Test
    public void shouldParseARequestWithHttpBody() {
        String requestData = "{actors:{id:1}}";
        String path = "movies";
        Request httpRequest = new RequestBuilder().withPath(path).withMethod("POST").withBody(requestData).build();

        Response response = HTTP.processRequest(httpRequest.toString(), "");

        Assertions.assertNotNull(response);
    }
}
