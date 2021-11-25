public class ResourceNotFound {

    public static Response prepareResponse(Request request) {
        TextResponseBuilder responseBuilder = new TextResponseBuilder();
        Response response = new Response(responseBuilder);
        System.out.println("Resource not Found");
        responseBuilder.setStatusCode(404);
        responseBuilder.setResponseBody("");
        return response;
    }
}
