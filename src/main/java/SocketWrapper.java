import java.io.IOException;

public interface SocketWrapper {

    void createSocket(int port) throws IOException;
    void acceptConnection() throws IOException, InterruptedException;
    String readRequestData() throws IOException;
    void sendResponseData(Response response);
    void closeSocket() throws IOException;
    boolean keepAlive();

}
