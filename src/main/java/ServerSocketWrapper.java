import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketWrapper implements SocketWrapper{
    ServerSocket serverSocket;
    BufferedReader input;
    PrintWriter output;

    @Override
    public void createSocket(int port) {
        try {
            serverSocket = new ServerSocket(port);
            Socket socket = serverSocket.accept();
            InputStreamReader inputStream = new InputStreamReader(socket.getInputStream());
            input = new BufferedReader(inputStream);
            output = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            System.out.println(e);
        }

    }

    @Override
    public String readRequestData() {
        return null;
    }


    @Override
    public void sendResponseData(String responseData) {

    }

    @Override
    public void closeSocket() {

    }
}
