package src;

import src.adapter.rest.controller.Controller;

public class MethodExecutor {
    Controller controller;
    public MethodExecutor(Controller controller, String method) {
        this.controller = controller;
    }


}
