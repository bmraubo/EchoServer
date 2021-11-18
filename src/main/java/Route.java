public class Route {

    static Response routeConnection(Request request) {
        switch (request.uri) {
            case ("/simple_get"):
                SimpleGet simpleGet = new SimpleGet(request.method, request.body);
                return simpleGet.prepareResponse();
            case ("/simple_get_with_body"):
                SimpleGetWithBody simpleGetWithBody = new SimpleGetWithBody(request.method, request.body);
                return simpleGetWithBody.prepareResponse();
            case ("/echo_body"):
                return EchoBody.prepareResponse(request);
            case ("/head_request"):
                return HeadRequest.prepareResponse(request);
            case ("/method_options"):
                return MethodOptions.prepareResponse(request);
            case ("/method_options2"):
                return MethodOptions2.prepareResponse(request);
        }
        return ResourceNotFound.prepareResponse(request);
    }
}
