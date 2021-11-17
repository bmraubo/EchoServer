public class ResourceNotFound {

    public static Response prepareResponse(Request request) {
        Response response = new Response();
        System.out.println("Resource not Found");
        response.setStatusCode(404);
        return response;
    }
}
