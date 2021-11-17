public class MethodNotAllowed {

    public static Response prepareResponse(String[] allowedMethods) {
        Response response = new Response();
        System.out.println("Method not allowed!");
        response.setStatusCode(405);
        response.setAllowHeader(allowedMethods);
        return response;
    }
}
