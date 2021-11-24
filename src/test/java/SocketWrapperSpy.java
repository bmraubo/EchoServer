import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class SocketWrapperSpy implements SocketWrapper{
    boolean keepAlive = true;
    BufferedReader input;
    PrintWriter output;
    boolean socketCreated;
    boolean connectionAccepted;
    boolean dataSent;
    boolean socketClosed;
    String dataReceived;
    String sentResponse;
    boolean nullInputReceived;
    boolean imageOutputSelected;
    byte[] responseBodyImage;

    public SocketWrapperSpy(BufferedReader input, PrintWriter output) {
        this.input = input;
        this.output = output;
    }

    @Override
    public void createSocket(int port) {
        this.socketCreated = true;
    }

    @Override
    public void acceptConnection() throws IOException {
        System.out.println("Connection accepted");
        System.out.println("I/O Streams opened");
        this.connectionAccepted = true;
    }


    @Override
    public String readRequestData() throws IOException {
        String data = "";
        int value;
        while (input.ready()) {
            data = data + Character.toString(input.read());
        }
        if (data.equals("")) {
            nullInputReceived = true;
            System.out.println("Null input received, closing connection...");
            return data;
        }
        dataReceived = data;
        System.out.println("Data received: "+ data);
        return data;
    }

    @Override
    public void sendResponseData(Response response) throws IOException {
        if (response.contentType != null && response.contentType.contains("image/")) {
            sendImageResponseData(response);
        } else {
            sendTextResponseData(response);
        }
        dataSent = true;
    }

    @Override
    public void sendImageResponseData(Response response) throws IOException {
        imageOutputSelected = true;
        System.out.println("Sending Response...");
        output.print(response.generateResponseLine());
        sentResponse = response.generateResponseLine();
        output.print(response.generateHeaders());
        sentResponse = sentResponse + response.generateHeaders();
        responseBodyImage = response.imageBody;
    }

    @Override
    public void sendTextResponseData(Response response) {
        System.out.println("Sending Response...");
        output.print(response.generateResponseLine());
        sentResponse = response.generateResponseLine();
        output.print(response.generateHeaders());
        sentResponse = sentResponse + response.generateHeaders();
        if (response.sendBody) {
            output.print(response.body);
            sentResponse = sentResponse + response.body;
        }
    }

    @Override
    public void closeSocket() throws IOException {
        System.out.println("Closing IO Streams and Socket");
        input.close();
        output.close();
        socketClosed = true;
        keepAlive = false;
    }

    @Override
    public boolean keepAlive() {
        return keepAlive;
    }

    public boolean wasCreated() {
        return socketCreated;
    }
}
