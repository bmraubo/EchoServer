public class SimpleGetWithBody implements RoutingInterface{
    String[] allowedMethods = {"GET", "OPTIONS"};
    String method;

    public SimpleGetWithBody (String method){
        this.method = method;
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
