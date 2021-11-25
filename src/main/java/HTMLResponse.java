public class HTMLResponse implements RoutingInterface{
    String contentType = "text/html;charset=utf-8";
    String responseBody = "<html><body><p>HTML Response</p></body></html>";

    @Override
    public Response prepareResponse() {
        TextResponseBuilder responseBuilder = new TextResponseBuilder();
        Response response = new Response(responseBuilder);
        responseBuilder.setStatusCode(200);
        responseBuilder.setHeader("Content-Type", contentType);
        responseBuilder.setResponseBody(responseBody);
        return response;
    }
}
