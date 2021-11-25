public class SimpleGet implements RoutingInterface{
    String[] allowedMethods = {"GET", "HEAD", "OPTIONS"};
    String method;

    public SimpleGet(String method) {
        this.method = method;
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
        TextResponseBuilder responseBuilder = new TextResponseBuilder();
        Response response = new Response(responseBuilder);
        System.out.println("Simple Get Identified");
        responseBuilder.setStatusCode(200);
        responseBuilder.setResponseBody("");
        return response;
    }

    private Response simpleHead() {
        TextResponseBuilder responseBuilder = new TextResponseBuilder();
        Response response = new Response(responseBuilder);
        System.out.println("Simple Head Identified");
        responseBuilder.setStatusCode(200);
        responseBuilder.setResponseBody("");
        return response;
    }
}
