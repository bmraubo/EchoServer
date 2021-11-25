import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.StringJoiner;

public class TextResponseBuilder implements ResponseBuilder{
    HashMap<Integer, String> statusCodeMap;
    int statusCode;
    String reasonPhrase;
    HashMap<String, String> headers;
    byte[] responseBody;

    public TextResponseBuilder() {
        generateStatusCodeMap();
        generateHeaderMap();
    }

    @Override
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
        this.reasonPhrase = statusCodeMap.get(statusCode);
    }

    @Override
    public void setResponseBody(String body) {
        this.responseBody = body.getBytes(StandardCharsets.UTF_8);
        setHeader("Content-Length: ", calculateContentLength());
    }

    @Override
    public void setResponseBody(byte[] body) {
        this.responseBody = body;
        setHeader("Content-Length: ", calculateContentLength());
    }

    @Override
    public void setHeader(String headerType, String headerValue) {
        headers.put(headerType, headerValue);
    }

    @Override
    public void setHeader(String headerType, String[] headerValue) {
        String joinedHeaderValue = generateHeaderString(headerValue);
        headers.put(headerType, joinedHeaderValue);
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

    private void generateHeaderMap() {
        headers = new HashMap<String, String>();
    }

    private String generateHeaderString(String[] headerValue) {
        StringJoiner joiner = new StringJoiner(", ");
        for (String x : headerValue) {
            joiner.add(x);
        }
        return joiner.toString();
    }

    private String calculateContentLength() {
        return String.valueOf(responseBody.length);
    }
}
