public interface RoutingInterface {

    static Response routeConnection(Request request) {
        Response response = new Response();
        switch (request.uri) {
            case ("/simple_get"):
                return SimpleGet.prepareResponse(request);
            case ("/simple_get_with_body"):
                return SimpleGetWithBody.prepareResponse(request);
            case ("/echo_body"):
                return EchoBody.prepareResponse(request);
            default:
                response.setStatusCode(404);
                break;
        }
        return response;
    }
}
