import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Kitteh {

    private byte[] convertImage() throws IOException {
        File file = new File("kitteh.jpg");
        return Files.readAllBytes(file.toPath());
    }
}
