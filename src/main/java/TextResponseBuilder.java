import java.util.HashMap;

public class TextResponseBuilder implements ResponseBuilder{
    HashMap<Integer, String> statusCodeMap;
    int statusCode;
    String reasonPhrase;

    public TextResponseBuilder() {
        generateStatusCodeMap();
    }

    @Override
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
        this.reasonPhrase = statusCodeMap.get(statusCode);
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

    private void generateStatusCodeMap() {
        statusCodeMap = new HashMap<Integer, String>();
        statusCodeMap.put(200, "OK");
        statusCodeMap.put(301, "Moved Permanently");
        statusCodeMap.put(404, "Not Found");
        statusCodeMap.put(405, "Method Not Allowed");
        statusCodeMap.put(500, "Internal Server Error");
    }
}
