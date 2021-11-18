public class MethodOptions {
    static String[] allowedMethods = {"GET", "HEAD", "OPTIONS"};

    public static Response prepareResponse(Request request) {
        Response response = new Response();
        System.out.println("Simple Options to Method Options Identified");
        response.setStatusCode(200);
        response.setAllowHeader(allowedMethods);
        response.addResponseBody("");
        return response;
    }
}
