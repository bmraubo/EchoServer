import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestJSONResponse {

    @Test
    void jsonResponseTest() {
        JSONResponse testJSONResponse = new JSONResponse();
        Response testResponse = testJSONResponse.prepareResponse();

        JSONObject testJSONObject = new JSONObject();
        testJSONObject.put("key1", "value1");
        testJSONObject.put("key2", "value2");

        Assertions.assertEquals(200, testResponse.statusCode);
        Assertions.assertEquals("Content-Type: application/json;charset=utf-8", testResponse.contentType);
        Assertions.assertEquals(testJSONObject.toString(), testResponse.body);
    }
}
