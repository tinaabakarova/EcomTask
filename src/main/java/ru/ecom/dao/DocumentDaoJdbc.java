package ru.ecom.dao;

import ru.ecom.config.DataSource;
import ru.ecom.domain.Document;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DocumentDaoJdbc implements DocumentDao {
    @Override
    public boolean insert(Document document) throws SQLException {
        String query = "INSERT INTO docs (file_name, doc_type, status, sender, recipient, doc_body)" +
                "VALUES (?, ?, ?, ?, ?, ?);";
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, document.getFileName());
            preparedStatement.setObject(2, document.getDocumentType());
            preparedStatement.setInt(3, document.getStatus());
            preparedStatement.setInt(4, document.getSender());
            preparedStatement.setInt(5, document.getRecipient());
            preparedStatement.setString(6, document.getDocBody());
            int i = preparedStatement.executeUpdate();

            if (i == 1) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Document getById(long id) {
        String query = "SELECT doc_id, file_name, doc_type, status, sender, recipient, doc_body FROM docs " +
                "WHERE doc_id = ";

        try (Connection connection = DataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query + id)) {
            if (resultSet.next()) {
                return mapResultSetToDocument(resultSet);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Document> getAll() {
        List<Document> documents = new ArrayList<>();
        String query = "SELECT doc_id, file_name, doc_type, status, sender, recipient, doc_body FROM docs;";

        try (Connection connection = DataSource.getConnection();
             Statement statement = connection.createStatement()) {
             ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                documents.add(mapResultSetToDocument(resultSet));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return documents;
    }

    @Override
    public boolean deleteById(long id) {
        String query = "DELETE FROM docs WHERE doc_id = ?;";

        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            int i = preparedStatement.executeUpdate();

            if (i == 1) {
                return true;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Document document) {
        String query = "UPDATE docs SET status = ? where doc_id = ?;";

        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, document.getStatus());
            preparedStatement.setLong(2, document.getDocId());
            int i = preparedStatement.executeUpdate();

            if (i == 1) {
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    private Document mapResultSetToDocument(ResultSet resultSet) throws SQLException {
        Document document = new Document(resultSet.getLong("doc_id"),
                resultSet.getString("doc_type"),
                resultSet.getInt("sender"),
                resultSet.getInt("recipient"),
                resultSet.getString("file_name"),
                resultSet.getInt("status"),
                resultSet.getString("doc_body"));
        return document;
    }
}
