package ru.ecom.enums;

public enum DocumentType {
    ORDER("ORDER"),
    DESADV("DESADV"),
    RECADV("RECADV");

    private String title;

    DocumentType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "DocumentType{" +
                "title='" + title + '\'' +
                '}';
    }
}
