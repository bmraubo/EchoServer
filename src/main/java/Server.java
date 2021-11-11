import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws Exception {
        String responseMessage = "Hello There!";
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
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
            //wait for data
            String data = "";
            while ((input.ready())) {
                data = data + Character.toString(input.read());
            }
            System.out.println(data);
            Request request = new Request(data);
            sendTestResponse(request, output);
            // Send Response - response will just be 200 OK
            /* HTTP Response Standard:
            Status Line
                Consists of: {Protocol Version} SP (space) {Status Code} SP {Reason Phrase} SP CRLF
            General Header - Optional
            Response Header - Optional
            Entity Header - Optional
            CRLF (Carriage Return and Line Feed - \r\n)
            Message Body - this will bounce back the Request Body
             */
            //}
            //close IO streams then socket
            closeConnection(input, output, socket);
        }
    }

    public static void sendTestResponse(Request request, PrintWriter output) {
        String crlf = "\r\n";
        String responseStatus = "HTTP/1.1 200 OK " + crlf;
        String responseBody = request.body;
        int responseContentLength = responseBody.length();
        String responseContentHeader = "Content-Length:" + String.format("%d", responseContentLength) + crlf;
        String responseConnectionStatus = "close";
        String responseConnectionHeader = "Connection:" + responseConnectionStatus;
        output.println(responseStatus + responseContentHeader + responseConnectionHeader + crlf + responseBody);
        System.out.println("Response has been sent");
    }

    public static void closeConnection(BufferedReader input, PrintWriter output, Socket socket) throws Exception{
        output.close();
        input.close();
        socket.close();
        System.out.println("Socket has been closed");
    }

}
