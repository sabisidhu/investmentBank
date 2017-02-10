package civilianBank.entity.bank;

import javax.persistence.*;
import javax.validation.constraints.Size;
//import java.util.Set;

/**
 * Created by BS23 on 5/16/14.
 */
@Entity
@Table(name = "BANK")
public class BankEntity {

    @Id
    @Column
    @SequenceGenerator(name = "BankIdGenerator", sequenceName = "BANK__ID", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BankIdGenerator")
    private int id;

    @Column(name ="CODE")
    @Size(max = 10)
    private String bankCode;

    @Column(name ="NAME")
    @Size(max = 50)
    private String bankName;

    @Column(name = "IS_ACTIVE")
    private int isActive;

//    @OneToMany(fetch = FetchType.LAZY,mappedBy ="bank")
//    private Set<BankBranchEntity> bankBranches;


    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

//    public Set<BankBranchEntity> getBankBranches() {
//        return bankBranches;
//    }
//
//    public void setBankBranches(Set<BankBranchEntity> bankBranches) {
//        this.bankBranches = bankBranches;
//    }

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


}
