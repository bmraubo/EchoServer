public class HeadRequest implements RoutingInterface{
    String[] allowedMethods = {"HEAD", "OPTIONS"};
    String method;

    public HeadRequest(String method) {
        this.method = method;
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
