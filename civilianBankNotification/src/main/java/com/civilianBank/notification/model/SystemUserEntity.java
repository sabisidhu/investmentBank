package com.civilianBank.notification.model;


import java.util.Date;
import java.util.UUID;

/**
 * Created by BS23 Parshotam
 */
public class SystemUserEntity {

    private int id;

    private String userCode;

    private UUID loginCode;

    private String userName;

    private String userShortName;

    private String empCode;//

    private int branchCode;

    private int userGroup;

    private int userStatus;

    private String emailId;

    private String password;

    private char pwdChangedForced;

    private int pwdChangePeriodDays;

    private Date pwdChangePreviousDate;

    private Date pwdChangeNextDate;

    private char minLifeReqForcedYN;

    private int minLifePeriodDays;

    private int  maxBadLifePeriodDay;

    private int pwdLockedPeriodMinute;

    private char hasActive;

    private int isRemove;

    private Date lastLoginDate;

    private int numberOfBadLogin;

    public int getNumberOfBadLogin() {
        return numberOfBadLogin;
    }

    public void setNumberOfBadLogin(int numberOfBadLogin) {
        this.numberOfBadLogin = numberOfBadLogin;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public int getIsRemove() {
        return isRemove;
    }

    public void setIsRemove(int isRemove) {
        this.isRemove = isRemove;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public UUID getLoginCode() {
        return loginCode;
    }

    public void setLoginCode(UUID uuid) {
        this.loginCode = uuid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserShortName() {
        return userShortName;
    }

    public void setUserShortName(String userShortName) {
        this.userShortName = userShortName;
    }

    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    public int getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(int branchCode) {
        this.branchCode = branchCode;
    }

    public int getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(int userGroup) {
        this.userGroup = userGroup;
    }

    public int getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(int userStatus) {
        this.userStatus = userStatus;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public char getPwdChangedForced() {
        return pwdChangedForced;
    }

    public void setPwdChangedForced(char pwdChangedForced) {
        this.pwdChangedForced = pwdChangedForced;
    }

    public int getPwdChangePeriodDays() {
        return pwdChangePeriodDays;
    }

    public void setPwdChangePeriodDays(int pwdChangePeriodDays) {
        this.pwdChangePeriodDays = pwdChangePeriodDays;
    }

    public Date getPwdChangePreviousDate() {
        return pwdChangePreviousDate;
    }

    public void setPwdChangePreviousDate(Date pwdChangePreviousDate) {
        this.pwdChangePreviousDate = pwdChangePreviousDate;
    }

    public Date getPwdChangeNextDate() {
        return pwdChangeNextDate;
    }

    public void setPwdChangeNextDate(Date pwdChangeNextDate) {
        this.pwdChangeNextDate = pwdChangeNextDate;
    }

    public char getMinLifeReqForcedYN() {
        return minLifeReqForcedYN;
    }

    public void setMinLifeReqForcedYN(char minLifeReqForcedYN) {
        this.minLifeReqForcedYN = minLifeReqForcedYN;
    }

    public int getMinLifePeriodDays() {
        return minLifePeriodDays;
    }

    public void setMinLifePeriodDays(int minLifePeriodDays) {
        this.minLifePeriodDays = minLifePeriodDays;
    }

    public int getMaxBadLifePeriodDay() {
        return maxBadLifePeriodDay;
    }

    public void setMaxBadLifePeriodDay(int maxBadLifePeriodDay) {
        this.maxBadLifePeriodDay = maxBadLifePeriodDay;
    }

    public int getPwdLockedPeriodMinute() {
        return pwdLockedPeriodMinute;
    }

    public void setPwdLockedPeriodMinute(int pwdLockedPeriodMinute) {
        this.pwdLockedPeriodMinute = pwdLockedPeriodMinute;
    }

    public char getHasActive() {
        return hasActive;
    }

    public void setHasActive(char hasActive) {
        this.hasActive = hasActive;
    }


}


