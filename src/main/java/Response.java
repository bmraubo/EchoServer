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
    String allowedMethods;
    String locationHeader;

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
        if (allowedMethods != null) {
            headers = headers + allowedMethods + crlf;
        }
        if (locationHeader != null) {
            headers = headers + locationHeader + crlf;
        }
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
            case (301):
                this.reasonPhrase = "Moved Permanently";
                break;
            case (404):
                this.reasonPhrase = "Not Found";
                break;
            case (405):
                this.reasonPhrase = "Method Not Allowed";
                break;
            case (500):
                this.reasonPhrase = "Internal Server Error";
                break;
        }
    }

    public void addResponseBody(String body) {
        System.out.println("Adding Response Body");
        if (body != null) {
            contentLength = calculateContentLength(body);
            this.body = body;
            this.sendBody = true;
        }
    }

    private String calculateContentLength(String body) {
        return "Content-Length: " + String.valueOf(body.length());
    }

    public void setAllowHeader(String[] allowedMethods) {
        System.out.println("Setting Allow Header");
        String methods = "Allow: ";
        for (String x : allowedMethods) {
            methods = methods + x + ", ";
        }
        this.allowedMethods = methods.substring(0, methods.length()-2);
    }

    public void setLocationHeader(String location) {
        this.locationHeader = "Location: " + location;
    }
}
