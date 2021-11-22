public class ServerError implements RoutingInterface{

    @Override
    public Response prepareResponse() {
        Response response = new Response();
        response.setStatusCode(500);
        return response;
    }
}
