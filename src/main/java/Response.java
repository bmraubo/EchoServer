public class Response {
    ResponseBuilder responseBuilder;

    String responseLine;
    String responseHeaders;
    byte[] responseBody;

    public Response(ResponseBuilder responseBuilder) {
        this.responseBuilder = responseBuilder;
    }

    public void generateResponse() {
        this.responseLine = responseBuilder.getResponseLine();
        this.responseHeaders = responseBuilder.getHeaders();
        this.responseBody = responseBuilder.getBody();
    }
}
