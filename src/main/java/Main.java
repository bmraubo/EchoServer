import site.bmraubo.HTTPServer.Router;
import site.bmraubo.HTTPServer.Server;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        int port = 5000;

        Router router = Routes.assignRoutes();
        Server server = new Server(router);
        System.out.println("Server Started");
        server.start(port);
    }
}
