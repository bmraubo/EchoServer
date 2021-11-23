import org.json.JSONObject;

public class JSONResponse implements RoutingInterface{
    JSONObject responseBody = generateJSONResponse();
    String contentType = "application/json;charset=utf-8";

    @Override
    public Response prepareResponse() {
        Response response = new Response();
        response.setStatusCode(200);
        response.setContentType(contentType);
        response.addResponseBody(responseBody.toString());
        return response;
    }

    private JSONObject generateJSONResponse() {
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("body", "json");
        return jsonResponse;
    }
}
