package civilianBank.entity.bank;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Created by BS23 on 5/28/14.
 */
@Entity
@Table(name="BANK_BRANCH_TEMP")
public class BankBranchTempEntity {

    @Id
    @Column
    @SequenceGenerator(name="BankBranchTempIdGenerator", sequenceName="Bank_Branch_Temp__Id", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BankBranchTempIdGenerator")
    private int id;

    @Column(name = "DISTRICT_ID")
    private long districtId;

    @Column(name = "BANK_ID")
    private int bankId;

    @Column(name ="CODE")
    @Size(max = 10)
    private String branchCode;

    @Column(name ="NAME")
    @Size(max = 100)
    private String branchName;

    @Column(name ="ROUTING_NUMBER")
    @Size(max = 20)
    private String routingNumber;

    @Column(name = "ISREMOVE")
    private int isRemove;

    @Column(name = "SOL_ID")
    private int solId;

    @Column(name = "BANK_BRANCH_ID")
    private int bankBranchId;

    @Column(name="CHECKER_ID")
    private int checkerId;

    @Column(name="MAKER_ID")
    private int makerId;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name="OPERATION_DATE",columnDefinition="DATE")
    private Date operationDate;

    @Column(name="CREATE_OR_UPDATE_STATUS")
    private int createOrUpdateStatus;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name="APPROVAL_DATE",columnDefinition="DATE")
    private Date approvalDate;

    public int getSolId() {
        return solId;
    }

    public void setSolId(int solId) {
        this.solId = solId;
    }

    public Date getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(Date approvalDate) {
        this.approvalDate = approvalDate;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getDistrictId() {
        return districtId;
    }

    public void setDistrictId(long districtId) {
        this.districtId = districtId;
    }

    public int getBankId() {
        return bankId;
    }

    public void setBankId(int bankId) {
        this.bankId = bankId;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getRoutingNumber() {
        return routingNumber;
    }

    public void setRoutingNumber(String routingNumber) {
        this.routingNumber = routingNumber;
    }

    public int getIsRemove() {
        return isRemove;
    }

    public void setIsRemove(int isRemove) {
        this.isRemove = isRemove;
    }

    public int getBankBranchId() {
        return bankBranchId;
    }

    public void setBankBranchId(int bankBranchId) {
        this.bankBranchId = bankBranchId;
    }


}
