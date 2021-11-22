public class ServerError implements RoutingInterface{
    String errorReason;

    public ServerError(String errorReason) {
        this.errorReason = errorReason;
    }

    @Override
    public Response prepareResponse() {
        Response response = new Response();
        response.setStatusCode(500);
        response.addResponseBody(errorReason);
        return response;
    }
}
