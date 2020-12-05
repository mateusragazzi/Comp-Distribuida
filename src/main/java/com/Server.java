package com;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    /**
     * Função que inicializa o Server e cria as Threads para cada cliente.
     * Ela também invoca a classe RequestProcessor para processamento de um pedido do cliente.
     *
     * @param args parâmetro que contém, na primeira posição, a porta desejada.
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        int portNumber = Integer.parseInt(args[0]);
        ServerSocket serverSocket = startServer(portNumber);
        while (true) {
            Socket clientSocket = serverSocket.accept();
            Thread t = new Thread(new RequestProcessor(clientSocket));
            t.start();
        }
    }

    /**
     * Inicializa o socket com a porta informada.
     *
     * @param portNumber porta solicitada.
     * @return retorna um socket, caso ocorreu um sucesso.
     * @throws IOException
     */
    private static ServerSocket startServer(int portNumber) throws IOException {
        ServerSocket serverSocket = new ServerSocket(portNumber);
        String serverAddr = serverSocket.getInetAddress().getHostAddress();
        System.out.printf("Listening at http://%s:%d%n\n", serverAddr, portNumber);
        return serverSocket;
    }
}