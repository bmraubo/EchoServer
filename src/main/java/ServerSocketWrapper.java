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
        Socket socket = serverSocket.accept();
        System.out.println("Connection accepted");
        InputStreamReader inputStream = new InputStreamReader(socket.getInputStream());
        input = new BufferedReader(inputStream);
        output = new PrintWriter(socket.getOutputStream(), true);
        System.out.println("I/O Streams opened");
    }

    @Override
    public String readRequestData() throws IOException {
        String data = "";
        int value;
        while ((value = input.read()) != -1) {
            data = data + Character.toString(value);
        }
        System.out.println("Data received");
        System.out.println(data);
        return data;
    }


    @Override
    public void sendResponseData(String responseData) {
        System.out.println("Sending Response...");
        output.print(responseData);
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
