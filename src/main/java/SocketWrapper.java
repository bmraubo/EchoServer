public interface SocketWrapper {

    void createSocket(int port);
    String readRequestData();
    void sendResponseData(String responseData);
    void closeSocket();

}
