import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import site.bmraubo.http_server.ConnectionSpy;
import site.bmraubo.http_server.Router;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class TestFeatures {

    @Test
    void SimpleGetTest() {
        String testRequest = "GET /simple_get HTTP/1.1\r\n";

        InputStream testInputStream = new ByteArrayInputStream(testRequest.getBytes());
        BufferedReader input = new BufferedReader(new InputStreamReader(testInputStream));
        PrintWriter output = new PrintWriter(new StringWriter());

        Router router = Routes.assignRoutes();
        ConnectionSpy connectionSpy = new ConnectionSpy(input, output, router);
        connectionSpy.processRequest();

        String expectedResponseLine = "HTTP/1.1 200 OK\r\n";
        String expectedResponseHeader = "Content-Length: 0\r\n\r\n";

        Assertions.assertEquals(expectedResponseLine, connectionSpy.responseLine);
        Assertions.assertEquals(expectedResponseHeader, connectionSpy.headers);
    }

    @Test
    void SimpleGetWithBodyTest() {
        String testRequest = "GET /simple_get_with_body HTTP/1.1\r\nContent-Length: 11\r\n\r\nHello World";

        InputStream testInputStream = new ByteArrayInputStream(testRequest.getBytes());
        BufferedReader input = new BufferedReader(new InputStreamReader(testInputStream));
        PrintWriter output = new PrintWriter(new StringWriter());

        Router router = Routes.assignRoutes();
        ConnectionSpy connectionSpy = new ConnectionSpy(input, output, router);
        connectionSpy.processRequest();

        String expectedResponseLine = "HTTP/1.1 200 OK\r\n";
        String expectedResponseHeaders = "Content-Length: 11\r\n\r\n";
        byte[] expectedResponseBody = "Hello world".getBytes(StandardCharsets.UTF_8);

        Assertions.assertEquals(expectedResponseLine, connectionSpy.responseLine);
        Assertions.assertEquals(expectedResponseHeaders, connectionSpy.headers);
        Assertions.assertArrayEquals(expectedResponseBody, connectionSpy.body);
    }

    @Test
    void SimplePostTest() {
        String testRequest = "POST /echo_body HTTP/1.1\r\nContent-Length: 11\r\n\r\nHello World";

        InputStream testInputStream = new ByteArrayInputStream(testRequest.getBytes());
        BufferedReader input = new BufferedReader(new InputStreamReader(testInputStream));
        PrintWriter output = new PrintWriter(new StringWriter());

        Router router = Routes.assignRoutes();
        ConnectionSpy connectionSpy = new ConnectionSpy(input, output, router);
        connectionSpy.processRequest();

        String expectedResponseLine = "HTTP/1.1 200 OK\r\n";
        String expectedResponseHeaders = "Content-Length: 11\r\n\r\n";
        byte[] expectedResponseBody = "Hello World".getBytes(StandardCharsets.UTF_8);

        Assertions.assertEquals(expectedResponseLine, connectionSpy.responseLine);
        Assertions.assertEquals(expectedResponseHeaders, connectionSpy.headers);
        Assertions.assertArrayEquals(expectedResponseBody, connectionSpy.body);
    }

    @Test
    void SimpleHeadToSimpleGetTest() {
        String testRequest = "HEAD /simple_get HTTP/1.1\r\n";

        InputStream testInputStream = new ByteArrayInputStream(testRequest.getBytes());
        BufferedReader input = new BufferedReader(new InputStreamReader(testInputStream));
        PrintWriter output = new PrintWriter(new StringWriter());

        Router router = Routes.assignRoutes();
        ConnectionSpy connectionSpy = new ConnectionSpy(input, output, router);
        connectionSpy.processRequest();

        String expectedResponseLine = "HTTP/1.1 200 OK\r\n";
        String expectedResponseHeader = "Content-Length: 0\r\n\r\n";

        Assertions.assertEquals(expectedResponseLine, connectionSpy.responseLine);
        Assertions.assertEquals(expectedResponseHeader, connectionSpy.headers);
    }

    @Test
    void SimpleHeadToHeadRequestTest() {
        String testRequest = "HEAD /head_request HTTP/1.1\r\n";

        InputStream testInputStream = new ByteArrayInputStream(testRequest.getBytes());
        BufferedReader input = new BufferedReader(new InputStreamReader(testInputStream));
        PrintWriter output = new PrintWriter(new StringWriter());

        Router router = Routes.assignRoutes();
        ConnectionSpy connectionSpy = new ConnectionSpy(input, output, router);
        connectionSpy.processRequest();

        String expectedResponseLine = "HTTP/1.1 200 OK\r\n";
        String expectedResponseHeader = "Content-Length: 0\r\n\r\n";

        Assertions.assertEquals(expectedResponseLine, connectionSpy.responseLine);
        Assertions.assertEquals(expectedResponseHeader, connectionSpy.headers);
    }

    @Test
    void MethodNotAllowedTest() {
        String testRequest = "GET /head_request HTTP/1.1\r\n";

        InputStream testInputStream = new ByteArrayInputStream(testRequest.getBytes());
        BufferedReader input = new BufferedReader(new InputStreamReader(testInputStream));
        PrintWriter output = new PrintWriter(new StringWriter());

        Router router = Routes.assignRoutes();
        ConnectionSpy connectionSpy = new ConnectionSpy(input, output, router);
        connectionSpy.processRequest();

        String expectedResponseLine = "HTTP/1.1 405 Method Not Allowed\r\n";
        String expectedResponseHeader = "Allow: HEAD, OPTIONS\r\nContent-Length: 0\r\n\r\n";

        Assertions.assertEquals(expectedResponseLine, connectionSpy.responseLine);
        Assertions.assertEquals(expectedResponseHeader, connectionSpy.headers);
    }

    @Test
    void SimpleOptionsToMethodOptionsTest() {
        String testRequest = "OPTIONS /method_options HTTP/1.1\r\n";

        InputStream testInputStream = new ByteArrayInputStream(testRequest.getBytes());
        BufferedReader input = new BufferedReader(new InputStreamReader(testInputStream));
        PrintWriter output = new PrintWriter(new StringWriter());

        Router router = Routes.assignRoutes();
        ConnectionSpy connectionSpy = new ConnectionSpy(input, output, router);
        connectionSpy.processRequest();

        String expectedResponseLine = "HTTP/1.1 200 OK\r\n";
        String expectedResponseHeader = "Allow: GET, HEAD, OPTIONS\r\nContent-Length: 0\r\n\r\n";

        Assertions.assertEquals(expectedResponseLine, connectionSpy.responseLine);
        Assertions.assertEquals(expectedResponseHeader, connectionSpy.headers);
    }

    @Test
    void SimpleOptionsToMethodOptions2Test() {
        String testRequest = "OPTIONS /method_options2 HTTP/1.1\r\n";

        InputStream testInputStream = new ByteArrayInputStream(testRequest.getBytes());
        BufferedReader input = new BufferedReader(new InputStreamReader(testInputStream));
        PrintWriter output = new PrintWriter(new StringWriter());

        Router router = Routes.assignRoutes();
        ConnectionSpy connectionSpy = new ConnectionSpy(input, output, router);
        connectionSpy.processRequest();

        String expectedResponseLine = "HTTP/1.1 200 OK\r\n";
        String expectedResponseHeader = "Allow: GET, HEAD, OPTIONS, PUT, POST\r\nContent-Length: 0\r\n\r\n";

        Assertions.assertEquals(expectedResponseLine, connectionSpy.responseLine);
        Assertions.assertEquals(expectedResponseHeader, connectionSpy.headers);
    }

    @Test
    void SimpleRedirectTest() {
        String testRequest = "GET /redirect HTTP/1.1\r\n";

        InputStream testInputStream = new ByteArrayInputStream(testRequest.getBytes());
        BufferedReader input = new BufferedReader(new InputStreamReader(testInputStream));
        PrintWriter output = new PrintWriter(new StringWriter());

        Router router = Routes.assignRoutes();
        ConnectionSpy connectionSpy = new ConnectionSpy(input, output, router);
        connectionSpy.processRequest();

        String expectedResponseLine = "HTTP/1.1 301 Moved Permanently\r\n";
        String expectedResponseHeader = "Location: http://127.0.0.1:5000/simple_get\r\nContent-Length: 0\r\n\r\n";

        Assertions.assertEquals(expectedResponseLine, connectionSpy.responseLine);
        Assertions.assertEquals(expectedResponseHeader, connectionSpy.headers);
    }

    @Test
    void ResourceNotFoundTest() {
        String testRequest = "GET /resource_not_found HTTP/1.1\r\n";

        InputStream testInputStream = new ByteArrayInputStream(testRequest.getBytes());
        BufferedReader input = new BufferedReader(new InputStreamReader(testInputStream));
        PrintWriter output = new PrintWriter(new StringWriter());

        Router router = Routes.assignRoutes();
        ConnectionSpy connectionSpy = new ConnectionSpy(input, output, router);
        connectionSpy.processRequest();

        String expectedResponseLine = "HTTP/1.1 404 Not Found\r\n";
        String expectedResponseHeader = "Content-Length: 0\r\n\r\n";

        Assertions.assertEquals(expectedResponseLine, connectionSpy.responseLine);
        Assertions.assertEquals(expectedResponseHeader, connectionSpy.headers);
    }

    @Test
    void TextResponseTest() {
        String testRequest = "GET /text_response HTTP/1.1\r\n";

        InputStream testInputStream = new ByteArrayInputStream(testRequest.getBytes());
        BufferedReader input = new BufferedReader(new InputStreamReader(testInputStream));
        PrintWriter output = new PrintWriter(new StringWriter());

        Router router = Routes.assignRoutes();
        ConnectionSpy connectionSpy = new ConnectionSpy(input, output, router);
        connectionSpy.processRequest();

        String responseText = "text response";

        String expectedResponseLine = "HTTP/1.1 200 OK\r\n";
        String expectedResponseHeader = "Content-Type: text/plain;charset=utf-8\r\nContent-Length: 13\r\n\r\n";
        byte[] expectedResponseBody = responseText.getBytes(StandardCharsets.UTF_8);

        Assertions.assertEquals(expectedResponseLine, connectionSpy.responseLine);
        Assertions.assertEquals(expectedResponseHeader, connectionSpy.headers);
        Assertions.assertArrayEquals(expectedResponseBody, connectionSpy.body);
    }

    @Test
    void HTMLResponseTest() {
        String testRequest = "GET /html_response HTTP/1.1\r\n";

        InputStream testInputStream = new ByteArrayInputStream(testRequest.getBytes());
        BufferedReader input = new BufferedReader(new InputStreamReader(testInputStream));
        PrintWriter output = new PrintWriter(new StringWriter());

        Router router = Routes.assignRoutes();
        ConnectionSpy connectionSpy = new ConnectionSpy(input, output, router);
        connectionSpy.processRequest();

        String responseText = "<html><body><p>HTML Response</p></body></html>";

        String expectedResponseLine = "HTTP/1.1 200 OK\r\n";
        String expectedResponseHeader = "Content-Type: text/html;charset=utf-8\r\nContent-Length: 46\r\n\r\n";
        byte[] expectedResponseBody = responseText.getBytes(StandardCharsets.UTF_8);

        Assertions.assertEquals(expectedResponseLine, connectionSpy.responseLine);
        Assertions.assertEquals(expectedResponseHeader, connectionSpy.headers);
        Assertions.assertArrayEquals(expectedResponseBody, connectionSpy.body);
    }

    @Test
    void JSONResponseTest() {
        String testRequest = "GET /json_response HTTP/1.1\r\n";

        InputStream testInputStream = new ByteArrayInputStream(testRequest.getBytes());
        BufferedReader input = new BufferedReader(new InputStreamReader(testInputStream));
        PrintWriter output = new PrintWriter(new StringWriter());

        Router router = Routes.assignRoutes();
        ConnectionSpy connectionSpy = new ConnectionSpy(input, output, router);
        connectionSpy.processRequest();

        JSONObject responseJson = new JSONObject();
        responseJson.put("key1", "value1");
        responseJson.put("key2", "value2");

        String expectedResponseLine = "HTTP/1.1 200 OK\r\n";
        String expectedResponseHeader = "Content-Type: application/json;charset=utf-8\r\nContent-Length: 33\r\n\r\n";
        byte[] expectedResponseBody = responseJson.toString().getBytes(StandardCharsets.UTF_8);

        Assertions.assertEquals(expectedResponseLine, connectionSpy.responseLine);
        Assertions.assertEquals(expectedResponseHeader, connectionSpy.headers);
        Assertions.assertArrayEquals(expectedResponseBody, connectionSpy.body);
    }

    @Test
    void XMLResponseTest() {
        String testRequest = "GET /xml_response HTTP/1.1\r\n";

        InputStream testInputStream = new ByteArrayInputStream(testRequest.getBytes());
        BufferedReader input = new BufferedReader(new InputStreamReader(testInputStream));
        PrintWriter output = new PrintWriter(new StringWriter());

        Router router = Routes.assignRoutes();
        ConnectionSpy connectionSpy = new ConnectionSpy(input, output, router);
        connectionSpy.processRequest();

        String XMLString = "<note><body>XML Response</body></note>";

        String expectedResponseLine = "HTTP/1.1 200 OK\r\n";
        String expectedResponseHeader = "Content-Type: application/xml;charset=utf-8\r\nContent-Length: 38\r\n\r\n";
        byte[] expectedResponseBody = XMLString.getBytes(StandardCharsets.UTF_8);

        Assertions.assertEquals(expectedResponseLine, connectionSpy.responseLine);
        Assertions.assertEquals(expectedResponseHeader, connectionSpy.headers);
        Assertions.assertArrayEquals(expectedResponseBody, connectionSpy.body);
    }

    @Test
    void HealthCheckTest() {
        String testRequest = "GET /health-check.html HTTP/1.1\r\n";

        InputStream testInputStream = new ByteArrayInputStream(testRequest.getBytes());
        BufferedReader input = new BufferedReader(new InputStreamReader(testInputStream));
        PrintWriter output = new PrintWriter(new StringWriter());

        Router router = Routes.assignRoutes();
        ConnectionSpy connectionSpy = new ConnectionSpy(input, output, router);
        connectionSpy.processRequest();

        String responseString = "<html><body><p><strong>Status:</strong> pass</p></body></html>";

        String expectedResponseLine = "HTTP/1.1 200 OK\r\n";
        String expectedResponseHeader = "Content-Type: text/html;charset=utf-8\r\nContent-Length: 62\r\n\r\n";
        byte[] expectedResponseBody = responseString.getBytes(StandardCharsets.UTF_8);

        Assertions.assertEquals(expectedResponseLine, connectionSpy.responseLine);
        Assertions.assertEquals(expectedResponseHeader, connectionSpy.headers);
        Assertions.assertArrayEquals(expectedResponseBody, connectionSpy.body);
    }

    @Test
    void JPEGImageSendTest() throws IOException {
        String testRequest = "GET /kitteh.jpg HTTP/1.1\r\n";

        InputStream testInputStream = new ByteArrayInputStream(testRequest.getBytes());
        BufferedReader input = new BufferedReader(new InputStreamReader(testInputStream));
        PrintWriter output = new PrintWriter(new StringWriter());

        Router router = Routes.assignRoutes();
        ConnectionSpy connectionSpy = new ConnectionSpy(input, output, router);
        connectionSpy.processRequest();

        String expectedResponseLine = "HTTP/1.1 200 OK\r\n";
        String expectedResponseHeader = "Content-Type: image/jpeg\r\nContent-Length: 207922\r\n\r\n";
        File file = new File("src/test/java/kitteh.jpg");
        byte[] expectedResponseBody = Files.readAllBytes(file.toPath());

        Assertions.assertEquals(expectedResponseLine, connectionSpy.responseLine);
        Assertions.assertEquals(expectedResponseHeader, connectionSpy.headers);
        Assertions.assertArrayEquals(expectedResponseBody, connectionSpy.body);
    }

    @Test
    void PNGImageSendTest() throws IOException {
        String testRequest = "GET /doggo.png HTTP/1.1\r\n";

        InputStream testInputStream = new ByteArrayInputStream(testRequest.getBytes());
        BufferedReader input = new BufferedReader(new InputStreamReader(testInputStream));
        PrintWriter output = new PrintWriter(new StringWriter());

        Router router = Routes.assignRoutes();
        ConnectionSpy connectionSpy = new ConnectionSpy(input, output, router);
        connectionSpy.processRequest();

        String expectedResponseLine = "HTTP/1.1 200 OK\r\n";
        String expectedResponseHeader = "Content-Type: image/png\r\nContent-Length: 351702\r\n\r\n";
        File file = new File("src/test/java/doggo.png");
        byte[] expectedResponseBody = Files.readAllBytes(file.toPath());

        Assertions.assertEquals(expectedResponseLine, connectionSpy.responseLine);
        Assertions.assertEquals(expectedResponseHeader, connectionSpy.headers);
        Assertions.assertArrayEquals(expectedResponseBody, connectionSpy.body);
    }

    @Test
    void GIFImageSendTest() throws IOException {
        String testRequest = "GET /kisses.gif HTTP/1.1\r\n";

        InputStream testInputStream = new ByteArrayInputStream(testRequest.getBytes());
        BufferedReader input = new BufferedReader(new InputStreamReader(testInputStream));
        PrintWriter output = new PrintWriter(new StringWriter());

        Router router = Routes.assignRoutes();
        ConnectionSpy connectionSpy = new ConnectionSpy(input, output, router);
        connectionSpy.processRequest();

        String expectedResponseLine = "HTTP/1.1 200 OK\r\n";
        String expectedResponseHeader = "Content-Type: image/gif\r\nContent-Length: 432985\r\n\r\n";
        File file = new File("src/test/java/kisses.gif");
        byte[] expectedResponseBody = Files.readAllBytes(file.toPath());

        Assertions.assertEquals(expectedResponseLine, connectionSpy.responseLine);
        Assertions.assertEquals(expectedResponseHeader, connectionSpy.headers);
        Assertions.assertArrayEquals(expectedResponseBody, connectionSpy.body);
    }

    @Test
    void createTaskTest() {
        String testRequest = "POST /todo HTTP/1.1\r\n" +
                "Content-Type: application/json\r\n" +
                "Connection: close\r\n" +
                "Host: 127.0.0.1:5000\r\n" +
                "User-Agent: http.rb/4.3.0\r\n" +
                "Content-Length: 21\r\n" +
                "\r\n" +
                "{\"task\":\"a new task\"}";

        InputStream testInputStream = new ByteArrayInputStream(testRequest.getBytes());
        BufferedReader input = new BufferedReader(new InputStreamReader(testInputStream));
        PrintWriter output = new PrintWriter(new StringWriter());

        Router router = Routes.assignRoutes();
        ConnectionSpy connectionSpy = new ConnectionSpy(input, output, router);
        connectionSpy.processRequest();

        String expectedResponseLine = "HTTP/1.1 201 Created\r\n";
        byte[] expectedBody = "{\"task\":\"a new task\"}".getBytes(StandardCharsets.UTF_8);

        Assertions.assertEquals(expectedResponseLine, connectionSpy.responseLine);
        Assertions.assertArrayEquals(expectedBody, connectionSpy.body);
    }

    @Test
    void updateTaskTest() {
        String testRequest = "POST /todo HTTP/1.1\r\n" +
                "Content-Type: application/json\r\n" +
                "Connection: close\r\n" +
                "Host: 127.0.0.1:5000\r\n" +
                "User-Agent: http.rb/4.3.0\r\n" +
                "Content-Length: 21\r\n" +
                "\r\n" +
                "{\"task\":\"a new task\"}";

        InputStream testInputStream = new ByteArrayInputStream(testRequest.getBytes());
        BufferedReader input = new BufferedReader(new InputStreamReader(testInputStream));
        PrintWriter output = new PrintWriter(new StringWriter());

        Router router = Routes.assignRoutes();
        ConnectionSpy connectionSpy = new ConnectionSpy(input, output, router);
        connectionSpy.processRequest();

        String updateRequest = "PUT /todo/1 HTTP/1.1\r\n" +
                "Content-Type: application/json\r\n" +
                "Connection: close\r\n" +
                "Host: 127.0.0.1:5000\r\n" +
                "User-Agent: http.rb/4.3.0\r\n" +
                "Content-Length: 26\r\n" +
                "\r\n" +
                "{\"task\":\"an updated task\"}";

        testInputStream = new ByteArrayInputStream(updateRequest.getBytes());
        input = new BufferedReader(new InputStreamReader(testInputStream));
        output = new PrintWriter(new StringWriter());

        connectionSpy = new ConnectionSpy(input, output, router);
        connectionSpy.processRequest();

        String expectedResponseLine = "HTTP/1.1 200 OK\r\n";
        byte[] expectedResponseBody = "{\"task\":\"an updated task\"}".getBytes(StandardCharsets.UTF_8);

        Assertions.assertEquals(expectedResponseLine, connectionSpy.responseLine);
        Assertions.assertArrayEquals(expectedResponseBody, connectionSpy.body);
    }

    @Test
    void deleteTaskTest() {
        String testRequest = "POST /todo HTTP/1.1\r\n" +
                "Content-Type: application/json\r\n" +
                "Connection: close\r\n" +
                "Host: 127.0.0.1:5000\r\n" +
                "User-Agent: http.rb/4.3.0\r\n" +
                "Content-Length: 21\r\n" +
                "\r\n" +
                "{\"task\":\"a new task\"}";

        InputStream testInputStream = new ByteArrayInputStream(testRequest.getBytes());
        BufferedReader input = new BufferedReader(new InputStreamReader(testInputStream));
        PrintWriter output = new PrintWriter(new StringWriter());

        Router router = Routes.assignRoutes();
        ConnectionSpy connectionSpy = new ConnectionSpy(input, output, router);
        connectionSpy.processRequest();

        String deleteRequest = "DELETE /todo/1 HTTP/1.1\r\n" +
                "Connection: close\r\n" +
                "Host: 127.0.0.1:5000\r\n" +
                "User-Agent: http.rb/4.3.0\r\n" +
                "\r\n";

        testInputStream = new ByteArrayInputStream(deleteRequest.getBytes());
        input = new BufferedReader(new InputStreamReader(testInputStream));
        output = new PrintWriter(new StringWriter());

        connectionSpy = new ConnectionSpy(input, output, router);
        connectionSpy.processRequest();

        String expectedResponseLine = "HTTP/1.1 204 No Content\r\n";

        Assertions.assertEquals(expectedResponseLine, connectionSpy.responseLine);
    }
}
