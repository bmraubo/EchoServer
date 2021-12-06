package site.bmraubo.http_server;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ConnectionSpy implements ConnectionWrapper{
    Router router;
    Socket socket;
    BufferedReader input;
    PrintWriter output;
    Request request;
    Response response;

    // Spy attributes
    boolean openedIOStreams;
    boolean requestBuilt;
    boolean connectionRouted;

    public ConnectionSpy(BufferedReader input, PrintWriter output, Router router) {
        this.input = input;
        this.output = output;
        this.router = router;
    }

    @Override
    public void processRequest() {
        openIOStreams();
        buildRequest();
        routeConnection();
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

    @Override
    public void routeConnection() {
        response = router.connect(request);
        connectionRouted = true;
    }
}
