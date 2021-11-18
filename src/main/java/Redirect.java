public class Redirect implements RoutingInterface{
    String[] allowedMethods = {"GET"};
    String contentLocation = "http://127.0.0.1:5000/simple_get";
    String method;

    public Redirect(String method) {
        this.method = method;
    }

    @Override
    public Response prepareResponse() {
        Response response = new Response();
        System.out.println("Redirecting");
        response.setStatusCode(301);
        response.setLocationHeader(contentLocation);
        response.addResponseBody("");
        return response;
    }
}
