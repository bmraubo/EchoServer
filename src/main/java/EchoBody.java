public class EchoBody implements RoutingInterface{
    String[] allowedMethods = {"POST", "OPTIONS"};
    String method;
    String body;

    public EchoBody(String method, String body) {
        this.method = method;
        this.body = body;
    }

    public Response prepareResponse() {
        if (method.equals("POST")) {
            return simplePostRequest();
        } else {
            return MethodNotAllowed.prepareResponse(allowedMethods);
        }
    }

    private Response simplePostRequest() {
        System.out.println("Simple Post identified");
        TextResponseBuilder responseBuilder = new TextResponseBuilder();
        Response response = new Response(responseBuilder);
        responseBuilder.setStatusCode(200);
        responseBuilder.setResponseBody(body);
        return response;
    }
}
