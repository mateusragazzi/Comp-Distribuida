package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class RequestProcessor implements Runnable {
    private final Socket clientSocket;
    private final Integer portNumber;

    public RequestProcessor(Socket clientSocket, Integer portNumber) {
        this.clientSocket = clientSocket;
        this.portNumber = portNumber;
    }

    @Override
    public void run() {
        try {
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String inputLine, outputLine;
            StringBuilder request = new StringBuilder();

            Http http = new Http(portNumber);

            while ((inputLine = in.readLine()) != null) {
                request.append(inputLine).append("\r\n");
                if (inputLine.isEmpty()) {
                    outputLine = http.processRequest(request.toString());
                    out.println(outputLine);
                    break;
                }
            }
            out.close();
            in.close();
            clientSocket.close();
        } catch (IOException exception) {
            System.err.println(exception.getMessage());
        }
    }
}
