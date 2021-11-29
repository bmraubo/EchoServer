public class EchoBody implements RoutingInterface{
    String[] allowedMethods = {"POST", "OPTIONS"};

    public Response prepareResponse(Request request) {
        if (request.method.equals("POST")) {
            return simplePostRequest(request);
        } else {
            return MethodNotAllowed.prepareResponse(allowedMethods);
        }
    }

    private Response simplePostRequest(Request request) {
        System.out.println("Simple Post identified");
        TextResponseBuilder responseBuilder = new TextResponseBuilder();
        Response response = new Response(responseBuilder);
        responseBuilder.setStatusCode(200);
        responseBuilder.setResponseBody(request.body);
        return response;
    }
}
