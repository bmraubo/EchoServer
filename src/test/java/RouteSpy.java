public class RouteSpy implements RoutingInterface{
    boolean endpointReached;

    @Override
    public Response prepareResponse(Request request) {
        endpointReached = true;
        ResponseBuilder responseBuilder = new ResponseBuilder();
        Response response = new Response(responseBuilder);
        responseBuilder.setStatusCode(200);
        return response;
    }
}
