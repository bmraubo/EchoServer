public class HeadRequest {
    static String[] allowedMethods = {"HEAD", "OPTIONS"};

    public static Response prepareResponse(Request request) {
        if (request.method.equals("HEAD")) {
            return headRequest(request);
        } else {
            return MethodNotAllowed.prepareResponse(allowedMethods);
        }
    }

    private static Response headRequest(Request request) {
        Response response = new Response();
        System.out.println("Simple Head Identified");
        response.setStatusCode(200);
        response.addResponseBody("");
        return response;
    }
}
