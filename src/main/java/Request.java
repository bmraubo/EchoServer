public class Request {
    String method;
    String uri;
    String body;
    int contentLength;

    public Request(String input) {
        String[] inputArray = input.split("\r\n");
        String requestLine = inputArray[0];
        String[] requestLineArray = requestLine.split(" ");
        method = requestLineArray[0];
        uri = requestLineArray[1];
        for (String x : inputArray) {
            if (x.startsWith("Content-Length: ")) {
                contentLength = Integer.parseInt(x.substring(x.indexOf(" ")+1, x.length()));
            }
        }
        body = inputArray[inputArray.length-1];
    }
}
