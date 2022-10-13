package ru.geekbrains.lesson2.domain;

import java.util.Map;

public class HttpResponse {

    private String version;

    private StatusCode statusCode;

    private Map<String, String> headers;

    private String body;


    private HttpResponse() {
    }

    public String getVersion() {
        return version;
    }

    public StatusCode getStatusCode() {
        return statusCode;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public String getBody() {
        return body;
    }

    @Override
    public String toString() {
        return "HttpResponse{" +
                "version='" + version + '\'' +
                ", statusCode=" + statusCode +
                ", headers=" + headers +
                ", body='" + body + '\'' +
                '}';
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder {

        private final HttpResponse httpResponse;

        private Builder() {
            this.httpResponse = new HttpResponse();
        }

        public Builder version(String version){
            this.httpResponse.version = version;
            return this;
        }

        public Builder statusCode(StatusCode statusCode){
            this.httpResponse.statusCode = statusCode;
            return this;
        }

        public Builder headers(Map<String,String> headers){
            this.httpResponse.headers = headers;
            return this;
        }

        public Builder body(String body){
            this.httpResponse.body = body;
            return this;
        }

        public HttpResponse build(){
            return this.httpResponse;
        }

    }
}
