import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestTextResponseBuilder {

    @Test
    void createTextResponseBuilder() {
        TextResponseBuilder testResponseBuilder = new TextResponseBuilder();

        Assertions.assertNotNull(testResponseBuilder.statusCodeMap);
        Assertions.assertEquals("OK", testResponseBuilder.statusCodeMap.get(200));
    }

    @Test
    void set200StatusCodeTest() {
        int statusCode = 200;

        TextResponseBuilder testResponseBuilder = new TextResponseBuilder();
        testResponseBuilder.setStatusCode(statusCode);

        Assertions.assertEquals(200, testResponseBuilder.statusCode);
        Assertions.assertEquals("OK", testResponseBuilder.reasonPhrase);
    }
}
