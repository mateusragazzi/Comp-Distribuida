package com;

import com.adapter.rest.Response;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Objects;

public class RequestProcessor implements Runnable {
    private final Socket clientSocket;

    public RequestProcessor(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    /**
     * Função responsável por ler a requisição e escrever a resposta para o cliente.
     */
    @Override
    public void run() {
        OutputStream out = null;
        BufferedReader in;
        if (clientSocket.isConnected()) {
            try {
                out = clientSocket.getOutputStream();
                in = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream()));
                String inputLine = "";
                String outputLine = "";
                StringBuilder request = new StringBuilder();
                StringBuilder body = new StringBuilder();
                Http http = new Http();

                clientSocket.setSoTimeout(10 * 1000);

                while (!"".equals((inputLine = in.readLine())))
                    request.append(inputLine).append("\r\n");

                while (in.ready() && ((inputLine = in.readLine()) != null))
                    body.append(inputLine).append("\r\n");

                Response response = http.processRequest(request.toString(), body.toString());
                outputLine = response.toString();
                out.write(outputLine.getBytes());
            } catch (IOException | NullPointerException e) {
                e.printStackTrace();
            } finally {
                if (out != null) {
                    try {
                        out.close();
                        clientSocket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
