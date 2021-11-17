public class EchoBody {
    String[] allowedMethods = {"POST", "OPTIONS"};

    public static Response prepareResponse(Request request) {
        if (request.method.equals("POST")) {
            return simplePostRequest(request);
        } else {
            return null; // method not allowed
        }
    }

    private static Response simplePostRequest(Request request) {
        System.out.println("Simple Post identified");
        Response response = new Response();
        response.setStatusCode(200);
        response.addResponseBody(request.body);
        return response;
    }
}
