package ru.ecom.domain;

import com.google.gson.JsonObject;
public class Response {
    private final JsonObject response;
    private final JsonObject error;
    private final Integer rCode;
    private final Integer contentLength;
    public static class Builder {
        private JsonObject response;
        private JsonObject error;
        private final Integer rCode;
        private final Integer contentLength;

        public Builder(Integer rCode, Integer contentLength) {
            this.rCode = rCode;
            this.contentLength = contentLength;
        }

        public Builder setResponse(JsonObject response) {
            this.response = response;
            return this;
        }

        public Builder setError(String errorMessage) {
            JsonObject error = new JsonObject();
            error.addProperty("errorMessage", errorMessage);
            this.error = error;
            return this;
        }

        public Response build() {
            return new Response(this);
        }
    }

    private Response(Builder builder){
        this.rCode = builder.rCode;
        this.contentLength = builder.contentLength;
        this.response = builder.response;
        this.error = builder.error;
    }

    public Integer getrCode() {
        return rCode;
    }

    public Integer getContentLength() {
        return contentLength;
    }
}

