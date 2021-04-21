package ru.ecom.dao;

import ru.ecom.domain.Document;

import java.sql.SQLException;
import java.util.List;

public interface DocumentDao {
    boolean insert(Document document) throws SQLException;

    Document getById(long id);

    List<Document> getAll();

    boolean deleteById(long id);

    boolean update(Document document);
}
