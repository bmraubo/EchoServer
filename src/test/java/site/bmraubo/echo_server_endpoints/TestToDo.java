package site.bmraubo.echo_server_endpoints;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import site.bmraubo.http_server.Endpoint;
import site.bmraubo.http_server.Request;
import site.bmraubo.http_server.RequestBuilder;
import site.bmraubo.http_server.Response;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TestToDo {

    @Test
    void contentTypeValidationTest() {
        String testRequest = "POST /todo HTTP/1.1\r\n" +
                "Content-Type: application/json\r\n" +
                "Connection: close\r\n" +
                "Host: 127.0.0.1:5000]r\n" +
                "User-Agent: http.rb/4.3.0\r\n" +
                "Content-Length: 21\r\n" +
                "\r\n" +
                "{\"task\":\"a new task\"}";
        InputStream testInputStream = new ByteArrayInputStream(testRequest.getBytes());
        BufferedReader input = new BufferedReader(new InputStreamReader(testInputStream));

        RequestBuilder requestBuilder = new RequestBuilder();
        Request request = new Request(requestBuilder);
        request.parseRequest(input);

        Endpoint endpoint = new ToDo();
        Response response = endpoint.prepareResponse(request);
        response.generateResponse();

        Assertions.assertEquals("HTTP/1.1 201 Created\r\n", response.responseLine);

    }

    @Test
    void contentValueValidationTest() {
        String testRequest = "POST /todo HTTP/1.1\r\n" +
                "Content-Type: application/xml\r\n" +
                "Connection: close\r\n" +
                "Host: 127.0.0.1:5000]r\n" +
                "User-Agent: http.rb/4.3.0\r\n" +
                "Content-Length: 10\r\n" +
                "\r\n" +
                "a new task";
        InputStream testInputStream = new ByteArrayInputStream(testRequest.getBytes());
        BufferedReader input = new BufferedReader(new InputStreamReader(testInputStream));

        RequestBuilder requestBuilder = new RequestBuilder();
        Request request = new Request(requestBuilder);
        request.parseRequest(input);

        Endpoint endpoint = new ToDo();
        Response response = endpoint.prepareResponse(request);
        response.generateResponse();

        Assertions.assertEquals("HTTP/1.1 415 Unsupported Media Type", response.responseLine);
    }
}
