import java.io.File;
import java.nio.file.Files;

public class Doggo implements RoutingInterface{
    byte[] responseBody;
    String contentType = "image/png";

    public Doggo() {
        convertImage();
    }

    @Override
    public Response prepareResponse() {
        TextResponseBuilder responseBuilder = new TextResponseBuilder();
        Response response = new Response(responseBuilder);
        responseBuilder.setStatusCode(200);
        responseBuilder.setHeader("Content-Type", contentType);
        responseBuilder.setResponseBody(responseBody);
        return response;
    }

    private void convertImage() {
        try {
            File file = new File("src/main/java/doggo.png");
            this.responseBody = Files.readAllBytes(file.toPath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
