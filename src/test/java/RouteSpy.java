public class RouteSpy implements RoutingInterface{
    boolean endpointReached;

    @Override
    public Response prepareResponse() {
        endpointReached = true;
        TextResponseBuilder textResponseBuilder = new TextResponseBuilder();
        Response response = new Response(textResponseBuilder);
        textResponseBuilder.setStatusCode(200);
        return response;
    }
}
