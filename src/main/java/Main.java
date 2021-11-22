import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        int port = 5000;

        ServerSocketWrapper socketWrapper = new ServerSocketWrapper();
        Server server = new Server(socketWrapper);
        System.out.println("Server Started");
        server.start(port);
    }
}
