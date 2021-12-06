package site.bmraubo.http_server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class SocketWrapperSpy implements SocketWrapper {
    boolean keepAlive = true;
    BufferedReader input;
    PrintWriter output;
    boolean socketCreated;
    public boolean connectionAccepted;
    public boolean dataSent;
    public boolean socketClosed;
    public String dataReceived;
    public String sentResponseLine;
    public String sentResponseHeaders;
    public byte[] sentResponseBody;
    public boolean nullInputReceived;


    public SocketWrapperSpy(BufferedReader input, PrintWriter output) {
        this.input = input;
        this.output = output;
    }

    @Override
    public void createSocket(int port) {
        this.socketCreated = true;
    }

    @Override
    public boolean acceptConnection() throws IOException {
        System.out.println("Connection accepted");
        System.out.println("I/O Streams opened");
        this.connectionAccepted = true;
        return true;
    }


    @Override
    public String readRequestData() throws IOException {
        String data = "";
        int value;
        while (input.ready()) {
            data = data + Character.toString(input.read());
        }
        if (data.equals("")) {
            nullInputReceived = true;
            System.out.println("Null input received, closing connection...");
            return data;
        }
        dataReceived = data;
        System.out.println("Data received: "+ data);
        return data;
    }

    @Override
    public void sendResponseData(Response response) throws IOException {
        response.generateResponse();
        System.out.println("Sending Response...");
        sentResponseLine = response.responseLine;
        sentResponseHeaders = response.responseHeaders;
        sentResponseBody = response.responseBody;
        dataSent = true;
    }

    @Override
    public void closeSocket() throws IOException {
        System.out.println("Closing IO Streams and Socket");
        input.close();
        output.close();
        socketClosed = true;
        keepAlive = false;
    }

    @Override
    public boolean keepAlive() {
        return keepAlive;
    }

    @Override
    public BufferedReader getInput() {
        return input;
    }

    public boolean wasCreated() {
        return socketCreated;
    }
}
