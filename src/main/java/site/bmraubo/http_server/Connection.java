package site.bmraubo.http_server;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Connection implements ConnectionWrapper{
    Socket socket;
    BufferedReader input;
    PrintWriter output;

    @Override
    public void openIOStreams() {

    }

    @Override
    public void buildRequest() {

    }

    @Override
    public void processRequest() {

    }
}
