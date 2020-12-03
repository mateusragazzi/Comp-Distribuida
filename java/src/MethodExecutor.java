package src;

import src.adapter.rest.Controller;

import javax.naming.ldap.Control;

public class MethodExecutor {
    Controller controller;
    public MethodExecutor(Controller controller, String method) {
        this.controller = controller;
    }


}
