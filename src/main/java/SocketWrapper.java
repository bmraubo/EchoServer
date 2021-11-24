import java.io.IOException;

public interface SocketWrapper {

    void createSocket(int port) throws IOException;
    void acceptConnection() throws IOException, InterruptedException;
    String readRequestData() throws IOException;
    void sendResponseData(Response response) throws IOException;
    void sendImageResponseData(Response response) throws IOException;
    void sendTextResponseData(Response response);
    void closeSocket() throws IOException;
    boolean keepAlive();

}
