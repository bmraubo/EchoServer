public class RequestBuilder {
    String statusLine;

    public void extractRequest(String requestData){
        String[] requestArray = requestData.split("\r\n");
        extractStatusLine(requestArray);
    }

    private void extractStatusLine(String[] requestArray) {
        statusLine = requestArray[0];
    }
}
