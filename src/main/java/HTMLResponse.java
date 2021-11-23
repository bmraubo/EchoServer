public class HTMLResponse implements RoutingInterface{
    String contentType = "text/html";
    String responseBody = "<!DOCTYPE html><html>html response</html>";

    @Override
    public Response prepareResponse() {
        Response response = new Response();
        response.setStatusCode(200);
        response.setContentType(contentType);
        response.addResponseBody(responseBody);
        return response;
    }
}
