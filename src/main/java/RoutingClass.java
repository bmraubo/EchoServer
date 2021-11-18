public class RoutingClass {

    static Response routeConnection(Request request) {
        switch (request.uri) {
            case ("/simple_get"):
                return SimpleGet.prepareResponse(request);
            case ("/simple_get_with_body"):
                SimpleGetWithBody route = new SimpleGetWithBody(request.method, request.body);
                return route.prepareResponse();
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
