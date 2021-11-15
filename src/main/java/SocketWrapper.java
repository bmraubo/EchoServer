import java.io.IOException;

public interface SocketWrapper {

    void createSocket(int port) throws IOException;
    String readRequestData() throws IOException;
    void sendResponseData(String responseData);
    void closeSocket();

}
