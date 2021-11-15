import java.io.IOException;

public class Server {
    SocketWrapper socketWrapper;

    public Server(SocketWrapper socketWrapper) {
        this.socketWrapper = socketWrapper;
    }

    public void start(int port) throws IOException {
        socketWrapper.createSocket(port);
        String requestData = socketWrapper.readRequestData();
        Request request = new Request(requestData);
        Response response = routeConnection(request);
        // What do
        String responseString = response.generateResponseString();
        // Generate Response String
        socketWrapper.sendResponseData(responseString);
        // Send Response
    }

    private Response routeConnection(Request request) {
        Response response = new Response();
        switch (request.uri) {
            case ("/simple_get"):
                response.setStatusCode(200);
                break;
            default:
                response.setStatusCode(404);
                break;
        }
        return response;
    }
}
