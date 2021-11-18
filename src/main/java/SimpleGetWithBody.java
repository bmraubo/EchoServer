public class SimpleGetWithBody {
    static String[] allowedMethods = {"GET", "OPTIONS"};

    public static Response prepareResponse(Request request) {
        if (request.method.equals("GET")) {
            return simpleGetWithBody(request);
        } else {
            return MethodNotAllowed.prepareResponse(allowedMethods);
        }
    }

    private static Response simpleGetWithBody(Request request) {
        Response response = new Response();
        System.out.println("Simple Get with Body Identified");
        response.setStatusCode(200);
        response.addResponseBody("Hello world");
        return response;
    }
}
