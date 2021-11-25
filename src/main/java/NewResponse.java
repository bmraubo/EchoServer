public class NewResponse {
    ResponseBuilder responseBuilder;

    String responseLine;
    String responseHeaders;
    byte[] responseBody;

    public NewResponse(ResponseBuilder responseBuilder) {
        this.responseBuilder = responseBuilder;
    }

    public void generateResponse() {
        this.responseLine = responseBuilder.getResponseLine();
        this.responseHeaders = responseBuilder.getHeaders();
        this.responseBody = responseBuilder.getBody();
    }
}
