public class ServerError implements ErrorHandler{
    String errorReason;

    public ServerError(String errorReason) {
        this.errorReason = errorReason;
    }

    @Override
    public Response prepareResponse() {
        ResponseBuilder responseBuilder = new ResponseBuilder();
        Response response = new Response(responseBuilder);
        responseBuilder.setStatusCode(400);
        responseBuilder.setResponseBody(errorReason);
        return response;
    }
}
