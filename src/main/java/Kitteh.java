import java.io.File;
import java.nio.file.Files;

public class Kitteh implements RoutingInterface {
    byte[] responseBody;
    String contentType = "image/jpeg";

    public Kitteh() {
        convertImage();
    }

    @Override
    public Response prepareResponse() {
        Response response = new Response();
        response.setStatusCode(200);
        response.setContentType(contentType);
        response.addResponseBody(responseBody);
        return response;
    }

    private void convertImage() {
        try {
            File file = new File("src/main/java/kitteh.jpg");
            this.responseBody = Files.readAllBytes(file.toPath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
