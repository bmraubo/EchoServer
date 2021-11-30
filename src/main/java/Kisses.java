import java.io.File;
import java.nio.file.Files;

public class Kisses implements Endpoint {
    byte[] responseBody;
    String contentType = "image/gif";

    public Kisses() {
        convertImage();
    }

    @Override
    public Response prepareResponse(Request request) {
        ResponseBuilder responseBuilder = new ResponseBuilder();
        Response response = new Response(responseBuilder);
        responseBuilder.setStatusCode(200);
        responseBuilder.setHeader("Content-Type", contentType);
        responseBuilder.setResponseBody(responseBody);
        return response;
    }

    private void convertImage() {
        try {
            File file = new File("src/main/java/kisses.gif");
            this.responseBody = Files.readAllBytes(file.toPath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
