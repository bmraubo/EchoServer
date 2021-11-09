import java.net.ServerSocket;

public class Server {

    public static void main(String[] args) throws Exception{
        int port = 5000;
        ServerSocket socket = new ServerSocket(port);
        System.out.println("server started");

    }

}
