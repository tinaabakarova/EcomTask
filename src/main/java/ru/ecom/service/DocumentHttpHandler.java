package ru.ecom.service;

import com.google.gson.JsonObject;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import ru.ecom.dao.DocumentDao;
import ru.ecom.dao.DocumentDaoJdbc;
import ru.ecom.domain.Document;
import ru.ecom.domain.Response;
import ru.ecom.enums.ResponseMessage;
import ru.ecom.enums.StatusCode;
import ru.ecom.utils.DocumentParser;

import java.io.*;
import java.sql.SQLException;

public class DocumentHttpHandler implements HttpHandler {
    private HttpExchange httpExchange;
    private StringBuilder stringBuilder;

    @Override
    public void handle(final HttpExchange httpExchange) {
        this.httpExchange = httpExchange;

        InputStream input = httpExchange.getRequestBody();
        stringBuilder = new StringBuilder();

        new BufferedReader(new InputStreamReader(input))
                .lines()
                .forEach((String s) -> stringBuilder.append(s).append("\n"));

        switch (httpExchange.getRequestMethod()){
            case "POST":
                postHandle();
                break;
            case "GET":
                getHandle();
                break;
            case "DELETE":
                deleteHandle();
                break;
            case "PUT":
                putHandle();
                break;
            default:
                Response response = new Response.Builder(StatusCode.METHOD_NOT_ALLOWED.getCode())
                        .setError(ResponseMessage.METHOD_NOT_ALLOWED.getMessage())
                        .build();
                sendResponse(response, stringBuilder.length());
                break;
        }
    }

    private void postHandle(){
        DocumentDao documentDao = new DocumentDaoJdbc();
        int contentLength = stringBuilder.length();
        Response response;

        DocumentParser<Document> documentParser = new DocumentParser<>(Document.class);
        Document document = documentParser.getDtoFromRequest(stringBuilder, "body");
        String fileName = documentParser.getValueFromRequest(stringBuilder, "doc_name");
        document.setFileName(fileName);
        JsonObject documentJson = DocumentParser.getJsonElementFromObject(document);
        document.setDocBody(DocumentParser.getJsonStringFromObject(document));

        if (document.getSender() == null) {
            response = new Response.Builder(StatusCode.BAD_REQUEST.getCode())
                    .setError(ResponseMessage.SENDER_NOT_FILLED.getMessage())
                    .build();
        } else if (document.getRecipient() == null) {
            response = new Response.Builder(StatusCode.BAD_REQUEST.getCode())
                    .setError(ResponseMessage.RECIPIENT_NOT_FILLED.getMessage())
                    .build();
        } else {
            try {
                if (documentDao.insert(document)) {
                    response = new Response.Builder(StatusCode.OK.getCode())
                            .setResponse(documentJson)
                            .build();
                } else {
                    response = new Response.Builder(StatusCode.OK.getCode())
                            .setError(ResponseMessage.ZERO_RECORDS_CHANGED.getMessage())
                            .build();
                }
            } catch (SQLException throwables) {
                response = new Response.Builder(StatusCode.BAD_REQUEST.getCode())
                        .setError(throwables.getMessage())
                        .build();
            }
        }
        sendResponse(response, contentLength);
    }

    private void getHandle(){
        //
    }

    private void deleteHandle(){
        //
    }

    private void putHandle(){
        //
    }

    private void sendResponse(Response response, int contentLength) {
        try {
            httpExchange.sendResponseHeaders(response.getResponseCode(), contentLength);
            OutputStream output = httpExchange.getResponseBody();
            output.write(DocumentParser.getJsonStringFromObject(response).getBytes());
            httpExchange.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}