public class HeadRequest implements RoutingInterface{
    String[] allowedMethods = {"HEAD", "OPTIONS"};
    String method;
    String body;

    public HeadRequest(String method, String body) {
        this.method = method;
        this.body = body;
    }
    @Override
    public Response prepareResponse() {
        if (method.equals("HEAD")) {
            return headRequest();
        } else {
            return MethodNotAllowed.prepareResponse(allowedMethods);
        }
    }

    private Response headRequest() {
        Response response = new Response();
        System.out.println("Simple Head Identified");
        response.setStatusCode(200);
        response.addResponseBody("");
        return response;
    }
}
