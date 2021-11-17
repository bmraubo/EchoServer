public class SimpleGetWithBody {
    String[] allowedMethods = {"GET", "OPTIONS"};

    public static Response prepareResponse(Request request) {
        if (request.method.equals("GET")) {
            return simpleGetWithBody(request);
        } else {
            return null; // method not allowed placeholder
        }
    }

    private static Response simpleGetWithBody(Request request) {
        Response response = new Response();
        System.out.println("Simple Get with Body Identified");
        response.setStatusCode(200);
        response.addResponseBody(request.body);
        return response;
    }
}
