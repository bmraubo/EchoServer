import java.io.IOException;

public class Server {
    SocketWrapper socketWrapper;

    public Server(SocketWrapper socketWrapper) {
        this.socketWrapper = socketWrapper;
    }

    public void start(int port) throws IOException {
        socketWrapper.createSocket(port);
        while (socketWrapper.keepAlive()) {
            socketWrapper.acceptConnection();
            String requestData = socketWrapper.readRequestData();
            Request request = new Request(requestData);
            Response response = RoutingInterface.routeConnection(request);
            String responseString = response.generateResponseString();
            socketWrapper.sendResponseData(responseString);
            socketWrapper.closeSocket();
        }
    }

}
