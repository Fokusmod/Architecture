package ru.geekbrains.lesson2.domain;

import java.util.Map;

public class HttpRequest {

    private String method;

    private String path;

    private String version;

    private Map<String, String> headers;

    private String body;

    private HttpRequest() {
    }

    public String getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }

    public String getVersion() {
        return version;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public String getBody() {
        return body;
    }



    @Override
    public String toString() {
        return "HttpRequest{" +
                "method='" + method + '\'' +
                ", path='" + path + '\'' +
                ", version='" + version + '\'' +
                ", headers=" + headers +
                ", body='" + body + '\'' +
                '}';
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder {

        private final HttpRequest httpRequest;

        private Builder() {
            this.httpRequest = new HttpRequest();
        }

        public Builder method(String method){
            this.httpRequest.method = method;
            return this;
        }

        public Builder path(String path){
            this.httpRequest.path = path;
            return this;
        }
        public Builder version(String version){
            this.httpRequest.version = version;
            return this;
        }
        public Builder headers(Map<String,String> headers){
            this.httpRequest.headers = headers;
            return this;
        }

        public Builder body(String body){
            this.httpRequest.body = body;
            return this;
        }

        public HttpRequest build(){
            return this.httpRequest;
        }
    }
}
