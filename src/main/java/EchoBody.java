public class EchoBody {

    public static Response prepareResponse(Request request) {
        if (request.method == "POST") {
            return simplePostRequest(request);
        }
        return null;
    }

    private static Response simplePostRequest(Request request) {
        System.out.println("Simple Post identified");
        Response response = new Response();
        response.setStatusCode(200);
        response.addResponseBody(request.body);
        return response;
    }
}
