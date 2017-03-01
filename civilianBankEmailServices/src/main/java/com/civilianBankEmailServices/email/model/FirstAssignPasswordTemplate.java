package com.civilianBankEmailServices.email.model;

import com.citybank.adminapp.common.emailhelper.BaseEmailTemplate;

/**
 * Created by BS-102 on 9/2/2014.
 */
public class FirstAssignPasswordTemplate extends BaseEmailTemplate {
    private String loginPassword;
    private String transactionPassword;
    public FirstAssignPasswordTemplate(String userName, String toEmailAddress, String subject, String templateName,
                                       String loginPassword,String transactionPassword) {
        super(userName, toEmailAddress, subject, templateName);
        this.loginPassword=loginPassword;
        this.transactionPassword=transactionPassword;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    public String getTransactionPassword() {
        return transactionPassword;
    }

    public void setTransactionPassword(String transactionPassword) {
        this.transactionPassword = transactionPassword;
    }
}
