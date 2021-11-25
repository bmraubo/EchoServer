public interface ResponseBuilder {

    void setStatusCode(int statusCode);
    void setResponseBody(String body);
    void setResponseBody(byte[] body);
    void setHeader(String headerType, String headerValue);
    void setHeader(String headerType, String[] headerValue);
    String getResponseLine();
    String getHeaders();
    String getBody();
}
