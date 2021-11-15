import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class SocketWrapperSpy implements SocketWrapper{
    BufferedReader input;
    PrintWriter output;
    boolean socketCreated;
    boolean dataSent;
    boolean socketClosed;
    String dataReceived;
    String sentResponse;

    public SocketWrapperSpy(BufferedReader input, PrintWriter output) {
        this.input = input;
        this.output = output;
    }

    @Override
    public void createSocket(int port) {
        this.socketCreated = true;
    }

    @Override
    public String readRequestData() throws IOException {
        String data = "";
        int value;
        while ((value = input.read()) != -1) {
            data = data + Character.toString(value);
        }
        dataReceived = data;
        return data;
    }

    @Override
    public void sendResponseData(String responseData) {
        output.print(responseData);
        sentResponse = responseData;
        dataSent = true;
    }

    @Override
    public void closeSocket() {
        input.close();
        output.close();
        socketClosed = true;
    }

    public boolean wasCreated() {
        return socketCreated;
    }
}
