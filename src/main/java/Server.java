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
            Response response = routeConnection(request);
            String responseString = response.generateResponseString();
            socketWrapper.sendResponseData(responseString);
            socketWrapper.closeSocket();
        }
    }

    private Response routeConnection(Request request) {
        Response response = new Response();
        switch (request.uri) {
            case ("/simple_get"):
                return SimpleGet.prepareResponse(request);
            case ("/simple_get_with_body"):
                System.out.println("Simple Get with Body Identified");
                response.setStatusCode(200);
                response.addResponseBody(request.body);
                break;
            case ("/echo_body"):
                System.out.println("Simple Post identified");
                if (request.method == "POST") {
                    response.setStatusCode(200);
                    response.addResponseBody(request.body);
                }
                break;
            default:
                response.setStatusCode(404);
                break;
        }
        return response;
    }
}
