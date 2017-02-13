package com.civilianBank.notification.model;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Created by BS23 on 4/30/14.
 */

@Entity
@Table(name = "CONVERSATION")
public class ConversationEntity {

    @Id
    @Column
    @SequenceGenerator(name="ConversationIdGenerator", sequenceName="Conversation__Id", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ConversationIdGenerator")
    private int id;

    @Column(name = "NOTIFICATION_ID")
    private int notificationId;

    @Column(name ="STATUS")
    private char status;

    @Column(name = "FROM_USER_ID")
    private int fromUserId;

    @Column(name = "TO_USER_ID")
    private int toUserId;

    @Column(name = "MESSAGE", columnDefinition = "NVARCHAR2(500) default 'message'")
    @Size(max=500)
    private String message;

    @Column(name="CONVERSATION_DATE")
    private Date conversationDate;

    public Date getConversationDate() {
        return conversationDate;
    }

    public void setConversationDate(Date conversationDate) {
        this.conversationDate = conversationDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }

    public int getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(int fromUserId) {
        this.fromUserId = fromUserId;
    }

    public int getToUserId() {
        return toUserId;
    }

    public void setToUserId(int toUserId) {
        this.toUserId = toUserId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(int notificationId) {
        this.notificationId = notificationId;
    }


}
