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
        Response response = new Response();
        response.setStatusCode(200);
        response.addResponseBody(body);
        return response;
    }
}
