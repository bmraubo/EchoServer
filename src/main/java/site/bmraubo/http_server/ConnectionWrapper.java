package site.bmraubo.http_server;

public interface ConnectionWrapper {

    void processRequest();
    void openIOStreams();
    void buildRequest();
    // void closeConnection();



}
