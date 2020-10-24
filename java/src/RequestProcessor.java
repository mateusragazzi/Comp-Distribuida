package src;

import java.io.*;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URISyntaxException;

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
            OutputStream out = clientSocket.getOutputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String inputLine, outputLine;
            StringBuilder request = new StringBuilder();

            Http http = new Http(portNumber);

            while ((inputLine = in.readLine()) != null) {
                request.append(inputLine).append("\r\n");
                if (inputLine.isEmpty()) {
                    outputLine = http.processRequest(request.toString());
                    out.write(outputLine.getBytes());
                    break;
                }
            }
            out.close();
            in.close();
            clientSocket.close();
        }catch(IOException | URISyntaxException exception) {
            System.err.println(exception.getMessage());
        }
    }
}
