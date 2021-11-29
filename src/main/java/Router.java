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
        RoutingInterface endpoint = findRoute(request);
        return endpoint.prepareResponse(request); // will need to have all endpoints take the whole Request for this to work.
    }

    private RoutingInterface findRoute(Request request) {
        for (String key : routes.keySet()) {
            if (request.uri.equals(key)) {
                return routes.get(key);
            }
        }
        return new ResourceNotFound();
    }

    private LinkedHashMap<String, RoutingInterface> generateRouteMap() {
        return new LinkedHashMap<String, RoutingInterface>();
    }

    static Response serverError(String errorReason, Request request) {
        ServerError error = new ServerError(errorReason);
        return error.prepareResponse(request);
    }
}
