public class SimpleGetWithBody implements RoutingInterface{
    String[] allowedMethods = {"GET", "OPTIONS"};

    @Override
    public Response prepareResponse(Request request) {
        if (request.method.equals("GET")) {
            return simpleGetWithBody();
        } else {
            return MethodNotAllowed.prepareResponse(allowedMethods);
        }
    }

    private Response simpleGetWithBody() {
        ResponseBuilder responseBuilder = new ResponseBuilder();
        Response response = new Response(responseBuilder);
        System.out.println("Simple Get with Body Identified");
        responseBuilder.setStatusCode(200);
        responseBuilder.setResponseBody("Hello world");
        return response;
    }
}
