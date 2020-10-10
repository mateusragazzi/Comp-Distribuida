package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        int portNumber = Integer.parseInt(args[0]);

        ServerSocket serverSocket = startServer(portNumber);
        Socket clientSocket = serverSocket.accept();
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
    }

    private static ServerSocket startServer(int portNumber) throws IOException {
        ServerSocket serverSocket = new ServerSocket(portNumber);
        String serverAddr = serverSocket.getInetAddress().getHostAddress();
        System.out.println("Listening at http://localhost:" + portNumber);
        return serverSocket;
    }
}