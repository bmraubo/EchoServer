public class SimplePost {

    public static Response prepareResponse(Request request) {
        System.out.println("Simple Post identified");
        Response response = new Response();
        response.setStatusCode(200);
        response.addResponseBody(request.body);
        return response;
    }
}
