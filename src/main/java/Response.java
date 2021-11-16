public class Response {
    String crlf = "\r\n";
    String protocol = "HTTP/1.1";
    int statusCode;
    String reasonPhrase;
    String contentLength;
    String body;

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
        determineReasonPhrase(statusCode);
    }

    public String generateResponseString() {
        System.out.println("generating response");
        String responseString = "";
        responseString = responseString + this.protocol + " " + this.statusCode + " " + reasonPhrase + crlf;
        System.out.println(responseString);
        return responseString;
    }

    private void determineReasonPhrase(int statusCode) {
        switch (statusCode) {
            case (200):
                this.reasonPhrase = "OK";
                break;
            case (404):
                this.reasonPhrase = "Not Found";
                break;
        }
    }


    public void addResponseBody(String body) {
        System.out.println("Adding Response Body");
        contentLength = calculateContentLength(body);
        this.body = body;
    }

    private String calculateContentLength(String body) {
        return "Content-Length: " + String.valueOf(body.length());
    }
}
