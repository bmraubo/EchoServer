package site.bmraubo.HTTPServer;

public interface Endpoint {

    Response prepareResponse(Request request);
}
