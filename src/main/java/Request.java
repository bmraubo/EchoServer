import java.util.LinkedHashMap;

public class Request {
    RequestBuilder requestBuilder;
    String method;
    String uri;
    String protocol;
    LinkedHashMap<String, String> headers;
    String body;

    public Request(RequestBuilder requestBuilder) {
        this.requestBuilder = requestBuilder;
    }

    public void parseRequest(String requestData) {
        requestBuilder.extractRequest(requestData);
        method = requestBuilder.getMethod();
        uri = requestBuilder.getURI();
        protocol = requestBuilder.getProtocol();
        headers = requestBuilder.getHeaders();
        body = requestBuilder.getBody();
    }

}
