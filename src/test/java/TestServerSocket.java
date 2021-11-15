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

        BufferedReader input = new BufferedReader(new StringReader(testRequest));
        PrintWriter output = new PrintWriter(new StringWriter());

        SocketWrapperSpy socketWrapper = new SocketWrapperSpy(input, output);
        Server testServer = new Server(socketWrapper);

        testServer.start(port);

        Assertions.assertTrue(socketWrapper.wasCreated());
    }

    @Test
    void ReadRequestDataTest() throws IOException {

        String testRequest = "GET /simple_get HTTP/1.1\r\n";

        int port = 5000;

        BufferedReader input = new BufferedReader(new StringReader(testRequest));
        PrintWriter output = new PrintWriter(new StringWriter());

        SocketWrapperSpy socketWrapper = new SocketWrapperSpy(input, output);
        Server testServer = new Server(socketWrapper);

        testServer.start(port);

        Assertions.assertEquals(testRequest, socketWrapper.dataReceived);
    }

    @Test
    void SocketSendData() throws IOException {
        String testRequest = "GET /simple_get HTTP/1.1\r\n";

        int port = 5000;

        BufferedReader input = new BufferedReader(new StringReader(testRequest));
        PrintWriter output = new PrintWriter(new StringWriter());

        SocketWrapperSpy socketWrapper = new SocketWrapperSpy(input, output);
        Server testServer = new Server(socketWrapper);

        testServer.start(port);

        String responseData = "Hello World\r\n";
        socketWrapper.sendResponseData(responseData);

        Assertions.assertTrue(socketWrapper.dataSent);

    }
}
