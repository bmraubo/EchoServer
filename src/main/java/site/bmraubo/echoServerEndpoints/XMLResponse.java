package site.bmraubo.echoServerEndpoints;

import site.bmraubo.HTTPServer.Endpoint;
import site.bmraubo.HTTPServer.Request;
import site.bmraubo.HTTPServer.Response;
import site.bmraubo.HTTPServer.ResponseBuilder;

public class XMLResponse implements Endpoint {
    String contentType = "application/xml;charset=utf-8";
    String responseBody = "<note><body>XML Response</body></note>";

    @Override
    public Response prepareResponse(Request request) {
        ResponseBuilder responseBuilder = new ResponseBuilder();
        Response response = new Response(responseBuilder);
        responseBuilder.setStatusCode(200);
        responseBuilder.setHeader("Content-Type", contentType);
        responseBuilder.setResponseBody(responseBody);
        return response;
    }

}
