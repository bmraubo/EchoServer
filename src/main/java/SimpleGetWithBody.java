public class SimpleGetWithBody {

    public static Response prepareResponse(Request request) {
        Response response = new Response();
        System.out.println("Simple Get with Body Identified");
        response.setStatusCode(200);
        response.addResponseBody(request.body);
        return response;
    }
}
