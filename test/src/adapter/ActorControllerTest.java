package src.adapter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import src.RequestBuilder;
import src.adapter.rest.ActorController;
import src.adapter.rest.Request;
import src.domain.response.Response;

public class ActorControllerTest {
    ActorController controller = new ActorController();

    @Test
    void shouldGetAllActors() {
        Request request = new RequestBuilder().withPath("actors").withMethod("get").build();

        Response response = controller.getAll(request);

        Assertions.assertNotNull(response);
    }
}
