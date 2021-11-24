public class HealthCheck implements RoutingInterface{
    String contentType = "text/html;charset=utf-8";
    String responseBody = "<html><body><p><strong>Status:</strong> pass</p></body></html>";

    @Override
    public Response prepareResponse() {
        Response response = new Response();
        response.setStatusCode(200);
        response.setContentType(contentType);
        response.addResponseBody(responseBody);
        return response;
    }
}
