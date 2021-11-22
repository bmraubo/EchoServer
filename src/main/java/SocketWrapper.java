import java.io.IOException;

public interface SocketWrapper {

    void createSocket(int port) throws IOException;
    void acceptConnection() throws IOException, InterruptedException;
    String readRequestData() throws IOException;
    void sendResponseData(String responseData);
    void closeSocket() throws IOException;
    boolean keepAlive();

}
