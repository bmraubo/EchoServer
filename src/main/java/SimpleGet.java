public class SimpleGet implements RoutingInterface{
    String[] allowedMethods = {"GET", "HEAD", "OPTIONS"};
    String method;
    String body;

    public SimpleGet(String method, String body) {
        this.method = method;
        this.body = body;
    }

    @Override
    public Response prepareResponse() {
        if (method.equals("GET")) {
            return simpleGet();
        } else if (method.equals("HEAD")) {
            return simpleHead();
        } else {
            return MethodNotAllowed.prepareResponse(allowedMethods);
        }
    }

    private Response simpleGet() {
        Response response = new Response();
        System.out.println("Simple Get Identified");
        response.setStatusCode(200);
        response.addResponseBody("");
        return response;
    }

    private Response simpleHead() {
        Response response = new Response();
        System.out.println("Simple Head Identified");
        response.setStatusCode(200);
        response.addResponseBody("");
        return response;
    }
}
