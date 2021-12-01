package site.bmraubo.echoServerEndpoints;

import org.json.JSONObject;
import site.bmraubo.HTTPServer.Endpoint;
import site.bmraubo.HTTPServer.Request;
import site.bmraubo.HTTPServer.Response;
import site.bmraubo.HTTPServer.ResponseBuilder;

public class JSONResponse implements Endpoint {
    JSONObject responseBody = generateJSONResponse();
    String contentType = "application/json;charset=utf-8";

    @Override
    public Response prepareResponse(Request request) {
        ResponseBuilder responseBuilder = new ResponseBuilder();
        Response response = new Response(responseBuilder);
        responseBuilder.setStatusCode(200);
        responseBuilder.setHeader("Content-Type", contentType);
        responseBuilder.setResponseBody(responseBody.toString());
        return response;
    }

    private JSONObject generateJSONResponse() {
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("key1", "value1");
        jsonResponse.put("key2", "value2");
        return jsonResponse;
    }
}
