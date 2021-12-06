package site.bmraubo.http_server;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketWrapper implements SocketWrapper{
    boolean keepAlive = true;
    ServerSocket serverSocket;
    Socket socket;
    BufferedReader input;
    PrintWriter output;



    @Override
    public void createSocket(int port) throws IOException {
        serverSocket = new ServerSocket(port, 1, InetAddress.getByName("0.0.0.0"));
    }

    @Override
    public boolean acceptConnection() throws IOException, InterruptedException {
        socket = serverSocket.accept();
        System.out.println("Connection accepted");
        boolean dataReceived = waitForData();
        if (dataReceived) {
            openIOStreams();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String readRequestData() throws IOException {
        System.out.println("looking at data");
        String data = "";
        int value;
        while ((input.ready())) {
            data = data + Character.toString(input.read());
        }
        System.out.println("Data read");
        System.out.println(data);
        return data;
    }

    @Override
    public void sendResponseData(Response response) throws IOException {
        response.generateResponse();
        System.out.println("Sending Response...");
        output.print(response.responseLine);
        output.print(response.responseHeaders);
        output.flush();
        OutputStream output = socket.getOutputStream();
        output.write(response.responseBody);
        output.flush();
    }

    @Override
    public void closeSocket() throws IOException {
        System.out.println("Closing IO Streams and Socket");
        input.close();
        output.close();
        socket.close();
    }

    @Override
    public boolean keepAlive() {
        return keepAlive;
    }

    @Override
    public BufferedReader getInput() {
        return input;
    }

    private boolean waitForData() throws IOException, InterruptedException {
        int timeoutCounter = 0;
        while ((socket.getInputStream().available() == 0) && (timeoutCounter < 1000)) {
            System.out.println("Input Stream not available - Waiting...");
            Thread.sleep(5);
            timeoutCounter++;
        }
        if (socket.getInputStream().available() == 0) {
            return false;
        } else {
            return true;
        }
    }

    private void openIOStreams() throws IOException {
        InputStreamReader inputStream = new InputStreamReader(socket.getInputStream());
        input = new BufferedReader(inputStream);
        output = new PrintWriter(socket.getOutputStream(), true);
        System.out.println("I/O Streams opened");
    }
}
