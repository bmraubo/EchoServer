package site.bmraubo.http_server;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ConnectionSpy implements ConnectionWrapper{
    Socket socket;
    BufferedReader input;
    PrintWriter output;
    Request request;

    // Spy attributes
    boolean openedIOStreams;
    boolean requestBuilt;

    public ConnectionSpy(BufferedReader input, PrintWriter output) {
        this.input = input;
        this.output = output;
    }

    @Override
    public void processRequest() {
        openIOStreams();
        buildRequest();
    }

    @Override
    public void openIOStreams() {
        openedIOStreams = true;
    }

    @Override
    public void buildRequest() {
        RequestBuilder requestBuilder = new RequestBuilder();
        request = new Request(requestBuilder);
        request.parseRequest(input);
        requestBuilt = true;
    }
}
