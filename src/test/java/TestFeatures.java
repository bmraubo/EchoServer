import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;

public class TestFeatures {

    @Test
    void SimpleGetTest() {
        int port = 5000;

        String testRequest = "GET /simple_get HTTP/1.1\r\n";

        BufferedReader input = new BufferedReader(new StringReader(testRequest));
        PrintWriter output = new PrintWriter(new StringWriter());

        SocketWrapperSpy socketWrapper = new SocketWrapperSpy(input, output);
        Server testServer = new Server(socketWrapper);

        testServer.start(port);

        Assertions.assertEquals("HTTP/1.1 200 OK\r\n", socketWrapper.sentResponse);
    }
}
