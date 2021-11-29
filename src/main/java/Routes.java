public class Routes {

    static Router assignRoutes() {
        Router router = new Router();
        router.routes.put("/simple_get", new SimpleGet());
        router.routes.put("/simple_get_with_body", new SimpleGetWithBody());
        router.routes.put("/echo_body", new EchoBody());
        router.routes.put("/head_request", new HeadRequest());
        router.routes.put("/method_options", new MethodOptions());
        router.routes.put("/method_options2", new MethodOptions2());
        router.routes.put("/redirect", new Redirect());
        router.routes.put("/text_response", new TextResponse());
        router.routes.put("/html_response", new HTMLResponse());
        router.routes.put("/json_response", new JSONResponse());
        router.routes.put("/xml_response", new XMLResponse());
        router.routes.put("/health-check.html", new HealthCheck());
        router.routes.put("/kitteh.jpg", new Kitteh());
        router.routes.put("/doggo.png", new Doggo());
        router.routes.put("/kisses.gif", new Kisses());
        return router;
    }
}
