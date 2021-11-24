import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;

public class TestServerSocket {
    BufferedReader input;
    PrintWriter output;


    @Test
    void socketCreationTest() throws IOException, InterruptedException {

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
    void AcceptConnectionTest() throws IOException, InterruptedException {
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
    void ReadRequestDataTest() throws IOException, InterruptedException {

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
    void ReadRequestNullDataTest() throws IOException, InterruptedException {

        byte[] testRequest = new byte[0];

        int port = 5000;

        InputStream testInputStream = new ByteArrayInputStream(testRequest);
        BufferedReader input = new BufferedReader(new InputStreamReader(testInputStream));
        PrintWriter output = new PrintWriter(new StringWriter());

        SocketWrapperSpy socketWrapper = new SocketWrapperSpy(input, output);
        Server testServer = new Server(socketWrapper);

        testServer.start(port);

        Assertions.assertTrue(socketWrapper.nullInputReceived);
    }

    @Test
    void SocketSendDataTest() throws IOException, InterruptedException {
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
    void SocketSwitchToImageOutputTest() throws IOException {
        String testRequest = "GET /simple_get HTTP/1.1\r\n";

        InputStream testInputStream = new ByteArrayInputStream(testRequest.getBytes());
        BufferedReader input = new BufferedReader(new InputStreamReader(testInputStream));
        PrintWriter output = new PrintWriter(new StringWriter());

        SocketWrapperSpy socketWrapper = new SocketWrapperSpy(input, output);

        Response testResponse = new Response();
        testResponse.setContentType("image/jpg");

        socketWrapper.sendResponseData(testResponse);

        Assertions.assertTrue(socketWrapper.imageOutputSelected);
    }

    @Test
    void SocketCloseTest() throws IOException, InterruptedException {
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
