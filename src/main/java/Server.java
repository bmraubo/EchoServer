import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws Exception{
        int port = 5000;
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("server started");

        while (true) {
            //set up client socket
            Socket socket = serverSocket.accept();
            System.out.println("connection made");
            //open IO stream
            InputStreamReader inputStream = new InputStreamReader(socket.getInputStream());
            BufferedReader input = new BufferedReader(inputStream);
            PrintWriter output = new PrintWriter(socket.getOutputStream(),true);
            //wait for data
            String data;
            while ((data = input.readLine()) != null) {
                // do something with the data
                output.println(data);
            }
            //close IO streams then socket
            output.close();
            input.close();
            socket.close();
        }
    }

}
