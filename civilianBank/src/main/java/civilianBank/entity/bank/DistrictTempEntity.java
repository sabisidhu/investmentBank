package civilianBank.entity.bank;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Created by BS-102 on 8/12/2014.
 */
@Entity
@Table(name="DISTRICT_TEMP")
public class DistrictTempEntity {

    @Id
    @Column
    @SequenceGenerator(name = "DistrictTempIdGenerator", sequenceName = "DISTRICT_TEMP_ID", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DistrictTempIdGenerator")
    private long id;

    @Column(name ="CODE", nullable = false)
    @Size(max = 10)
    private String code;

    @Column(name ="NAME", nullable = false)
    @Size(max = 50)
    private String name;

    @Column(name = "IS_ACTIVE")
    private int isActive;

    @Column(name = "DISTRICT_ID", nullable = false)
    private long districtId;

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getDistrictId() {
        return districtId;
    }

    public void setDistrictId(long districtId) {
        this.districtId = districtId;
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

