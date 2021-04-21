package ru.ecom.domain;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.Objects;

@XStreamAlias("document")
public class Document {
   private Long docId;
   @XStreamAlias("documentType")
   private String documentType;
   @XStreamAlias("sender")
   private Integer sender;
   @XStreamAlias("recipient")
   private Integer recipient;
   @XStreamAlias("fileName")
   private String fileName;
   @XStreamAlias("status")
   private Integer status;
   private String docBody;

   public Document() {
   }

   public Document(String documentType, Integer sender, Integer recipient, String fileName, Integer status, String docBody) {
      this.documentType = documentType;
      this.sender = sender;
      this.recipient = recipient;
      this.fileName = fileName;
      this.status = status;
      this.docBody = docBody;
   }

   public Document(Long docId, String documentType, Integer sender, Integer recipient, String fileName, Integer status, String docBody) {
      this.docId = docId;
      this.documentType = documentType;
      this.sender = sender;
      this.recipient = recipient;
      this.fileName = fileName;
      this.status = status;
      this.docBody = docBody;
   }

   public void setDocId(Long docId) {
      this.docId = docId;
   }

   public void setDocumentType(String documentType) {
      this.documentType = documentType;
   }

   public void setSender(Integer sender) {
      this.sender = sender;
   }

   public void setRecipient(Integer recipient) {
      this.recipient = recipient;
   }

   public void setFileName(String fileName) {
      this.fileName = fileName;
   }

   public void setStatus(Integer status) {
      this.status = status;
   }

   public void setDocBody(String docBody) {
      this.docBody = docBody;
   }

   public long getDocId() {
      return docId;
   }

   public String getDocumentType() {
      return documentType;
   }

   public Integer getSender() {
      return sender;
   }

   public Integer getRecipient() {
      return recipient;
   }

   public String getFileName() {
      return fileName;
   }

   public Integer getStatus() {
      return status;
   }

   public String getDocBody() {
      return docBody;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) {
         return true;
      }
      if (!(o instanceof Document)) {
         return false;
      }
      Document document = (Document) o;
      return docId.equals(document.docId) &&
              sender.equals(document.sender) &&
              recipient.equals(document.recipient) &&
              status.equals(document.status) &&
              documentType.equals(document.documentType) &&
              fileName.equals(document.fileName) &&
              docBody.equals(document.docBody);
   }

   @Override
   public int hashCode() {
      return Objects.hash(docId, documentType, sender, recipient, fileName, status, docBody);
   }

   @Override
   public String toString() {
      return "Document{" +
              "docId=" + docId +
              ", documentType='" + documentType + '\'' +
              ", sender=" + sender +
              ", recipient=" + recipient +
              ", fileName='" + fileName + '\'' +
              ", status=" + status +
              ", docBody=" + docBody +
              '}';
   }
}
