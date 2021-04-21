DROP TABLE IF EXISTS DOCS;
CREATE TABLE DOCS(DOC_ID BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                    FILE_NAME VARCHAR(255),
                    DOC_TYPE VARCHAR(255) check (DOC_TYPE in ('ORDER', 'DESADV', 'RECADV')),
                    STATUS BIGINT DEFAULT 0 check (STATUS in (0, 1, 2)),
                    SENDER BIGINT,
                    RECIPIENT BIGINT,
                    DOC_BODY VARCHAR(255));