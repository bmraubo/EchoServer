public class XMLResponse implements RoutingInterface{
    String contentType = "application/xml;charset=utf-8";

    @Override
    public Response prepareResponse() {
        Response response = new Response();
        response.setStatusCode(200);
        response.setContentType(contentType);
        return response;
    }
}
