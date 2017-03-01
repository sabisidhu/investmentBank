package com.civilianBankEmailServices.email.model;


import java.util.Date;

/**
 * Created by BS-102 on 9/2/2014.
 */
public class AccountOpeningMailTemplate  {

    private String cbsCustomerNo;
    private String fullName;
    private String customerEmailAddress;
    private String customerMobile;
    private String accountOpeningType;
    private String prefferedBranch;
    private String customerRemarks;
    private String date;

    public AccountOpeningMailTemplate(){

    }

    public AccountOpeningMailTemplate(String userName, String toEmailAddress, String subject, String templateName, String customerAddress, String cbsCustomerNo, String fullName, String customerMobile, String accountOpeningType, String prefferedBranch, String customerRemarks) {
        super(userName, toEmailAddress, subject, templateName);
        this.customerEmailAddress = customerAddress;
        this.cbsCustomerNo = cbsCustomerNo;
        this.fullName = fullName;
        this.customerMobile = customerMobile;
        this.accountOpeningType = accountOpeningType;
        this.prefferedBranch = prefferedBranch;
        this.customerRemarks = customerRemarks;
        this.date= new Date().toString();
    }

    public String getCbsCustomerNo() {
        return cbsCustomerNo;
    }

    public void setCbsCustomerNo(String cbsCustomerNo) {
        this.cbsCustomerNo = cbsCustomerNo;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCustomerEmailAddress() {
        return customerEmailAddress;
    }

    public void setCustomerEmailAddress(String customerEmailAddress) {
        this.customerEmailAddress = customerEmailAddress;
    }

    public String getCustomerMobile() {
        return customerMobile;
    }

    public void setCustomerMobile(String customerMobile) {
        this.customerMobile = customerMobile;
    }

    public String getAccountOpeningType() {
        return accountOpeningType;
    }

    public void setAccountOpeningType(String accountOpeningType) {
        this.accountOpeningType = accountOpeningType;
    }

    public String getPrefferedBranch() {
        return prefferedBranch;
    }

    public void setPrefferedBranch(String prefferedBranch) {
        this.prefferedBranch = prefferedBranch;
    }

    public String getCustomerRemarks() {
        return customerRemarks;
    }

    public void setCustomerRemarks(String customerRemarks) {
        this.customerRemarks = customerRemarks;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
