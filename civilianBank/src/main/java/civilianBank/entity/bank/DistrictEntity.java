package civilianBank.entity.bank;

import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 * Created by BS23 on 5/16/14.
 */
@Entity
@Table(name="DISTRICT")
public class DistrictEntity {

    @Id
    @Column
    @SequenceGenerator(name = "DistrictIdGenerator", sequenceName = "DISTRICT_ID", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DistrictIdGenerator")
    private long id;

    @Column(name ="CODE")
    @Size(max = 10)
    private String code;

    @Column(name ="NAME")
    @Size(max = 50)
    private String name;

    @Column(name = "IS_ACTIVE")
    private int isActive;

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

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }
}
