package civilianBank.entity.systemUser;

import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 * Created by Parshotam
 */
@Entity
@Table(name = "USER_GROUP")
public class UserGroupEntity {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public char getIsActive() {
        return isActive;
    }

    public void setIsActive(char isActive) {
        this.isActive = isActive;
    }

    @Id
    @Column
    @SequenceGenerator(name="UserGroupIdGenerator", sequenceName="USER_GROUP__ID", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "UserGroupIdGenerator")
    private int id;

    @Column(name = "GROUP_NAME")
    @Size(max = 100)
    private String groupName;

    @Column(name = "IS_ACTIVE")
    private char isActive;
}
