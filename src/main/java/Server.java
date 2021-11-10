import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws Exception{
        int port = 5000;
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("server started");
        System.out.println(InetAddress.getLocalHost());

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
                int contentLength = 0;
                System.out.println(data);
                if (data.startsWith("Content-Length:")) {
                    contentLength = Integer.valueOf(data.substring(data.indexOf(' ') + 1, data.length()));
                    //contentLength = contentLength + 2; //hardcoded - but there is an escape character somewhere
                }
                if (contentLength != 0) {
                    System.out.println("Data Length: "+data.length());
                    char[] body = new char[data.length()]; //using contentLength requires hardcoded +2 modifier
                    input.read(body, 0, data.length()); //using contentLength requires hardcoded +2 modifier
                    String requestBody = new String(body).trim();
                    //System.out.println("body: "+body);
                    System.out.println("Request Body: "+requestBody);
                    System.out.println("Request Body Length: "+requestBody.length());
                }

                //output.println(data);
            }
            //close IO streams then socket
            output.close();
            input.close();
            socket.close();
            System.out.println("Socket has been closed");
        }
    }

}
