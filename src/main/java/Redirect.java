public class Redirect implements RoutingInterface{
    String[] allowedMethods = {"GET"};
    String contentLocation = "http://127.0.0.1:5000/simple_get";

    @Override
    public Response prepareResponse(Request request) {
        TextResponseBuilder responseBuilder = new TextResponseBuilder();
        Response response = new Response(responseBuilder);
        System.out.println("Redirecting");
        responseBuilder.setStatusCode(301);
        responseBuilder.setHeader("Location", contentLocation);
        responseBuilder.setResponseBody("");
        return response;
    }
}
