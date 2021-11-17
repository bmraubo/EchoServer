public class SimpleGet {
    static String[] allowedMethods = {"GET", "HEAD", "OPTIONS"};

    public static Response prepareResponse(Request request) {
        if (request.method.equals("GET")) {
            return simpleGet(request);
        } else if (request.method.equals("HEAD")) {
            return simpleHead(request);
        } else {
            return null; // wrong method placeholder
        }
    }

    private static Response simpleGet(Request request) {
        Response response = new Response();
        System.out.println("Simple Get Identified");
        response.setStatusCode(200);
        return response;
    }

    private static Response simpleHead(Request request) {
        Response response = new Response();
        System.out.println("Simple Head Identified");
        response.setStatusCode(200);
        return response;
    }
}
