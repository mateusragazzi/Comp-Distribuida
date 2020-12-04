package src;

import src.adapter.rest.Response;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

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
        try {
            out = clientSocket.getOutputStream();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));
            String inputLine, outputLine;
            StringBuilder request = new StringBuilder();
            Http http = new Http();

            clientSocket.setSoTimeout(10 * 1000);

            while ((inputLine = in.readLine()) != null) {
                request.append(inputLine).append("\r\n");
                if (inputLine.isEmpty()) {
                    Response response = http.processRequest(request.toString());
                    outputLine = response.toString();
                    out.write(outputLine.getBytes());
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
