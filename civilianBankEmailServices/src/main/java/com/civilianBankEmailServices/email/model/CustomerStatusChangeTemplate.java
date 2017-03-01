package com.civilianBankEmailServices.email.model;

import com.citybank.adminapp.common.emailhelper.BaseEmailTemplate;

/**
 * Created by shadiq.bs23 on 1/26/15.
 */
public class CustomerStatusChangeTemplate extends BaseEmailTemplate {
    private String status;

    public CustomerStatusChangeTemplate() {
    }

    public CustomerStatusChangeTemplate(String userId,String userName, String toEmailAddress, String subject, String templateName, String status ) {
        super(userId,userName, toEmailAddress, subject, templateName);
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
