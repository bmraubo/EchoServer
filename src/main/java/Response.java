public class Response {
    String protocol = "HTTP/1.1";
    int statusCode;
    String reasonPhrase;

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String generateResponseString() {
        String responseString = "";
        responseString = responseString + this.protocol;
    }

    private void determineReasonPhrase(int statusCode) {
        switch (statusCode) {
            case (200):
                 this.reasonPhrase = "OK";
        }
    }
}
