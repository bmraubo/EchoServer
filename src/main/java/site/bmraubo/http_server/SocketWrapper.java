package site.bmraubo.http_server;

import java.io.BufferedReader;
import java.io.IOException;

public interface SocketWrapper {

    void createSocket(int port) throws IOException;
    boolean acceptConnection() throws IOException, InterruptedException;
    String readRequestData() throws IOException;
    void sendResponseData(Response response) throws IOException;
    void closeSocket() throws IOException;
    boolean keepAlive();
    BufferedReader getInput();

}
