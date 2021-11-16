import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketWrapper implements SocketWrapper{
    boolean keepAlive = true;
    ServerSocket serverSocket;
    Socket socket;
    BufferedReader input;
    PrintWriter output;

    @Override
    public void createSocket(int port) throws IOException {
        serverSocket = new ServerSocket(port);
    }

    @Override
    public void acceptConnection() throws IOException {
        socket = serverSocket.accept();
        System.out.println("Connection accepted");
        InputStreamReader inputStream = new InputStreamReader(socket.getInputStream());
        input = new BufferedReader(inputStream);
        output = new PrintWriter(socket.getOutputStream(), true);
        System.out.println("I/O Streams opened");
    }

    @Override
    public String readRequestData() throws IOException {
        System.out.println("looking at data");
        String data = "";
        int value;
        while ((input.ready())) {
            data = data + Character.toString(input.read());
        }
        System.out.println("Data read");
        System.out.println(data);
        return data;
    }


    @Override
    public void sendResponseData(String responseData) {
        System.out.println("Sending Response...");
        System.out.println(responseData);
        output.print(responseData);
        output.flush();
    }

    @Override
    public void closeSocket() throws IOException {
        System.out.println("Closing IO Streams and Socket");
        input.close();
        output.close();
        socket.close();
    }

    @Override
    public boolean keepAlive() {
        return keepAlive;
    }
}
