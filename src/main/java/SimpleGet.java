public class SimpleGet {

    public static Response prepareResponse(Request request) {
        Response response = new Response();
        System.out.println("Simple Get Identified");
        response.setStatusCode(200);
        return response;
    }
}
