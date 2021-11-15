import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestServerSocket {

    @Test
    void testSocketCreation() {

        int port = 5000;

        MockSocketWrapper socketWrapper = new MockSocketWrapper(input, output);
        Server testServer = new Server(socketWrapper);

        testServer.start(port);

        Assertions.assertTrue(socketWrapper.wasCreated());
    }
}
