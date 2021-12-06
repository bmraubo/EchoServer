package site.bmraubo.http_server;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ConnectionSpy implements ConnectionWrapper{
    Socket socket;
    BufferedReader input;
    PrintWriter output;

    // Spy attributes
    boolean openedIOStreams;

    public ConnectionSpy(BufferedReader input, PrintWriter output) {
        this.input = input;
        this.output = output;
    }

    @Override
    public void processRequest() {
        openIOStreams();
    }

    @Override
    public void openIOStreams() {
        openedIOStreams = true;
    }
}
