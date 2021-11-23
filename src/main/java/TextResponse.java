public class TextResponse implements RoutingInterface{
    String contentType = "text/plain;charset=utf-8";
    String responseBody = "text response";

    @Override
    public Response prepareResponse() {
        Response response = new Response();
        response.setStatusCode(200);
        response.setContentType(contentType);
        response.addResponseBody(responseBody);
        return response;
    }
}
