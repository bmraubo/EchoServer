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
        TextResponseBuilder responseBuilder = new TextResponseBuilder();
        Response response = new Response(responseBuilder);
        System.out.println("Simple Get with Body Identified");
        responseBuilder.setStatusCode(200);
        responseBuilder.setResponseBody("Hello world");
        return response;
    }
}
