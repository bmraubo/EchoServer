public class HTMLResponse implements Endpoint {
    String contentType = "text/html;charset=utf-8";
    String responseBody = "<html><body><p>HTML Response</p></body></html>";

    @Override
    public Response prepareResponse(Request request) {
        ResponseBuilder responseBuilder = new ResponseBuilder();
        Response response = new Response(responseBuilder);
        responseBuilder.setStatusCode(200);
        responseBuilder.setHeader("Content-Type", contentType);
        responseBuilder.setResponseBody(responseBody);
        return response;
    }
}
