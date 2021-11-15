public class Request {
    String[] requestArray;
    String[] requestLineArray;
    String method;
    String uri;
    String protocol;
    String body;

    int contentLength;

    public Request(String request) {
        splitRequestString(request);
        extractRequestLineInformation();
        extractHeaderInformation();
        extractRequestBody();
    }

    private void splitRequestString(String request) {
        this.requestArray = request.split("\r\n");
        this.requestLineArray = requestArray[0].split(" ");
    }

    private void extractRequestLineInformation() {
        this.method = requestLineArray[0];
        this.uri = requestLineArray[1];
        this.protocol = requestLineArray[2];
    }

    private void extractHeaderInformation() {
        for (String x : requestArray) {
            if (x.startsWith("Content-Length")) {
                this.contentLength = Integer.parseInt(x.substring(x.indexOf(" ") + 1, x.length()));
            }
        }
    }

    private void extractRequestBody() {
        this.body = requestArray[requestArray.length-1];
    }

}
