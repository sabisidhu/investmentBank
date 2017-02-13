package com.civilianBank.notification.model;

import java.util.Date;

/**
 * Created by Parshotam
 */
public class NotificationViewModel {

    private int referenceTableId;
    private int notificationId;
    private String messageBody;
    private int conversationId;
    private String url;
    private Date conversationDate;
    private String conversationDateAsString;
    private String senderName;
    private int subtypeId;

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public int getSubtypeId() {
        return subtypeId;
    }

    public void setSubtypeId(int subtypeId) {
        this.subtypeId = subtypeId;
    }

    public String getConversationDateAsString() {
        return conversationDateAsString;
    }

    public void setConversationDateAsString(String conversationDateAsString) {
        this.conversationDateAsString = conversationDateAsString;
    }

    public Date getConversationDate() {
        return conversationDate;
    }

    public void setConversationDate(Date conversationDate) {
        this.conversationDate = conversationDate;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getConversationId() {
        return conversationId;
    }

    public void setConversationId(int conversationId) {
        this.conversationId = conversationId;
    }

    public int getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(int notificationId) {
        this.notificationId = notificationId;
    }

    public int getReferenceTableId() {
        return referenceTableId;
    }

    public void setReferenceTableId(int referenceTableId) {
        this.referenceTableId = referenceTableId;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }
}
