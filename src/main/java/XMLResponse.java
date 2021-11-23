public class XMLResponse implements RoutingInterface{
    String contentType = "application/xml;charset=utf-8";
    String responseBody = "<note><body>XML Response</body></note>";

    @Override
    public Response prepareResponse() {
        Response response = new Response();
        response.setStatusCode(200);
        response.setContentType(contentType);
        response.addResponseBody(responseBody);
        return response;
    }

}
