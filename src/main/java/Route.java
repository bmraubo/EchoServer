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
                EchoBody echoBody = new EchoBody(request.method, request.body);
                return echoBody.prepareResponse();
            case ("/head_request"):
                HeadRequest headRequest = new HeadRequest(request.method, request.body);
                return headRequest.prepareResponse();
            case ("/method_options"):
                MethodOptions methodOptions = new MethodOptions();
                return methodOptions.prepareResponse();
            case ("/method_options2"):
                return MethodOptions2.prepareResponse(request);
        }
        return ResourceNotFound.prepareResponse(request);
    }
}
