public class HealthCheck implements RoutingInterface{
    String contentType = "text/html;charset=utf-8";
    String responseBody = "<html><body><p><strong>Status:</strong> pass</p></body></html>";

    @Override
    public Response prepareResponse(Request request) {
        TextResponseBuilder responseBuilder = new TextResponseBuilder();
        Response response = new Response(responseBuilder);
        responseBuilder.setStatusCode(200);
        responseBuilder.setHeader("Content-Type", contentType);
        responseBuilder.setResponseBody(responseBody);
        return response;
    }
}
