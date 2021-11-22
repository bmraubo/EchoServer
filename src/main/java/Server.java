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
            if (requestData.equals("")) {
                Response response = Route.serverError();
                String responseString = response.generateResponseString();
                socketWrapper.sendResponseData(responseString);
                socketWrapper.closeSocket();
            } else {
                Request request = new Request(requestData);
                Response response = Route.routeConnection(request);
                String responseString = response.generateResponseString();
                socketWrapper.sendResponseData(responseString);
                socketWrapper.closeSocket();
            }
        }
    }

}
