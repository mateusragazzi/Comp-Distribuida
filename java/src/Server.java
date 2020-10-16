package src;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException{
        int portNumber = Integer.parseInt(args[0]);
        ServerSocket serverSocket = startServer(portNumber);
        while(true) {
            Socket clientSocket = serverSocket.accept();
            Thread t = new Thread(new RequestProcessor(clientSocket, portNumber));
            t.start();
        }
    }

    private static ServerSocket startServer(int portNumber) throws IOException {
        ServerSocket serverSocket = new ServerSocket(portNumber);
        String serverAddr = serverSocket.getInetAddress().getHostAddress();
        System.out.println("Listening at http://localhost:" + portNumber);
        return serverSocket;
    }
}