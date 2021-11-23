import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;

public class TestFeatures {

    @Test
    void SimpleGetTest() throws IOException, InterruptedException {
        int port = 5000;

        String testRequest = "GET /simple_get HTTP/1.1\r\n";

        InputStream testInputStream = new ByteArrayInputStream(testRequest.getBytes());
        BufferedReader input = new BufferedReader(new InputStreamReader(testInputStream));
        PrintWriter output = new PrintWriter(new StringWriter());

        SocketWrapperSpy socketWrapper = new SocketWrapperSpy(input, output);
        Server testServer = new Server(socketWrapper);

        testServer.start(port);

        Assertions.assertEquals("HTTP/1.1 200 OK\r\nConnection: close\r\nContent-Length: 0\r\n\r\n", socketWrapper.sentResponse);
    }

    @Test
    void SimpleGetWithBodyTest() throws IOException, InterruptedException {
        int port = 5000;

        String testRequest = "GET /simple_get_with_body HTTP/1.1\r\nContent-Length: 11\r\n\r\nHello World";

        InputStream testInputStream = new ByteArrayInputStream(testRequest.getBytes());
        BufferedReader input = new BufferedReader(new InputStreamReader(testInputStream));
        PrintWriter output = new PrintWriter(new StringWriter());

        SocketWrapperSpy socketWrapper = new SocketWrapperSpy(input, output);
        Server testServer = new Server(socketWrapper);

        testServer.start(port);

        String expectedResponse = "HTTP/1.1 200 OK\r\nConnection: close\r\nContent-Length: 11\r\n\r\nHello world";

        Assertions.assertEquals(expectedResponse, socketWrapper.sentResponse);
    }

    @Test
    void SimplePostTest() throws IOException, InterruptedException {
        int port = 5000;

        String testRequest = "POST /echo_body HTTP/1.1\r\nContent-Length: 11\r\n\r\nHello World";

        InputStream testInputStream = new ByteArrayInputStream(testRequest.getBytes());
        BufferedReader input = new BufferedReader(new InputStreamReader(testInputStream));
        PrintWriter output = new PrintWriter(new StringWriter());

        SocketWrapperSpy socketWrapper = new SocketWrapperSpy(input, output);
        Server testServer = new Server(socketWrapper);

        testServer.start(port);

        String expectedResponse = "HTTP/1.1 200 OK\r\nConnection: close\r\nContent-Length: 11\r\n\r\nHello World";

        Assertions.assertEquals(expectedResponse, socketWrapper.sentResponse);
    }

    @Test
    void SimpleHeadToSimpleGetTest() throws IOException, InterruptedException {
        int port = 5000;

        String testRequest = "HEAD /simple_get HTTP/1.1\r\n";

        InputStream testInputStream = new ByteArrayInputStream(testRequest.getBytes());
        BufferedReader input = new BufferedReader(new InputStreamReader(testInputStream));
        PrintWriter output = new PrintWriter(new StringWriter());

        SocketWrapperSpy socketWrapper = new SocketWrapperSpy(input, output);
        Server testServer = new Server(socketWrapper);

        testServer.start(port);

        Assertions.assertEquals("HTTP/1.1 200 OK\r\nConnection: close\r\nContent-Length: 0\r\n\r\n", socketWrapper.sentResponse);
    }

    @Test
    void SimpleHeadToHeadRequestTest() throws IOException, InterruptedException{
        int port = 5000;

        String testRequest = "HEAD /head_request HTTP/1.1\r\n";

        InputStream testInputStream = new ByteArrayInputStream(testRequest.getBytes());
        BufferedReader input = new BufferedReader(new InputStreamReader(testInputStream));
        PrintWriter output = new PrintWriter(new StringWriter());

        SocketWrapperSpy socketWrapper = new SocketWrapperSpy(input, output);
        Server testServer = new Server(socketWrapper);

        testServer.start(port);

        Assertions.assertEquals("HTTP/1.1 200 OK\r\nConnection: close\r\nContent-Length: 0\r\n\r\n", socketWrapper.sentResponse);
    }

    @Test
    void MethodNotAllowedTest() throws IOException, InterruptedException {
        int port = 5000;

        String testRequest = "GET /head_request HTTP/1.1\r\n";

        InputStream testInputStream = new ByteArrayInputStream(testRequest.getBytes());
        BufferedReader input = new BufferedReader(new InputStreamReader(testInputStream));
        PrintWriter output = new PrintWriter(new StringWriter());

        SocketWrapperSpy socketWrapper = new SocketWrapperSpy(input, output);
        Server testServer = new Server(socketWrapper);

        testServer.start(port);

        Assertions.assertEquals("HTTP/1.1 405 Method Not Allowed\r\nConnection: close\r\nAllow: HEAD, OPTIONS\r\nContent-Length: 0\r\n\r\n", socketWrapper.sentResponse);
    }

    @Test
    void SimpleOptionsToMethodOptionsTest() throws IOException, InterruptedException {
        int port = 5000;

        String testRequest = "OPTIONS /method_options HTTP/1.1\r\n";

        InputStream testInputStream = new ByteArrayInputStream(testRequest.getBytes());
        BufferedReader input = new BufferedReader(new InputStreamReader(testInputStream));
        PrintWriter output = new PrintWriter(new StringWriter());

        SocketWrapperSpy socketWrapper = new SocketWrapperSpy(input, output);
        Server testServer = new Server(socketWrapper);

        testServer.start(port);

        String expectedResponse = "HTTP/1.1 200 OK\r\nConnection: close\r\nAllow: GET, HEAD, OPTIONS\r\nContent-Length: 0\r\n\r\n";

        Assertions.assertEquals(expectedResponse, socketWrapper.sentResponse);
    }

    @Test
    void SimpleOptionsToMethodOptions2Test() throws IOException, InterruptedException {
        int port = 5000;

        String testRequest = "OPTIONS /method_options2 HTTP/1.1\r\n";

        InputStream testInputStream = new ByteArrayInputStream(testRequest.getBytes());
        BufferedReader input = new BufferedReader(new InputStreamReader(testInputStream));
        PrintWriter output = new PrintWriter(new StringWriter());

        SocketWrapperSpy socketWrapper = new SocketWrapperSpy(input, output);
        Server testServer = new Server(socketWrapper);

        testServer.start(port);

        String expectedResponse = "HTTP/1.1 200 OK\r\nConnection: close\r\nAllow: GET, HEAD, OPTIONS, PUT, POST\r\nContent-Length: 0\r\n\r\n";

        Assertions.assertEquals(expectedResponse, socketWrapper.sentResponse);
    }

    @Test
    void SimpleRedirectTest() throws IOException, InterruptedException {
        int port = 5000;

        String testRequest = "GET /redirect HTTP/1.1\r\n";

        InputStream testInputStream = new ByteArrayInputStream(testRequest.getBytes());
        BufferedReader input = new BufferedReader(new InputStreamReader(testInputStream));
        PrintWriter output = new PrintWriter(new StringWriter());

        SocketWrapperSpy socketWrapper = new SocketWrapperSpy(input, output);
        Server testServer = new Server(socketWrapper);

        testServer.start(port);

        String expectedResponse = "HTTP/1.1 301 Moved Permanently\r\nConnection: close\r\nLocation: http://127.0.0.1:5000/simple_get\r\nContent-Length: 0\r\n\r\n";

        Assertions.assertEquals(expectedResponse, socketWrapper.sentResponse);
    }

    @Test
    void ResourceNotFoundTest() throws IOException, InterruptedException {
        int port = 5000;

        String testRequest = "GET /resource_not_found HTTP/1.1\r\n";

        InputStream testInputStream = new ByteArrayInputStream(testRequest.getBytes());
        BufferedReader input = new BufferedReader(new InputStreamReader(testInputStream));
        PrintWriter output = new PrintWriter(new StringWriter());

        SocketWrapperSpy socketWrapper = new SocketWrapperSpy(input, output);
        Server testServer = new Server(socketWrapper);

        testServer.start(port);

        String expectedResponse = "HTTP/1.1 404 Not Found\r\nConnection: close\r\nContent-Length: 0\r\n\r\n";

        Assertions.assertEquals(expectedResponse, socketWrapper.sentResponse);
    }

    @Test
    void EmptyRequestTest() throws IOException, InterruptedException {
        int port = 5000;

        byte[] testRequest = new byte[0];

        InputStream testInputStream = new ByteArrayInputStream(testRequest);
        BufferedReader input = new BufferedReader(new InputStreamReader(testInputStream));
        PrintWriter output = new PrintWriter(new StringWriter());

        SocketWrapperSpy socketWrapper = new SocketWrapperSpy(input, output);
        Server testServer = new Server(socketWrapper);

        testServer.start(port);

        String expectedResponse = "HTTP/1.1 500 Internal Server Error\r\nConnection: close\r\nContent-Length: 40\r\n\r\nRequest read as empty, please try again.";

        Assertions.assertEquals(expectedResponse, socketWrapper.sentResponse);
    }

    @Test
    void TextResponseTest() throws IOException, InterruptedException {
        int port = 5000;

        String testRequest = "GET /text_response HTTP/1.1\r\n";

        InputStream testInputStream = new ByteArrayInputStream(testRequest.getBytes());
        BufferedReader input = new BufferedReader(new InputStreamReader(testInputStream));
        PrintWriter output = new PrintWriter(new StringWriter());

        SocketWrapperSpy socketWrapper = new SocketWrapperSpy(input, output);
        Server testServer = new Server(socketWrapper);

        testServer.start(port);

        String responseText = "text response";

        String expectedResponse = "HTTP/1.1 200 OK\r\nConnection: close\r\nContent-Type: text/plain;charset=utf-8\r\nContent-Length: 13\r\n\r\n"+responseText;

        Assertions.assertEquals(expectedResponse, socketWrapper.sentResponse);
    }

    @Test
    void HTMLResponseTest() throws IOException, InterruptedException {
        int port = 5000;

        String testRequest = "GET /html_response HTTP/1.1\r\n";

        InputStream testInputStream = new ByteArrayInputStream(testRequest.getBytes());
        BufferedReader input = new BufferedReader(new InputStreamReader(testInputStream));
        PrintWriter output = new PrintWriter(new StringWriter());

        SocketWrapperSpy socketWrapper = new SocketWrapperSpy(input, output);
        Server testServer = new Server(socketWrapper);

        testServer.start(port);

        String responseText = "<html><body><p>HTML Response</p></body></html>";

        String expectedResponse = "HTTP/1.1 200 OK\r\nConnection: close\r\nContent-Type: text/html;charset=utf-8\r\nContent-Length: 46\r\n\r\n" + responseText;

        Assertions.assertEquals(expectedResponse, socketWrapper.sentResponse);
    }

    @Test
    void JSONResponseTest() throws IOException, InterruptedException {
        int port = 5000;

        String testRequest = "GET /json_response HTTP/1.1\r\n";

        InputStream testInputStream = new ByteArrayInputStream(testRequest.getBytes());
        BufferedReader input = new BufferedReader(new InputStreamReader(testInputStream));
        PrintWriter output = new PrintWriter(new StringWriter());

        SocketWrapperSpy socketWrapper = new SocketWrapperSpy(input, output);
        Server testServer = new Server(socketWrapper);

        testServer.start(port);

        JSONObject responseJson = new JSONObject();
        responseJson.put("body", "json");

        String expectedResponse = "HTTP/1.1 200 OK\r\nConnection: close\r\nContent-Type: application/json;charset=utf-8\r\nContent-Length: 15\r\n\r\n" + responseJson;

        Assertions.assertEquals(expectedResponse, socketWrapper.sentResponse);
    }
}
