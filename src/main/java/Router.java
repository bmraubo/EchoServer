import java.util.HashMap;
import java.util.LinkedHashMap;

public class Router {
    HashMap<String, RoutingInterface> routes;

    public Router() {
        routes = generateRouteMap();
    }

    public void addRoute(String uri, RoutingInterface endpoint) {
        routes.put(uri, endpoint);
    }

    public Response connect(Request request) {
        RoutingInterface endpoint = findRoute(request.uri);
        return endpoint.prepareResponse(request); // will need to have all endpoints take the whole Request for this to work.
    }

    private RoutingInterface findRoute(String uri) {
        for (String key : routes.keySet()) {
            if (uri.equals(key)) {
                return routes.get(key);
            }
        }
        return null; // will return Resource not Found
    }

    private LinkedHashMap<String, RoutingInterface> generateRouteMap() {
        return new LinkedHashMap<String, RoutingInterface>();
    }

    static Response routeConnection(Request request) {
        switch (request.uri) {
            case ("/simple_get"):
                SimpleGet simpleGet = new SimpleGet();
                return simpleGet.prepareResponse(request);
            case ("/simple_get_with_body"):
                SimpleGetWithBody simpleGetWithBody = new SimpleGetWithBody();
                return simpleGetWithBody.prepareResponse(request);
            case ("/echo_body"):
                EchoBody echoBody = new EchoBody();
                return echoBody.prepareResponse(request);
            case ("/head_request"):
                HeadRequest headRequest = new HeadRequest();
                return headRequest.prepareResponse(request);
            case ("/method_options"):
                MethodOptions methodOptions = new MethodOptions();
                return methodOptions.prepareResponse(request);
            case ("/method_options2"):
                MethodOptions2 methodOptions2 = new MethodOptions2();
                return methodOptions2.prepareResponse();
            case ("/redirect"):
                Redirect redirect = new Redirect(request.method);
                return redirect.prepareResponse();
            case ("/text_response"):
                TextResponse textResponse = new TextResponse();
                return textResponse.prepareResponse();
            case ("/html_response"):
                HTMLResponse htmlResponse = new HTMLResponse();
                return htmlResponse.prepareResponse();
            case ("/json_response"):
                JSONResponse jsonResponse = new JSONResponse();
                return jsonResponse.prepareResponse();
            case ("/xml_response"):
                XMLResponse xmlResponse = new XMLResponse();
                return xmlResponse.prepareResponse();
            case ("/health-check.html"):
                HealthCheck healthCheck = new HealthCheck();
                return healthCheck.prepareResponse();
            case ("/kitteh.jpg"):
                Kitteh kitteh = new Kitteh();
                return kitteh.prepareResponse();
            case ("/doggo.png"):
                Doggo doggo = new Doggo();
                return doggo.prepareResponse();
            case ("/kisses.gif"):
                Kisses kisses = new Kisses();
                return kisses.prepareResponse();
        }
        return ResourceNotFound.prepareResponse(request);
    }

    static Response serverError(String errorReason) {
        ServerError error = new ServerError(errorReason);
        return error.prepareResponse();
    }
}
