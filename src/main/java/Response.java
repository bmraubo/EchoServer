public class Response {
    String crlf = "\r\n";
    String protocol = "HTTP/1.1";
    int statusCode;
    String reasonPhrase;
    boolean sendBody = false;
    String contentLength;
    String body;

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
        determineReasonPhrase(statusCode);
    }

    public String generateResponseString() {
        System.out.println("generating response");
        String responseString;
        String responseLine = generateResponseLine();
        String responseHeaders = generateHeaders();
        responseString = responseLine + responseHeaders;
        if (sendBody) {
            String responseBody = crlf + body;
            responseString = responseString + responseBody;
        }
        System.out.println(responseString);
        return responseString;
    }

    private String generateResponseLine() {
        return this.protocol + " " + this.statusCode + " " + this.reasonPhrase + crlf;
    }

    private String generateHeaders() {
        String headers = "";
        if (sendBody) {
            headers = headers + contentLength + crlf;
        }
        return headers;
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
        this.sendBody = true;
    }

    private String calculateContentLength(String body) {
        return "Content-Length: " + String.valueOf(body.length());
    }
}
