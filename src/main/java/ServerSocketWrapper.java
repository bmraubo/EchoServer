import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketWrapper implements SocketWrapper{
    boolean keepAlive = true;
    ServerSocket serverSocket;
    Socket socket;
    BufferedReader input;
    PrintWriter output;
    PrintWriter otherOutput;


    @Override
    public void createSocket(int port) throws IOException {
        serverSocket = new ServerSocket(port, 1, InetAddress.getByName("0.0.0.0"));
    }

    @Override
    public void acceptConnection() throws IOException, InterruptedException {
        int timeoutCounter = 0;
        socket = serverSocket.accept();
        System.out.println("Connection accepted");
        while ((socket.getInputStream().available() == 0) && (timeoutCounter < 1000)) {
            System.out.println("Input Stream not available - Waiting...");
            Thread.sleep(5);
            timeoutCounter++;
        }
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
    public void sendResponseData(Response response) {
        if (response.contentType != null && response.contentType.contains("image/")) {
            sendImageResponseData(response);
        } else {
            sendTextResponseData(response);
        }
    }

    @Override
    public void sendImageResponseData(Response response) {
        System.out.println("Image Output");
    }

    @Override
    public void sendTextResponseData(Response response) {
        System.out.println("Sending Response...");
        output.print(response.generateResponseLine());
        output.print(response.generateHeaders());
        if (response.sendBody) {
            output.print(response.body);
            output.flush();
        }
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
