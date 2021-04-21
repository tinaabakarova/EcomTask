package ru.ecom.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.ecom.domain.Document;
import ru.ecom.enums.DocumentType;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DocumentDaoTest {

    @DisplayName("должен сохранять документ в базу данных")
    @Test
    void insert() throws SQLException {
        Document document = new Document(3L, DocumentType.DESADV.getTitle(), TestData.SENDER_DESADV,
                TestData.RECIPIENT_DESADV, TestData.FILE_NAME_DESADV, TestData.STATUS_1, TestData.DOC_BODY);

        DocumentDao documentDao = new DocumentDaoJdbc();
        documentDao.insert(document);
        assertEquals(documentDao.getById(3), document);
    }

    @DisplayName("должен получать документ из базы данных")
    @Test
    void getById() {
        Document document = new Document(1L, DocumentType.ORDER.getTitle(), TestData.SENDER_ORDER,
                TestData.RECIPIENT_ORDER, TestData.FILE_NAME_ORDER, TestData.STATUS_0, TestData.DOC_BODY);

        DocumentDao documentDao = new DocumentDaoJdbc();
        assertEquals(documentDao.getById(1), document);
    }

    @DisplayName("должен получать все документы из базы данных")
    @Test
    void getAll() {
        DocumentDao documentDao = new DocumentDaoJdbc();
        List<Document> documents = documentDao.getAll();

        assertTrue(documents.containsAll(Arrays.asList(new Document(1L, DocumentType.ORDER.getTitle(), TestData.SENDER_ORDER,
                TestData.RECIPIENT_ORDER, TestData.FILE_NAME_ORDER, TestData.STATUS_0, TestData.DOC_BODY),
                new Document(2L, DocumentType.RECADV.getTitle(), TestData.SENDER_RECADV,
                        TestData.RECIPIENT_RECADV, TestData.FILE_NAME_RECADV, TestData.STATUS_2, TestData.DOC_BODY))));
    }

    @DisplayName("должен удалять документ из базы данных по id")
    @Test
    void deleteById() throws SQLException {
        Document document = new Document(5L, DocumentType.DESADV.getTitle(), TestData.SENDER_DESADV,
                TestData.RECIPIENT_DESADV, TestData.FILE_NAME_DESADV, TestData.STATUS_1, TestData.DOC_BODY);

        DocumentDao documentDao = new DocumentDaoJdbc();
        documentDao.insert(document);
        documentDao.deleteById(5);
        assertNull(documentDao.getById(5));
    }

    @DisplayName("должен обновлять документ в базе данных")
    @Test
    void update() throws SQLException {
        Document document = new Document(4L, DocumentType.DESADV.getTitle(), TestData.SENDER_DESADV,
                TestData.RECIPIENT_DESADV, TestData.FILE_NAME_DESADV, TestData.STATUS_1, TestData.DOC_BODY);

        DocumentDao documentDao = new DocumentDaoJdbc();
        documentDao.insert(document);

        document = new Document(4L, DocumentType.DESADV.getTitle(), TestData.SENDER_DESADV,
                TestData.RECIPIENT_DESADV, TestData.FILE_NAME_DESADV, TestData.STATUS_2, TestData.DOC_BODY);
        documentDao.update(document);
        assertEquals(documentDao.getById(4), document);
    }
}