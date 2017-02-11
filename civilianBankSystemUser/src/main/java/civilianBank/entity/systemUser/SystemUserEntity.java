package civilianBank.entity.systemUser;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Created by BS23 Parshotam
 */
@Entity
@Table(name = "SYSTEMUSER")
public class SystemUserEntity {

    @Id
    @Column
    @SequenceGenerator(name="SystemUserGenerator", sequenceName="Systemuser__Id", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SystemUserGenerator")
    private int id;

    @Column(name = "USERCODE")
    @Size(max = 30)
    private String userCode;

    @Column(name = "LOGINCODE")
    private int loginCode;

    @Column(name = "USERNAME")
    @Size(max = 100)
    private String userName;

    @Column(name = "USERSHORTNAME")
    @Size(max = 50)
    private String userShortName;

    @Column(name = "EMPCODE")
    @Size(max = 16)
    private String empCode;//

    @Column(name = "BRCODE")
    private int branchCode;

    @Column(name = "USERGROUP")
    private int userGroup;

    @Column(name = "USERSTATUS")
    private int userStatus;

    @Column(name = "EMAILID")
    @Size(max = 50)
    private String emailId;

    @Column(name = "PASSWORD")
    @Size(max = 100)
    private String password;

    @Column(name = "PWDCHGFORCEDYN")
    private char pwdChangedForced;

    @Column(name = "PWDCHGPERIODDAYS")
    private int pwdChangePeriodDays;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "PWDCHGPREVDATE",columnDefinition="DATE")
    private Date pwdChangePreviousDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "PWDCHGNEXTDATE",columnDefinition="DATE")
    private Date pwdChangeNextDate;

    @Column(name = "MINLIFREQFORCEDYN")
    private char minLifeReqForcedYN;

    @Column(name = "MINLIPERIODDAYS")
    private int minLifePeriodDays;

    @Column(name = "MAXBADLIPERDAY")
    private int  maxBadLifePeriodDay;

    @Column(name = "PWDLOCKEDPEROIDMINUTE")
    private int pwdLockedPeriodMinute;

    @Column(name = "HASACTIVE")
    private char hasActive;

    @Column(name = "ISREMOVE")
    private int isRemove;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "LAST_LOGIN_DATE",columnDefinition="DATE")
    private Date lastLoginDate;

    @Column(name = "NUMBER_OF_BAD_LOGIN")
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

    public int getLoginCode() {
        return loginCode;
    }

    public void setLoginCode(int loginCode) {
        this.loginCode = loginCode;
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


