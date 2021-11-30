public class ResourceNotFound implements RoutingInterface{

    public Response prepareResponse(Request request) {
        ResponseBuilder responseBuilder = new ResponseBuilder();
        Response response = new Response(responseBuilder);
        System.out.println("Resource not Found");
        responseBuilder.setStatusCode(404);
        responseBuilder.setResponseBody("");
        return response;
    }
}
