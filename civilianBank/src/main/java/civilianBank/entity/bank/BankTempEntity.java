package civilianBank.entity.bank;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;

/**
 * Created by BS-102 on 8/12/2014.
 */
@Entity
@Table(name = "BANK_TEMP")
public class BankTempEntity {
    @Id
    @Column
    @SequenceGenerator(name = "BankTempIdGenerator", sequenceName = "BANK_TEMP__ID", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BankTempIdGenerator")
    private int id;

    @Column(name ="CODE")
    @Size(max = 10)
    private String bankCode;

    @Column(name ="NAME")
    @Size(max = 50)
    private String bankName;

    @Column(name = "BANK_ID", nullable = false)
    private int bankId;

    @Column(name="CHECKER_ID",nullable = false)
    private int checkerId;

    @Column(name="MAKER_ID",nullable = false)
    private int makerId;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name="OPERATION_DATE",columnDefinition = "DATE",nullable = false)
    private Date operationDate;

    @Column(name="CREATE_OR_UPDATE_STATUS",nullable = false)
    private int createOrUpdateStatus;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name="APPROVAL_DATE",columnDefinition = "DATE",nullable = false)
    private Date approvalDate;

    @Column(name = "IS_ACTIVE")
    private int isActive;


    @OneToMany(fetch = FetchType.EAGER,mappedBy ="bank")
    private Set<BankBranchEntity> bankBranches;

    public Set<BankBranchEntity> getBankBranches() {
        return bankBranches;
    }

    public void setBankBranches(Set<BankBranchEntity> bankBranches) {
        this.bankBranches = bankBranches;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public int getBankId() {
        return bankId;
    }

    public void setBankId(int bankId) {
        this.bankId = bankId;
    }

    public int getCheckerId() {
        return checkerId;
    }

    public void setCheckerId(int checkerId) {
        this.checkerId = checkerId;
    }

    public int getMakerId() {
        return makerId;
    }

    public void setMakerId(int makerId) {
        this.makerId = makerId;
    }

    public Date getOperationDate() {
        return operationDate;
    }

    public void setOperationDate(Date operationDate) {
        this.operationDate = operationDate;
    }

    public int getCreateOrUpdateStatus() {
        return createOrUpdateStatus;
    }

    public void setCreateOrUpdateStatus(int createOrUpdateStatus) {
        this.createOrUpdateStatus = createOrUpdateStatus;
    }

    public Date getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(Date approvalDate) {
        this.approvalDate = approvalDate;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }
}

