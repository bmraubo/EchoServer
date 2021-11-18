public class MethodOptions2 {
    String[] allowedMethods = {"GET", "HEAD", "OPTIONS", "PUT", "POST"};

    public Response prepareResponse() {
        Response response = new Response();
        System.out.println("Simple Options to Method Options Identified");
        response.setStatusCode(200);
        response.setAllowHeader(allowedMethods);
        response.addResponseBody("");
        return response;
    }
}
