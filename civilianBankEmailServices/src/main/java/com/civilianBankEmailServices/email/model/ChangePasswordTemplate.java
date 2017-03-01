package com.civilianBankEmailServices.email.model;

import com.citybank.adminapp.common.emailhelper.BaseEmailTemplate;

/**
 * Created by BS23 on 7/17/14.
 */
public class ChangePasswordTemplate extends BaseEmailTemplate {
    private String loginPassword;
    private String transactionPassword;
    private char templateForLoginPasswordChanged;
    private boolean loginPasswordFirstTime;
    private boolean transactionPasswordFirstTime;



    public ChangePasswordTemplate() {
    }

    public ChangePasswordTemplate(String userId,String userName, String toEmailAddress, String subject, String templateName, String loginPassword,String transactionPassword ) {
        super(userId,userName, toEmailAddress, subject, templateName);
        this.loginPassword = loginPassword;
        this.transactionPassword=transactionPassword;
    }
    public ChangePasswordTemplate(String userId, String userName, String toEmailAddress, String subject, String templateName, String loginPassword) {
        super(userId,userName, toEmailAddress, subject, templateName);
        this.loginPassword = loginPassword;
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

    public char getTemplateForLoginPasswordChanged() {
        return templateForLoginPasswordChanged;
    }

    public void setTemplateForLoginPasswordChanged(char templateForLoginPasswordChanged) {
        this.templateForLoginPasswordChanged = templateForLoginPasswordChanged;
    }

    public boolean getLoginPasswordFirstTime() {
        return loginPasswordFirstTime;
    }

    public void setLoginPasswordFirstTime(boolean loginPasswordFirstTime) {
        this.loginPasswordFirstTime = loginPasswordFirstTime;
    }

    public boolean getTransactionPasswordFirstTime() {
        return transactionPasswordFirstTime;
    }

    public void setTransactionPasswordFirstTime(boolean transactionPasswordFirstTime) {
        this.transactionPasswordFirstTime = transactionPasswordFirstTime;
    }
}