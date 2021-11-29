import java.io.IOException;

public class Server {
    SocketWrapper socketWrapper;
    Router router;

    public Server(SocketWrapper socketWrapper, Router router) {
        this.socketWrapper = socketWrapper;
        this.router = router;
    }

    public void start(int port) throws IOException, InterruptedException{
        socketWrapper.createSocket(port);
        while (socketWrapper.keepAlive()) {
            socketWrapper.acceptConnection();
            String requestData = socketWrapper.readRequestData();
            if (requestData.equals("")) {
                RequestBuilder requestBuilder = new RequestBuilder();
                Request request = new Request(requestBuilder);
                String errorReason = "Request read as empty, please try again.";
                Response response = Router.serverError(errorReason, request);
                socketWrapper.sendResponseData(response);
                socketWrapper.closeSocket();
            } else {
                RequestBuilder requestBuilder = new RequestBuilder();
                Request request = new Request(requestBuilder);
                request.parseRequest(requestData);
                Response response = router.connect(request);
                socketWrapper.sendResponseData(response);
                socketWrapper.closeSocket();
            }
        }
    }

}
