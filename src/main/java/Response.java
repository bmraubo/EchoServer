public class Response {
    String crlf = "\r\n";
    String protocol = "HTTP/1.1";
    int statusCode;
    String reasonPhrase;

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
        determineReasonPhrase(statusCode);
    }

    public String generateResponseString() {
        String responseString = "";
        responseString = responseString + this.protocol + " " + this.statusCode + " " + reasonPhrase + crlf;
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
}
