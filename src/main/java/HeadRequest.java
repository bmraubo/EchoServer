public class HeadRequest implements RoutingInterface{
    String[] allowedMethods = {"HEAD", "OPTIONS"};

    @Override
    public Response prepareResponse(Request request) {
        if (request.method.equals("HEAD")) {
            return headRequest();
        } else {
            return MethodNotAllowed.prepareResponse(allowedMethods);
        }
    }

    private Response headRequest() {
        ResponseBuilder responseBuilder = new ResponseBuilder();
        Response response = new Response(responseBuilder);
        System.out.println("Simple Head Identified");
        responseBuilder.setStatusCode(200);
        responseBuilder.setResponseBody("");
        return response;
    }
}
