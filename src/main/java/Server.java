public class Server {
    SocketWrapper socketWrapper;

    public Server(SocketWrapper socketWrapper) {
        this.socketWrapper = socketWrapper;
    }

    public void start(int port) {
        socketWrapper.createSocket(port);
    }
}
