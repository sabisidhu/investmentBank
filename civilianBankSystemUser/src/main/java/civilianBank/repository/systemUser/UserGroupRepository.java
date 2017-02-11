package civilianBank.repository.systemUser;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import civilianBank.entity.systemUser.UserGroupEntity;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Created by Parshotam
 */
@Repository
public class UserGroupRepository{

    @Value("${superAdmin.UserGroup.Id}")
    private int superAdminUserGroupId;
    @PersistenceContext
    EntityManager entityManger;

    public List<UserGroupEntity> getAllUserGroupWithoutSuperAdmin()
    {
        Query query = entityManger.createQuery("FROM UserGroupEntity WHERE ID != :superAdminUserGroupId and isActive = 'Y' order by GROUP_NAME asc");
        query.setParameter("superAdminUserGroupId",superAdminUserGroupId);
        return query.getResultList();
    }

    public List<UserGroupEntity> getAllActiveGroups() {
        Query query = entityManger.createQuery("FROM UserGroupEntity WHERE isActive = 'Y' order by lower(GROUP_NAME) asc ");
        return query.getResultList();
    }
    public UserGroupEntity getUserGroupById(int id) {
    	Query query = entityManger.createQuery("FROM UserGroupEntity WHERE isActive = 'Y' and ID = :id order by lower(GROUP_NAME) asc ");
    	query.setParameter("id", id);
    	return (UserGroupEntity)query.getSingleResult();
	}
}
