public class MethodOptions implements RoutingInterface{
    String[] allowedMethods = {"GET", "HEAD", "OPTIONS"};

    @Override
    public Response prepareResponse() {
        Response response = new Response();
        System.out.println("Simple Options to Method Options Identified");
        response.setStatusCode(200);
        response.setAllowHeader(allowedMethods);
        response.addResponseBody("");
        return response;
    }
}
