import java.io.IOException;

public class Server {
    SocketWrapper socketWrapper;

    public Server(SocketWrapper socketWrapper) {
        this.socketWrapper = socketWrapper;
    }

    public void start(int port) throws IOException, InterruptedException{
        socketWrapper.createSocket(port);
        while (socketWrapper.keepAlive()) {
            socketWrapper.acceptConnection();
            String requestData = socketWrapper.readRequestData();
            if (requestData.equals("")) {
                String errorReason = "Request read as empty, please try again.";
                Response response = Route.serverError(errorReason);
                socketWrapper.sendResponseData(response);
                socketWrapper.closeSocket();
            } else {
                Request request = new Request(requestData);
                Response response = Route.routeConnection(request);
                socketWrapper.sendResponseData(response);
                socketWrapper.closeSocket();
            }
        }
    }

}
