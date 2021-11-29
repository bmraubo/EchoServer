import java.util.Arrays;
import java.util.LinkedHashMap;

public class RequestBuilder {
    String[] requestArray;
    String statusLine;
    LinkedHashMap<String, String> headers;
    String body;
    boolean requestIncludesHeaders;
    boolean requestIncludesBody;

    public RequestBuilder(){
        generateHeaderMap();
    }

    public void extractRequest(String requestData){
        requestArray = requestData.split("\r\n");
        extractStatusLine();
        requestIncludesHeaders = checkForHeaders();
        requestIncludesBody = checkForBody();
        if (requestIncludesHeaders) {
            extractHeaders();
        }
        if (requestIncludesBody) {
            extractBody();
        }
    }

    public String getMethod() {
        return statusLine.split(" ")[0];
    }

    public String getURI() {
        return statusLine.split(" ")[1];
    }

    public String getProtocol() {
        return statusLine.split(" ")[2];
    }

    private void extractStatusLine() {
        statusLine = requestArray[0];
    }

    private void extractHeaders() {
        if (requestIncludesBody) {
            for (String x : Arrays.copyOfRange(requestArray, 1, requestArray.length - 2)) {
                String headerKey = extractHeaderKey(x);
                String headerValue = extractHeaderValue(x);
                headers.put(headerKey, headerValue);
            }
        } else {
            for (String x : Arrays.copyOfRange(requestArray, 1, requestArray.length - 1 )) {
                String headerKey = extractHeaderKey(x);
                String headerValue = extractHeaderValue(x);
                headers.put(headerKey, headerValue);
            }
        }
    }

    private String extractHeaderKey(String header) {
        return header.substring(0, header.indexOf(":"));
    }

    private String extractHeaderValue(String header) {
        return header.substring(header.indexOf(" ") + 1, header.length());
    }

    private void extractBody() {
        body = requestArray[requestArray.length-1];

    }

    private void generateHeaderMap() {
        headers = new LinkedHashMap<String, String>();
    }

    private boolean checkForBody() {
        for (String x : requestArray) {
            if (x.startsWith("Content-Length")) {
                return true;
            }
        }
        return false;
    }

    private boolean checkForHeaders() {
        return requestArray.length > 1;
    }
}
