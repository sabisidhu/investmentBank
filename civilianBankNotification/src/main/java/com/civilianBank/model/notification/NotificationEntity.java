package com.civilianBank.model.notification;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "NOTIFICATION")
public class NotificationEntity {

    @Id
    @Column
    @SequenceGenerator(name="NotificationIdGenerator", sequenceName="Notification__Id", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "NotificationIdGenerator")
    private int id;


    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "NOTIFICATION_ID")
    private List<ConversationEntity> conversationEntities;


    @Column(name = "NOTIFICATION_TYPE_ID")
    private int notificationTypeId;

    @Column(name = "REF_TABLE_ID")
    private int refTableId;

    @Column(name ="STATUS")
    private char status;

    @Column(name = "FROM_USER_ID")
    private int fromUserId;

    @Column(name = "TO_USER_ID")
    private int toUserId;

    @Column(name = "URL")
    @Size(max=100)
    private String url;

    @Column(name = "NOTIFICATION_SUB_TYPE")
    private int notificationSubType;

    public int getNotificationSubType() {
        return notificationSubType;
    }

    public void setNotificationSubType(int notificationSubType) {
        this.notificationSubType = notificationSubType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<ConversationEntity> getConversationEntities() {
        return conversationEntities;
    }

    public void setConversationEntities(List<ConversationEntity> conversationEntities) {
        this.conversationEntities = conversationEntities;
    }

    public int getNotificationTypeId() {
        return notificationTypeId;
    }

    public void setNotificationTypeId(int notificationTypeId) {
        this.notificationTypeId = notificationTypeId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRefTableId() {
        return refTableId;
    }

    public void setRefTableId(int refTableId) {
        this.refTableId = refTableId;
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

}
