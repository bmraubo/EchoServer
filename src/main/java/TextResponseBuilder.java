public class TextResponseBuilder implements ResponseBuilder{
    int statusCode;
    String reasonPhrase;

    @Override
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    @Override
    public void setResponseBody(String body) {

    }

    @Override
    public void setResponseBody(byte[] body) {

    }

    @Override
    public void setHeader(String headerType, String headerValue) {

    }

    @Override
    public void setHeader(String headerType, String[] headerValue) {

    }

    @Override
    public String getResponseLine() {
        return null;
    }

    @Override
    public String getHeaders() {
        return null;
    }

    @Override
    public String getBody() {
        return null;
    }
}
