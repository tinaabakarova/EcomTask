package ru.ecom.domain;

import com.google.gson.JsonObject;
public class Response {
    private final JsonObject response;
    private final JsonObject error;
    private final Integer responseCode;
    public static class Builder {
        private JsonObject response;
        private JsonObject error;
        private final Integer responseCode;

        public Builder(Integer responseCode) {
            this.responseCode = responseCode;
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
        this.responseCode = builder.responseCode;
        this.response = builder.response;
        this.error = builder.error;
    }

    public Integer getResponseCode() {
        return responseCode;
    }
}

