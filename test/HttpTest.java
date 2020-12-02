import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import src.Http;
import src.adapter.rest.Request;

import java.net.MalformedURLException;

public class HttpTest {
    private final Http HTTP = new Http(8080);

    public HttpTest() throws MalformedURLException {
    }

    @Test
    public void shouldParseARequestWithHttpBody() {
        String requestData = "{ movie: { id: 1 } }";
        Request httpRequest = new HttpRequestBuilder().withMethod("POST").withBody(requestData).build();

        String response = HTTP.processRequest(httpRequest.toString());

        Assertions.assertFalse(response.isEmpty());
    }
}
