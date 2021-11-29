import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        int port = 5000;

        ServerSocketWrapper socketWrapper = new ServerSocketWrapper();
        Router router = Routes.assignRoutes();
        Server server = new Server(socketWrapper, router);
        System.out.println("Server Started");
        server.start(port);
    }
}
