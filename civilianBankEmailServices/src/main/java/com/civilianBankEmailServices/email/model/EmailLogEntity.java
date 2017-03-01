package com.civilianBankEmailServices.email.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by BS102-Saif on 1/27/2015.
 */
@Entity(name = "ADMIN_EMAIL_LOG")
public class EmailLogEntity {

    @Id
    @Column
    @SequenceGenerator(name = "EmailLogIdGenerator", sequenceName = "ADMIN_EMAIL_LOG__ID", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EmailLogIdGenerator")

    private long id;

    @Column( name = "USER_ID")
    private long userId;

    @Column( name = "USER_TPYE")
    private String userType;

    @Column( name = "EMAIL")
    private String email;

    @Column( name = "EMAIL_SUBJECT")
    private String subject;

    @Column( name = "RESPONSE_CODE")
    private int responseCode;

    @Column( name = "RESPONSE_MESSAGE")
    private String responseMessage;

    @Column( name = "TEMP_TABLE_ID")
    private long tempTableId;

    @Column( name = "EMAIL_SENDING_DATE")
    private Date emailSendingDate;

    @Column( name = "CHECKER_ID")
    private long checkerId;

    public long getCheckerId() {
        return checkerId;
    }

    public void setCheckerId(long checkerId) {
        this.checkerId = checkerId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public long getTempTableId() {
        return tempTableId;
    }

    public void setTempTableId(long tempTableId) {
        this.tempTableId = tempTableId;
    }

    public Date getEmailSendingDate() {
        return emailSendingDate;
    }

    public void setEmailSendingDate(Date emailSendingDate) {
        this.emailSendingDate = emailSendingDate;
    }
}
