public class HeadRequest implements RoutingInterface{
    String[] allowedMethods = {"HEAD", "OPTIONS"};
    String method;

    public HeadRequest(String method) {
        this.method = method;
    }
    @Override
    public Response prepareResponse() {
        if (method.equals("HEAD")) {
            return headRequest();
        } else {
            return MethodNotAllowed.prepareResponse(allowedMethods);
        }
    }

    private Response headRequest() {
        TextResponseBuilder responseBuilder = new TextResponseBuilder();
        Response response = new Response(responseBuilder);
        System.out.println("Simple Head Identified");
        responseBuilder.setStatusCode(200);
        responseBuilder.setResponseBody("");
        return response;
    }
}
