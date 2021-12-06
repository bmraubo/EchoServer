package site.bmraubo.http_server;

import java.io.BufferedReader;
import java.io.IOException;

public class Server {
    SocketWrapper socketWrapper = new ServerSocketWrapper();
    Router router;

    boolean acceptedConnection;

    public Server(Router router) {
        this.router = router;
    }

    public void start(int port) throws IOException, InterruptedException{
        socketWrapper.createSocket(port);
        while (socketWrapper.keepAlive()) {
            Response response;
            acceptedConnection = socketWrapper.acceptConnection();
            if (!acceptedConnection){
                response = raiseTimeoutError();
            } else {
                response = processRequest(socketWrapper.getInput());
            }
            socketWrapper.sendResponseData(response);
            socketWrapper.closeSocket();
        }
    }

    public void setSocketWrapper(SocketWrapper socketWrapper) {
        this.socketWrapper = socketWrapper;
    }

    private Response raiseTimeoutError() {
        TimeoutError timeoutError = new TimeoutError();
        return timeoutError.prepareResponse();
    }

    private Response processRequest(BufferedReader input) {
        RequestBuilder requestBuilder = new RequestBuilder();
        Request request = new Request(requestBuilder);
        request.parseRequest(input);
        return router.connect(request);
    }

}
