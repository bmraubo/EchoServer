public class XMLResponse implements RoutingInterface{
    @Override
    public Response prepareResponse() {
        Response response = new Response();
        response.setStatusCode(200);
        return response;
    }
}
