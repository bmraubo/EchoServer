public class SimpleHead {

    public static Response prepareResponse(Request request) {
        if (request.method.equals("HEAD")) {
            return simpleHead(request);
        } else {
            return null; // wrong method placeholder
        }
    }

    private static Response simpleHead(Request request) {
        Response response = new Response();
        System.out.println("Simple Head Identified");
        response.setStatusCode(200);
        return response;
    }
}
