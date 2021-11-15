import java.io.BufferedReader;
import java.io.PrintWriter;

public class SocketWrapperSpy implements SocketWrapper{
    BufferedReader input;
    PrintWriter output;
    boolean socketCreated;
    boolean dataSent;
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
    public String readRequestData() {
        return null;
    }

    @Override
    public void sendResponseData(String responseData) {
        output.print(responseData);
        sentResponse = responseData;
        dataSent = true;
    }

    @Override
    public void closeSocket() {

    }

    public boolean wasCreated() {
        return socketCreated;
    }
}
