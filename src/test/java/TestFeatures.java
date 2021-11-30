import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class TestFeatures {

    @Test
    void SimpleGetTest() throws IOException, InterruptedException {
        int port = 5000;

        String testRequest = "GET /simple_get HTTP/1.1\r\n";

        InputStream testInputStream = new ByteArrayInputStream(testRequest.getBytes());
        BufferedReader input = new BufferedReader(new InputStreamReader(testInputStream));
        PrintWriter output = new PrintWriter(new StringWriter());

        Router router = Routes.assignRoutes();
        SocketWrapperSpy socketWrapper = new SocketWrapperSpy(input, output);
        Server testServer = new Server(router);
        testServer.setSocketWrapper(socketWrapper);

        testServer.start(port);

        String expectedResponseLine = "HTTP/1.1 200 OK\r\n";
        String expectedResponseHeader = "Content-Length: 0\r\n\r\n";

        Assertions.assertEquals(expectedResponseLine, socketWrapper.sentResponseLine);
        Assertions.assertEquals(expectedResponseHeader, socketWrapper.sentResponseHeaders);
    }

    @Test
    void SimpleGetWithBodyTest() throws IOException, InterruptedException {
        int port = 5000;

        String testRequest = "GET /simple_get_with_body HTTP/1.1\r\nContent-Length: 11\r\n\r\nHello World";

        InputStream testInputStream = new ByteArrayInputStream(testRequest.getBytes());
        BufferedReader input = new BufferedReader(new InputStreamReader(testInputStream));
        PrintWriter output = new PrintWriter(new StringWriter());

        Router router = Routes.assignRoutes();
        SocketWrapperSpy socketWrapper = new SocketWrapperSpy(input, output);
        Server testServer = new Server(router);
        testServer.setSocketWrapper(socketWrapper);

        testServer.start(port);

        String expectedResponseLine = "HTTP/1.1 200 OK\r\n";
        String expectedResponseHeaders = "Content-Length: 11\r\n\r\n";
        byte[] expectedResponseBody = "Hello world".getBytes(StandardCharsets.UTF_8);

        Assertions.assertEquals(expectedResponseLine, socketWrapper.sentResponseLine);
        Assertions.assertEquals(expectedResponseHeaders, socketWrapper.sentResponseHeaders);
        Assertions.assertArrayEquals(expectedResponseBody, socketWrapper.sentResponseBody);
    }

    @Test
    void SimplePostTest() throws IOException, InterruptedException {
        int port = 5000;

        String testRequest = "POST /echo_body HTTP/1.1\r\nContent-Length: 11\r\n\r\nHello World";

        InputStream testInputStream = new ByteArrayInputStream(testRequest.getBytes());
        BufferedReader input = new BufferedReader(new InputStreamReader(testInputStream));
        PrintWriter output = new PrintWriter(new StringWriter());

        Router router = Routes.assignRoutes();
        SocketWrapperSpy socketWrapper = new SocketWrapperSpy(input, output);
        Server testServer = new Server(router);
        testServer.setSocketWrapper(socketWrapper);

        testServer.start(port);

        String expectedResponseLine = "HTTP/1.1 200 OK\r\n";
        String expectedResponseHeaders = "Content-Length: 11\r\n\r\n";
        byte[] expectedResponseBody = "Hello World".getBytes(StandardCharsets.UTF_8);

        Assertions.assertEquals(expectedResponseLine, socketWrapper.sentResponseLine);
        Assertions.assertEquals(expectedResponseHeaders, socketWrapper.sentResponseHeaders);
        Assertions.assertArrayEquals(expectedResponseBody, socketWrapper.sentResponseBody);
    }

    @Test
    void SimpleHeadToSimpleGetTest() throws IOException, InterruptedException {
        int port = 5000;

        String testRequest = "HEAD /simple_get HTTP/1.1\r\n";

        InputStream testInputStream = new ByteArrayInputStream(testRequest.getBytes());
        BufferedReader input = new BufferedReader(new InputStreamReader(testInputStream));
        PrintWriter output = new PrintWriter(new StringWriter());

        Router router = Routes.assignRoutes();
        SocketWrapperSpy socketWrapper = new SocketWrapperSpy(input, output);
        Server testServer = new Server(router);
        testServer.setSocketWrapper(socketWrapper);

        testServer.start(port);

        String expectedResponseLine = "HTTP/1.1 200 OK\r\n";
        String expectedResponseHeader = "Content-Length: 0\r\n\r\n";

        Assertions.assertEquals(expectedResponseLine, socketWrapper.sentResponseLine);
        Assertions.assertEquals(expectedResponseHeader, socketWrapper.sentResponseHeaders);
    }

    @Test
    void SimpleHeadToHeadRequestTest() throws IOException, InterruptedException{
        int port = 5000;

        String testRequest = "HEAD /head_request HTTP/1.1\r\n";

        InputStream testInputStream = new ByteArrayInputStream(testRequest.getBytes());
        BufferedReader input = new BufferedReader(new InputStreamReader(testInputStream));
        PrintWriter output = new PrintWriter(new StringWriter());

        Router router = Routes.assignRoutes();
        SocketWrapperSpy socketWrapper = new SocketWrapperSpy(input, output);
        Server testServer = new Server(router);
        testServer.setSocketWrapper(socketWrapper);

        testServer.start(port);

        String expectedResponseLine = "HTTP/1.1 200 OK\r\n";
        String expectedResponseHeader = "Content-Length: 0\r\n\r\n";

        Assertions.assertEquals(expectedResponseLine, socketWrapper.sentResponseLine);
        Assertions.assertEquals(expectedResponseHeader, socketWrapper.sentResponseHeaders);
    }

    @Test
    void MethodNotAllowedTest() throws IOException, InterruptedException {
        int port = 5000;

        String testRequest = "GET /head_request HTTP/1.1\r\n";

        InputStream testInputStream = new ByteArrayInputStream(testRequest.getBytes());
        BufferedReader input = new BufferedReader(new InputStreamReader(testInputStream));
        PrintWriter output = new PrintWriter(new StringWriter());

        Router router = Routes.assignRoutes();
        SocketWrapperSpy socketWrapper = new SocketWrapperSpy(input, output);
        Server testServer = new Server(router);
        testServer.setSocketWrapper(socketWrapper);

        testServer.start(port);

        String expectedResponseLine = "HTTP/1.1 405 Method Not Allowed\r\n";
        String expectedResponseHeader = "Allow: HEAD, OPTIONS\r\nContent-Length: 0\r\n\r\n";

        Assertions.assertEquals(expectedResponseLine, socketWrapper.sentResponseLine);
        Assertions.assertEquals(expectedResponseHeader, socketWrapper.sentResponseHeaders);
    }

    @Test
    void SimpleOptionsToMethodOptionsTest() throws IOException, InterruptedException {
        int port = 5000;

        String testRequest = "OPTIONS /method_options HTTP/1.1\r\n";

        InputStream testInputStream = new ByteArrayInputStream(testRequest.getBytes());
        BufferedReader input = new BufferedReader(new InputStreamReader(testInputStream));
        PrintWriter output = new PrintWriter(new StringWriter());

        Router router = Routes.assignRoutes();
        SocketWrapperSpy socketWrapper = new SocketWrapperSpy(input, output);
        Server testServer = new Server(router);
        testServer.setSocketWrapper(socketWrapper);

        testServer.start(port);

        String expectedResponseLine = "HTTP/1.1 200 OK\r\n";
        String expectedResponseHeader = "Allow: GET, HEAD, OPTIONS\r\nContent-Length: 0\r\n\r\n";

        Assertions.assertEquals(expectedResponseLine, socketWrapper.sentResponseLine);
        Assertions.assertEquals(expectedResponseHeader, socketWrapper.sentResponseHeaders);
    }

    @Test
    void SimpleOptionsToMethodOptions2Test() throws IOException, InterruptedException {
        int port = 5000;

        String testRequest = "OPTIONS /method_options2 HTTP/1.1\r\n";

        InputStream testInputStream = new ByteArrayInputStream(testRequest.getBytes());
        BufferedReader input = new BufferedReader(new InputStreamReader(testInputStream));
        PrintWriter output = new PrintWriter(new StringWriter());

        Router router = Routes.assignRoutes();
        SocketWrapperSpy socketWrapper = new SocketWrapperSpy(input, output);
        Server testServer = new Server(router);
        testServer.setSocketWrapper(socketWrapper);

        testServer.start(port);

        String expectedResponseLine = "HTTP/1.1 200 OK\r\n";
        String expectedResponseHeader = "Allow: GET, HEAD, OPTIONS, PUT, POST\r\nContent-Length: 0\r\n\r\n";

        Assertions.assertEquals(expectedResponseLine, socketWrapper.sentResponseLine);
        Assertions.assertEquals(expectedResponseHeader, socketWrapper.sentResponseHeaders);
    }

    @Test
    void SimpleRedirectTest() throws IOException, InterruptedException {
        int port = 5000;

        String testRequest = "GET /redirect HTTP/1.1\r\n";

        InputStream testInputStream = new ByteArrayInputStream(testRequest.getBytes());
        BufferedReader input = new BufferedReader(new InputStreamReader(testInputStream));
        PrintWriter output = new PrintWriter(new StringWriter());

        Router router = Routes.assignRoutes();
        SocketWrapperSpy socketWrapper = new SocketWrapperSpy(input, output);
        Server testServer = new Server(router);
        testServer.setSocketWrapper(socketWrapper);

        testServer.start(port);

        String expectedResponseLine = "HTTP/1.1 301 Moved Permanently\r\n";
        String expectedResponseHeader = "Location: http://127.0.0.1:5000/simple_get\r\nContent-Length: 0\r\n\r\n";

        Assertions.assertEquals(expectedResponseLine, socketWrapper.sentResponseLine);
        Assertions.assertEquals(expectedResponseHeader, socketWrapper.sentResponseHeaders);
    }

    @Test
    void ResourceNotFoundTest() throws IOException, InterruptedException {
        int port = 5000;

        String testRequest = "GET /resource_not_found HTTP/1.1\r\n";

        InputStream testInputStream = new ByteArrayInputStream(testRequest.getBytes());
        BufferedReader input = new BufferedReader(new InputStreamReader(testInputStream));
        PrintWriter output = new PrintWriter(new StringWriter());

        Router router = Routes.assignRoutes();
        SocketWrapperSpy socketWrapper = new SocketWrapperSpy(input, output);
        Server testServer = new Server(router);
        testServer.setSocketWrapper(socketWrapper);

        testServer.start(port);

        String expectedResponseLine = "HTTP/1.1 404 Not Found\r\n";
        String expectedResponseHeader = "Content-Length: 0\r\n\r\n";

        Assertions.assertEquals(expectedResponseLine, socketWrapper.sentResponseLine);
        Assertions.assertEquals(expectedResponseHeader, socketWrapper.sentResponseHeaders);
    }

    @Test
    void EmptyRequestTest() throws IOException, InterruptedException {
        int port = 5000;

        byte[] testRequest = new byte[0];

        InputStream testInputStream = new ByteArrayInputStream(testRequest);
        BufferedReader input = new BufferedReader(new InputStreamReader(testInputStream));
        PrintWriter output = new PrintWriter(new StringWriter());

        Router router = Routes.assignRoutes();
        SocketWrapperSpy socketWrapper = new SocketWrapperSpy(input, output);
        Server testServer = new Server(router);
        testServer.setSocketWrapper(socketWrapper);

        testServer.start(port);

        String expectedResponseLine = "HTTP/1.1 500 Internal Server Error\r\n";
        String expectedResponseHeader = "Content-Length: 40\r\n\r\n";
        byte[] expectedResponseBody = "Request read as empty, please try again.".getBytes(StandardCharsets.UTF_8);

        Assertions.assertEquals(expectedResponseLine, socketWrapper.sentResponseLine);
        Assertions.assertEquals(expectedResponseHeader, socketWrapper.sentResponseHeaders);
        Assertions.assertArrayEquals(expectedResponseBody, socketWrapper.sentResponseBody);
    }

    @Test
    void TextResponseTest() throws IOException, InterruptedException {
        int port = 5000;

        String testRequest = "GET /text_response HTTP/1.1\r\n";

        InputStream testInputStream = new ByteArrayInputStream(testRequest.getBytes());
        BufferedReader input = new BufferedReader(new InputStreamReader(testInputStream));
        PrintWriter output = new PrintWriter(new StringWriter());

        Router router = Routes.assignRoutes();
        SocketWrapperSpy socketWrapper = new SocketWrapperSpy(input, output);
        Server testServer = new Server(router);
        testServer.setSocketWrapper(socketWrapper);

        testServer.start(port);

        String responseText = "text response";

        String expectedResponseLine = "HTTP/1.1 200 OK\r\n";
        String expectedResponseHeader = "Content-Type: text/plain;charset=utf-8\r\nContent-Length: 13\r\n\r\n";
        byte[] expectedResponseBody = responseText.getBytes(StandardCharsets.UTF_8);

        Assertions.assertEquals(expectedResponseLine, socketWrapper.sentResponseLine);
        Assertions.assertEquals(expectedResponseHeader, socketWrapper.sentResponseHeaders);
        Assertions.assertArrayEquals(expectedResponseBody, socketWrapper.sentResponseBody);
    }

    @Test
    void HTMLResponseTest() throws IOException, InterruptedException {
        int port = 5000;

        String testRequest = "GET /html_response HTTP/1.1\r\n";

        InputStream testInputStream = new ByteArrayInputStream(testRequest.getBytes());
        BufferedReader input = new BufferedReader(new InputStreamReader(testInputStream));
        PrintWriter output = new PrintWriter(new StringWriter());

        Router router = Routes.assignRoutes();
        SocketWrapperSpy socketWrapper = new SocketWrapperSpy(input, output);
        Server testServer = new Server(router);
        testServer.setSocketWrapper(socketWrapper);

        testServer.start(port);

        String responseText = "<html><body><p>HTML Response</p></body></html>";

        String expectedResponseLine = "HTTP/1.1 200 OK\r\n";
        String expectedResponseHeader = "Content-Type: text/html;charset=utf-8\r\nContent-Length: 46\r\n\r\n";
        byte[] expectedResponseBody = responseText.getBytes(StandardCharsets.UTF_8);

        Assertions.assertEquals(expectedResponseLine, socketWrapper.sentResponseLine);
        Assertions.assertEquals(expectedResponseHeader, socketWrapper.sentResponseHeaders);
        Assertions.assertArrayEquals(expectedResponseBody, socketWrapper.sentResponseBody);
    }

    @Test
    void JSONResponseTest() throws IOException, InterruptedException {
        int port = 5000;

        String testRequest = "GET /json_response HTTP/1.1\r\n";

        InputStream testInputStream = new ByteArrayInputStream(testRequest.getBytes());
        BufferedReader input = new BufferedReader(new InputStreamReader(testInputStream));
        PrintWriter output = new PrintWriter(new StringWriter());

        Router router = Routes.assignRoutes();
        SocketWrapperSpy socketWrapper = new SocketWrapperSpy(input, output);
        Server testServer = new Server(router);
        testServer.setSocketWrapper(socketWrapper);

        testServer.start(port);

        JSONObject responseJson = new JSONObject();
        responseJson.put("key1", "value1");
        responseJson.put("key2", "value2");

        String expectedResponseLine = "HTTP/1.1 200 OK\r\n";
        String expectedResponseHeader = "Content-Type: application/json;charset=utf-8\r\nContent-Length: 33\r\n\r\n";
        byte[] expectedResponseBody = responseJson.toString().getBytes(StandardCharsets.UTF_8);

        Assertions.assertEquals(expectedResponseLine, socketWrapper.sentResponseLine);
        Assertions.assertEquals(expectedResponseHeader, socketWrapper.sentResponseHeaders);
        Assertions.assertArrayEquals(expectedResponseBody, socketWrapper.sentResponseBody);
    }

    @Test
    void XMLResponseTest() throws IOException, InterruptedException {
        int port = 5000;

        String testRequest = "GET /xml_response HTTP/1.1\r\n";

        InputStream testInputStream = new ByteArrayInputStream(testRequest.getBytes());
        BufferedReader input = new BufferedReader(new InputStreamReader(testInputStream));
        PrintWriter output = new PrintWriter(new StringWriter());

        Router router = Routes.assignRoutes();
        SocketWrapperSpy socketWrapper = new SocketWrapperSpy(input, output);
        Server testServer = new Server(router);
        testServer.setSocketWrapper(socketWrapper);

        testServer.start(port);

        String XMLString = "<note><body>XML Response</body></note>";

        String expectedResponseLine = "HTTP/1.1 200 OK\r\n";
        String expectedResponseHeader = "Content-Type: application/xml;charset=utf-8\r\nContent-Length: 38\r\n\r\n";
        byte[] expectedResponseBody = XMLString.getBytes(StandardCharsets.UTF_8);

        Assertions.assertEquals(expectedResponseLine, socketWrapper.sentResponseLine);
        Assertions.assertEquals(expectedResponseHeader, socketWrapper.sentResponseHeaders);
        Assertions.assertArrayEquals(expectedResponseBody, socketWrapper.sentResponseBody);
    }

    @Test
    void HealthCheckTest() throws IOException, InterruptedException {
        int port = 5000;

        String testRequest = "GET /health-check.html HTTP/1.1\r\n";

        InputStream testInputStream = new ByteArrayInputStream(testRequest.getBytes());
        BufferedReader input = new BufferedReader(new InputStreamReader(testInputStream));
        PrintWriter output = new PrintWriter(new StringWriter());

        Router router = Routes.assignRoutes();
        SocketWrapperSpy socketWrapper = new SocketWrapperSpy(input, output);
        Server testServer = new Server(router);
        testServer.setSocketWrapper(socketWrapper);

        testServer.start(port);

        String responseString = "<html><body><p><strong>Status:</strong> pass</p></body></html>";

        String expectedResponseLine = "HTTP/1.1 200 OK\r\n";
        String expectedResponseHeader = "Content-Type: text/html;charset=utf-8\r\nContent-Length: 62\r\n\r\n";
        byte[] expectedResponseBody = responseString.getBytes(StandardCharsets.UTF_8);

        Assertions.assertEquals(expectedResponseLine, socketWrapper.sentResponseLine);
        Assertions.assertEquals(expectedResponseHeader, socketWrapper.sentResponseHeaders);
        Assertions.assertArrayEquals(expectedResponseBody, socketWrapper.sentResponseBody);
    }

    @Test
    void JPEGImageSendTest() throws IOException, InterruptedException {
        int port = 5000;

        String testRequest = "GET /kitteh.jpg HTTP/1.1\r\n";

        InputStream testInputStream = new ByteArrayInputStream(testRequest.getBytes());
        BufferedReader input = new BufferedReader(new InputStreamReader(testInputStream));
        PrintWriter output = new PrintWriter(new StringWriter());

        Router router = Routes.assignRoutes();
        SocketWrapperSpy socketWrapper = new SocketWrapperSpy(input, output);
        Server testServer = new Server(router);
        testServer.setSocketWrapper(socketWrapper);

        testServer.start(port);

        String expectedResponseLine = "HTTP/1.1 200 OK\r\n";
        String expectedResponseHeader = "Content-Type: image/jpeg\r\nContent-Length: 207922\r\n\r\n";
        File file = new File("src/test/java/kitteh.jpg");
        byte[] expectedResponseBody = Files.readAllBytes(file.toPath());

        Assertions.assertEquals(expectedResponseLine, socketWrapper.sentResponseLine);
        Assertions.assertEquals(expectedResponseHeader, socketWrapper.sentResponseHeaders);
        Assertions.assertEquals(expectedResponseBody.length, socketWrapper.sentResponseBody.length);
    }

    @Test
    void PNGImageSendTest() throws IOException, InterruptedException {
        int port = 5000;

        String testRequest = "GET /doggo.png HTTP/1.1\r\n";

        InputStream testInputStream = new ByteArrayInputStream(testRequest.getBytes());
        BufferedReader input = new BufferedReader(new InputStreamReader(testInputStream));
        PrintWriter output = new PrintWriter(new StringWriter());

        Router router = Routes.assignRoutes();
        SocketWrapperSpy socketWrapper = new SocketWrapperSpy(input, output);
        Server testServer = new Server(router);
        testServer.setSocketWrapper(socketWrapper);

        testServer.start(port);

        String expectedResponseLine = "HTTP/1.1 200 OK\r\n";
        String expectedResponseHeader = "Content-Type: image/png\r\nContent-Length: 351702\r\n\r\n";
        File file = new File("src/test/java/doggo.png");
        byte[] expectedResponseBody = Files.readAllBytes(file.toPath());

        Assertions.assertEquals(expectedResponseLine, socketWrapper.sentResponseLine);
        Assertions.assertEquals(expectedResponseHeader, socketWrapper.sentResponseHeaders);
        Assertions.assertEquals(expectedResponseBody.length, socketWrapper.sentResponseBody.length);
    }

    @Test
    void GIFImageSendTest() throws IOException, InterruptedException {
        int port = 5000;

        String testRequest = "GET /kisses.gif HTTP/1.1\r\n";

        InputStream testInputStream = new ByteArrayInputStream(testRequest.getBytes());
        BufferedReader input = new BufferedReader(new InputStreamReader(testInputStream));
        PrintWriter output = new PrintWriter(new StringWriter());

        Router router = Routes.assignRoutes();
        SocketWrapperSpy socketWrapper = new SocketWrapperSpy(input, output);
        Server testServer = new Server(router);
        testServer.setSocketWrapper(socketWrapper);

        testServer.start(port);

        String expectedResponseLine = "HTTP/1.1 200 OK\r\n";
        String expectedResponseHeader = "Content-Type: image/gif\r\nContent-Length: 432985\r\n\r\n";
        File file = new File("src/test/java/kisses.gif");
        byte[] expectedResponseBody = Files.readAllBytes(file.toPath());

        Assertions.assertEquals(expectedResponseLine, socketWrapper.sentResponseLine);
        Assertions.assertEquals(expectedResponseHeader, socketWrapper.sentResponseHeaders);
        Assertions.assertEquals(expectedResponseBody.length, socketWrapper.sentResponseBody.length);
    }
}
