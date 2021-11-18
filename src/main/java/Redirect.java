public class Redirect implements RoutingInterface{
    String[] allowedMethods = {"GET"};
    String contentLocation = "http://0.0.0.0:5000/simple_get";
    String method;

    public Redirect(String method) {
        this.method = method;
    }

    @Override
    public Response prepareResponse() {
        Response response = new Response();
        System.out.println("Redirecting");
        response.setStatusCode(301);
        response.setContentLocationHeader(contentLocation);
        response.addResponseBody("");
        return response;
    }
}
