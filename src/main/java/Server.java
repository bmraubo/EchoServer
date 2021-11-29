import java.io.IOException;

public class Server {
    SocketWrapper socketWrapper = new ServerSocketWrapper();
    Router router;

    public Server(Router router) {
        this.router = router;
    }

    public void start(int port) throws IOException, InterruptedException{
        socketWrapper.createSocket(port);
        while (socketWrapper.keepAlive()) {
            Response response;
            socketWrapper.acceptConnection();
            String requestData = socketWrapper.readRequestData();
            if (validateRequestData(requestData)) {
                response = processRequest(requestData);
            } else {
                response = raiseServerError();
            }
            socketWrapper.sendResponseData(response);
            socketWrapper.closeSocket();
        }
    }

    public void setSocketWrapper(SocketWrapper socketWrapper) {
        this.socketWrapper = socketWrapper;
    }

    private boolean validateRequestData(String requestData) {
        return requestData.length() != 0;
    }

    private Response raiseServerError() {
        String errorReason = "Request read as empty, please try again.";
        return Router.serverError(errorReason);
    }

    private Response processRequest(String requestData) {
        RequestBuilder requestBuilder = new RequestBuilder();
        Request request = new Request(requestBuilder);
        request.parseRequest(requestData);
        return router.connect(request);
    }

}
