public class MethodOptions2 {
    static String[] allowedMethods = {"GET", "HEAD", "OPTIONS", "PUT", "POST"};

    public static Response prepareResponse(Request request) {
        Response response = new Response();
        System.out.println("Simple Options to Method Options Identified");
        response.setStatusCode(200);
        response.setAllowHeader(allowedMethods);
        response.addResponseBody("");
        return response;
    }
}
