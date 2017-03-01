package com.civilianBankEmailServices.email.model;

import com.citybank.adminapp.common.emailhelper.BaseEmailTemplate;

/**
 * Created by BS102-Saif on 1/28/2015.
 */
public class CommonMailTemplateModel extends BaseEmailTemplate {

    private String greetingMessage;

    public CommonMailTemplateModel() {

    }

    public CommonMailTemplateModel(String userId, String userName, String toEmailAddress, String subject, String templateName, String greetingMessage) {
        super(userId, userName, toEmailAddress, subject, templateName);
        this.greetingMessage = greetingMessage;
    }

    public CommonMailTemplateModel(String userName, String toEmailAddress, String subject, String templateName, String greetingMessage) {
        super(userName, toEmailAddress, subject, templateName);
        this.greetingMessage = greetingMessage;
    }

    public String getGreetingMessage() {
        return greetingMessage;
    }

    public void setGreetingMessage(String greetingMessage) {
        this.greetingMessage = greetingMessage;
    }
}
