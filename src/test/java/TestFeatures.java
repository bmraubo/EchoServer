import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;

public class TestFeatures {

    @Test
    void SimpleGetTest() throws IOException {
        int port = 5000;

        String testRequest = "GET /simple_get HTTP/1.1\r\n";

        InputStream testInputStream = new ByteArrayInputStream(testRequest.getBytes());
        BufferedReader input = new BufferedReader(new InputStreamReader(testInputStream));
        PrintWriter output = new PrintWriter(new StringWriter());

        SocketWrapperSpy socketWrapper = new SocketWrapperSpy(input, output);
        Server testServer = new Server(socketWrapper);

        testServer.start(port);

        Assertions.assertEquals("HTTP/1.1 200 OK\r\nConnection: close\r\n", socketWrapper.sentResponse);
    }

    @Test
    void SimpleGetWithBodyTest() throws IOException {
        int port = 5000;

        String testRequest = "GET /simple_get_with_body HTTP/1.1\r\nContent-Length: 11\r\n\r\nHello World";

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
    void SimplePostTest() throws IOException {
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
    void SimpleHeadToSimpleGetTest() throws IOException{
        int port = 5000;

        String testRequest = "HEAD /simple_get HTTP/1.1\r\n";

        InputStream testInputStream = new ByteArrayInputStream(testRequest.getBytes());
        BufferedReader input = new BufferedReader(new InputStreamReader(testInputStream));
        PrintWriter output = new PrintWriter(new StringWriter());

        SocketWrapperSpy socketWrapper = new SocketWrapperSpy(input, output);
        Server testServer = new Server(socketWrapper);

        testServer.start(port);

        Assertions.assertEquals("HTTP/1.1 200 OK\r\nConnection: close\r\n", socketWrapper.sentResponse);
    }

    @Test
    void SimpleHeadToSimpleHeadTest() throws IOException{
        int port = 5000;

        String testRequest = "HEAD /simple_head HTTP/1.1\r\n";

        InputStream testInputStream = new ByteArrayInputStream(testRequest.getBytes());
        BufferedReader input = new BufferedReader(new InputStreamReader(testInputStream));
        PrintWriter output = new PrintWriter(new StringWriter());

        SocketWrapperSpy socketWrapper = new SocketWrapperSpy(input, output);
        Server testServer = new Server(socketWrapper);

        testServer.start(port);

        Assertions.assertEquals("HTTP/1.1 200 OK\r\nConnection: close\r\n", socketWrapper.sentResponse);
    }
}
