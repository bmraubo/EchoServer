public class SimpleGetWithBody implements RoutingInterface{
    String[] allowedMethods = {"GET", "OPTIONS"};
    String method;
    String body;

    public SimpleGetWithBody (String method, String body){
        this.method = method;
        this.body = body;
    }

    @Override
    public Response prepareResponse() {
        if (method.equals("GET")) {
            return simpleGetWithBody();
        } else {
            return MethodNotAllowed.prepareResponse(allowedMethods);
        }
    }

    private Response simpleGetWithBody() {
        Response response = new Response();
        System.out.println("Simple Get with Body Identified");
        response.setStatusCode(200);
        response.addResponseBody("Hello world");
        return response;
    }
}
