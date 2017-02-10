package civilianBank.entity.bank;

import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 * Created by BS23 on 5/16/14.
 */
@Entity
@Table(name="BANK_BRANCH")
public class BankBranchEntity {

    @Id
    @Column
    @SequenceGenerator(name="BankBranchIdGenerator", sequenceName="Bank_Branch__Id", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BankBranchIdGenerator")
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "DISTRICT_ID")
    private DistrictEntity district;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "BANK_ID")
    private BankEntity bank;

    @Column(name ="CODE")
    @Size(max = 10)
    private String branchCode;

    @Column(name ="NAME")
    @Size(max = 100)
    private String branchName;

    @Column(name ="ROUTING_NUMBER")
    @Size(max = 20)
    private String routingNumber;

    @Column(name = "SOL_ID")
    private int solId;

    @Column(name = "ISREMOVE")
    private int isRemove;

    public int getSolId() {
        return solId;
    }

    public void setSolId(int solId) {
        this.solId = solId;
    }

    public int getIsRemove() {
        return isRemove;
    }

    public void setIsRemove(int isRemove) {
        this.isRemove = isRemove;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DistrictEntity getDistrict() {
        return district;
    }

    public void setDistrict(DistrictEntity district) {
        this.district = district;
    }

    public BankEntity getBank() {
        return bank;
    }

    public void setBank(BankEntity bank) {
        this.bank = bank;
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
}
