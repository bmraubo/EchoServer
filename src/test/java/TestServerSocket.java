import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;

public class TestServerSocket {
    BufferedReader input;
    PrintWriter output;


    @Test
    void socketCreationTest() throws IOException {

        String testRequest = "GET /simple_get HTTP/1.1\r\n";

        int port = 5000;

        InputStream testInputStream = new ByteArrayInputStream(testRequest.getBytes());
        BufferedReader input = new BufferedReader(new InputStreamReader(testInputStream));
        PrintWriter output = new PrintWriter(new StringWriter());

        SocketWrapperSpy socketWrapper = new SocketWrapperSpy(input, output);
        Server testServer = new Server(socketWrapper);

        testServer.start(port);

        Assertions.assertTrue(socketWrapper.wasCreated());
    }

    @Test
    void AcceptConnectionTest() throws IOException {
        String testRequest = "GET /simple_get HTTP/1.1\r\n";

        int port = 5000;

        InputStream testInputStream = new ByteArrayInputStream(testRequest.getBytes());
        BufferedReader input = new BufferedReader(new InputStreamReader(testInputStream));
        PrintWriter output = new PrintWriter(new StringWriter());

        SocketWrapperSpy socketWrapper = new SocketWrapperSpy(input, output);
        Server testServer = new Server(socketWrapper);

        testServer.start(port);

        Assertions.assertTrue(socketWrapper.connectionAccepted);
    }

    @Test
    void ReadRequestDataTest() throws IOException {

        String testRequest = "GET /simple_get HTTP/1.1\r\n";

        int port = 5000;

        InputStream testInputStream = new ByteArrayInputStream(testRequest.getBytes());
        BufferedReader input = new BufferedReader(new InputStreamReader(testInputStream));
        PrintWriter output = new PrintWriter(new StringWriter());

        SocketWrapperSpy socketWrapper = new SocketWrapperSpy(input, output);
        Server testServer = new Server(socketWrapper);

        testServer.start(port);

        Assertions.assertEquals(testRequest, socketWrapper.dataReceived);
    }

    @Test
    void SocketSendDataTest() throws IOException {
        String testRequest = "GET /simple_get HTTP/1.1\r\n";

        int port = 5000;

        InputStream testInputStream = new ByteArrayInputStream(testRequest.getBytes());
        BufferedReader input = new BufferedReader(new InputStreamReader(testInputStream));
        PrintWriter output = new PrintWriter(new StringWriter());

        SocketWrapperSpy socketWrapper = new SocketWrapperSpy(input, output);
        Server testServer = new Server(socketWrapper);

        testServer.start(port);

        Assertions.assertTrue(socketWrapper.dataSent);
        Assertions.assertEquals("HTTP/1.1 200 OK\r\nConnection: close\r\nContent-Length: 0\r\n\r\n", socketWrapper.sentResponse);

    }

    @Test
    void SocketCloseTest() throws IOException {
        String testRequest = "GET /simple_get HTTP/1.1\r\n";

        int port = 5000;

        InputStream testInputStream = new ByteArrayInputStream(testRequest.getBytes());
        BufferedReader input = new BufferedReader(new InputStreamReader(testInputStream));
        PrintWriter output = new PrintWriter(new StringWriter());

        SocketWrapperSpy socketWrapper = new SocketWrapperSpy(input, output);
        Server testServer = new Server(socketWrapper);

        testServer.start(port);

        Assertions.assertTrue(socketWrapper.socketClosed);
    }
}
