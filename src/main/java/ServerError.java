public class ServerError implements RoutingInterface{
    String errorReason;

    public ServerError(String errorReason) {
        this.errorReason = errorReason;
    }

    @Override
    public Response prepareResponse(Request request) {
        TextResponseBuilder responseBuilder = new TextResponseBuilder();
        Response response = new Response(responseBuilder);
        responseBuilder.setStatusCode(500);
        responseBuilder.setResponseBody(errorReason);
        return response;
    }
}
