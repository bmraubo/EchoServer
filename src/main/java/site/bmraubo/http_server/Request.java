package site.bmraubo.http_server;

import java.util.LinkedHashMap;

public class Request {
    RequestBuilder requestBuilder;
    public String method;
    public String uri;
    public String protocol;
    public LinkedHashMap<String, String> headers;
    public String body;

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
