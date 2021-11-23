public class HTMLResponse implements RoutingInterface{
    String contentType = "text/html;charset=utf-8";
    String responseBody = "<html><body><p>HTML Response</p></body></html>";

    @Override
    public Response prepareResponse() {
        Response response = new Response();
        response.setStatusCode(200);
        response.setContentType(contentType);
        response.addResponseBody(responseBody);
        return response;
    }
}
