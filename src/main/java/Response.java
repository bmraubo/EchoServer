public class Response {
    String crlf = "\r\n";
    // Response Line
    String protocol = "HTTP/1.1";
    int statusCode;
    String reasonPhrase;

    //Headers
    String contentLength;
    boolean closeConnection = true;
    String closeConnectionHeader = "Connection: close";

    // Body
    boolean sendBody = false;
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
        headers = headers + closeConnectionHeader + crlf;
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
