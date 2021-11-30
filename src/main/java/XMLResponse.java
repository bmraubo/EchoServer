public class XMLResponse implements RoutingInterface{
    String contentType = "application/xml;charset=utf-8";
    String responseBody = "<note><body>XML Response</body></note>";

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
