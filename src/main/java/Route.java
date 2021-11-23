public class Route {

    static Response routeConnection(Request request) {
        switch (request.uri) {
            case ("/simple_get"):
                SimpleGet simpleGet = new SimpleGet(request.method);
                return simpleGet.prepareResponse();
            case ("/simple_get_with_body"):
                SimpleGetWithBody simpleGetWithBody = new SimpleGetWithBody(request.method);
                return simpleGetWithBody.prepareResponse();
            case ("/echo_body"):
                EchoBody echoBody = new EchoBody(request.method, request.body);
                return echoBody.prepareResponse();
            case ("/head_request"):
                HeadRequest headRequest = new HeadRequest(request.method);
                return headRequest.prepareResponse();
            case ("/method_options"):
                MethodOptions methodOptions = new MethodOptions();
                return methodOptions.prepareResponse();
            case ("/method_options2"):
                MethodOptions2 methodOptions2 = new MethodOptions2();
                return methodOptions2.prepareResponse();
            case ("/redirect"):
                Redirect redirect = new Redirect(request.method);
                return redirect.prepareResponse();
            case ("/text_response"):
                TextResponse textResponse = new TextResponse();
                return textResponse.prepareResponse();
        }
        return ResourceNotFound.prepareResponse(request);
    }

    static Response serverError(String errorReason) {
        ServerError error = new ServerError(errorReason);
        return error.prepareResponse();
    }
}
