package ru.ecom.enums;

public enum ResponseMessage {
    SENDER_NOT_FILLED("Не заполнен тег SENDER"),
    RECIPIENT_NOT_FILLED("Не заполнен тег RECIPIENT"),
    ZERO_RECORDS_CHANGED("Изменено 0 записей"),
    METHOD_NOT_ALLOWED("Метод не реализован");

    private String message;

    ResponseMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
