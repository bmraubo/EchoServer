public class TextResponse implements RoutingInterface{
    String contentType = "text/plain";
    String responseBody = "Aedh Wishes for the Cloths of Heaven\n"+
            "WB YEATS\n\n" +
            "Had I the heavens' embroidered cloths,\n" +
            "Enwrought with golden and silver light,\n"+
            "The blue and the dim and the dark cloths\n"+
            "Of night and light and the half light,\n"+
            "I would spread the cloths under your feet:\n"+
            "But I, being poor, have only my dreams;\n"+
            "I have spread my dreams under your feet;\n"+
            "Tread softly because you tread on my dreams.";

    @Override
    public Response prepareResponse() {
        Response response = new Response();
        response.setStatusCode(200);
        response.setContentType(contentType);
        response.addResponseBody(responseBody);
        return response;
    }
}
