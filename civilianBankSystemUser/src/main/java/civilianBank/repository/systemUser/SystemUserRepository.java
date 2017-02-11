package civilianBank.repository.systemUser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import civilianBank.entity.systemUser.SystemUserEntity;
import civilianBank.entity.systemUser.UserGroupEntity;
import civilianBank.exception.systemUser.NoRecordsFoundException;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Created by Parshotam.
 */
@Repository
public class SystemUserRepository {
	@PersistenceContext
	EntityManager entityManager;
	Logger logger = LoggerFactory.getLogger(SystemUserRepository.class);

	/**
	 * @Purpose Check User Name and Password.
	 * @param userName
	 *            and Password
	 * @return SystemUserEnotity
	 */

	public SystemUserEntity checkUserNameAndPassword(String systemUserName, String password) {
		logger.debug("SystemUserRepository.checkUserNameAndPassword checking userName and password with :"
				+ systemUserName + " and " + password);
		Query query = entityManager.createQuery(
				"FROM SystemUserEntity WHERE lower(USERNAME) =:systemUserName AND PASSWORD=:password AND ISREMOVE =:isRemove And hasActive = 'Y'");
		query.setParameter("systemUserName", systemUserName.toLowerCase());
		query.setParameter("password", password);
		query.setParameter("isRemove", 0);
		query.setMaxResults(1);
		if ((SystemUserEntity) query.getSingleResult() == null) {
			logger.warn("SystemUserRepository.checkUserNameAndPassword not found with userName and password :"
					+ systemUserName + " and " + password);
			throw new NoRecordsFoundException();
		}
		logger.info("SystemUserRepository.checkUserNameAndPassword retrieved information with userName and password :"
				+ systemUserName + " and " + password);
		return (SystemUserEntity) query.getSingleResult();
	}

	/**
	 * @Purpose get user group by user id.
	 * @param userId
	 * 
	 * @return user group
	 */

	public int getUserGroupByUserId(int userId) {
		logger.debug("SystemUserRepository.getUserGroupByUserId get user group  with userId :" + userId);
		Query query = entityManager.createQuery("SELECT USERGROUP FROM SystemUserEntity WHERE id =:systemUserId");
		query.setParameter("systemUserId", userId);
		query.setMaxResults(1);
		if ((Long) query.getSingleResult() == null) {
			logger.warn("SystemUserRepository.getUserGroupByUserId not foud user group  with userId :" + userId);
			throw new NoRecordsFoundException();
		}
		int result1 = ((Long) query.getSingleResult()).intValue();
		logger.info("SystemUserRepository.getUserGroupByUserId retrieved user group  with userId :" + userId);
		return result1;
	}
	
	/**
	 * @Purpose get user group by user id.
	 * @param userId
	 * 
	 * @return user group
	 */

	public SystemUserEntity getSystemUserByUserId(int userId) {
		logger.debug("SystemUserRepository.getSystemUserByUserId get user group  with userId :" + userId);
		Query query = entityManager.createQuery("SELECT a FROM SystemUserEntity WHERE id =:systemUserId");
		query.setParameter("systemUserId", userId);
		if ( query.getSingleResult() == null) {
			logger.warn("SystemUserRepository.getSystemUserByUserId not foud user group  with userId :" + userId);
			throw new NoRecordsFoundException();
		}
		 
		 logger.info("SystemUserRepository.getSystemUserByUserId retrieved user group  with userId :" + userId);
		return (SystemUserEntity)query.getSingleResult();
	}

	/**
	 * @Purpose get system user total number record
	 * @param searchUserName,
	 *            searchUser, ShortName, searchEmail, branchCode
	 * 
	 * @return user total number
	 */

	public int getSystemUserTotalRecordNumber(String searchUserName, String searchUserShortName, String searchEmail,
			int branchCode) {

		logger.debug("SystemUserRepository.getSystemUserTotalRecordNumber getting user group  with parameter  :"
				+ searchEmail + " " + searchUserShortName + " " + searchEmail + " " + branchCode);
		Query query = entityManager.createQuery(
				"SELECT count(*) FROM SystemUserEntity WHERE BRCODE =:branchCode AND lower(USERNAME) LIKE lower(:searchUserName) AND  lower(USERSHORTNAME) LIKE lower(:searchUserShortName) AND lower(EMAILID) like lower(:searchEmail) AND ISREMOVE =:isRemove ");
		query.setParameter("searchUserName", "%" + searchUserName + "%");
		query.setParameter("searchUserShortName", "%" + searchUserShortName + "%");
		query.setParameter("searchEmail", "%" + searchEmail + "%");
		query.setParameter("branchCode", branchCode);
		query.setParameter("isRemove", 0);
		query.setMaxResults(1);
		if ((Long) query.getSingleResult() == null) {
			logger.warn(
					"SystemUserRepository.getSystemUserTotalRecordNumber not found user group  with parameter  :"
				+ searchEmail + " " + searchUserShortName + " " + searchEmail + " " + branchCode);
			throw new NoRecordsFoundException();
		}
		int result = ((Long) query.getSingleResult()).intValue();
		logger.info("SystemUserRepository.getSystemUserTotalRecordNumber retrieved user group  with parameter  :"
				+ searchEmail + " " + searchUserShortName + " " + searchEmail + " " + branchCode);
		return result;
	}

	public List<SystemUserEntity> getAll() {
		Query query = entityManager.createQuery("FROM SystemUserEntity WHERE ISREMOVE =:isRemove");
		query.setParameter("isRemove", 0);
		return query.getResultList();
	}

	public List<SystemUserEntity> getAllAuthorizedSystemUser(String menuCode, int branchCode) {
		Query query = entityManager.createQuery(
				"FROM SystemUserEntity WHERE BRCODE =:branchCode and hasActive = 'Y' and USERGROUP IN (Select groupId FROM MenuEntity mt WHERE AUTHORISE_PERMISSION = :autho AND mt.siteMenuLinkEntity.menuCode = :menuCode ) order by lower(USERSHORTNAME) asc");
		query.setParameter("branchCode", branchCode);
		query.setParameter("autho", 'Y');
		query.setParameter("menuCode", menuCode);
		return query.getResultList();
	}

	public SystemUserEntity getSystemUserByUserName(String userName) {
		Query query = entityManager
				.createQuery("FROM SystemUserEntity WHERE lower(USERNAME) = :systemUserName And ISREMOVE =:isRemove");
		query.setParameter("systemUserName", userName.toLowerCase());
		query.setParameter("isRemove", 0);
		query.setMaxResults(1);
		return (SystemUserEntity) query.getSingleResult();
	}

	public SystemUserEntity checkPassword(int id, String password) {
		Query query = entityManager.createQuery("FROM SystemUserEntity WHERE ID =:id AND password = :pass");
		query.setParameter("id", id);
		query.setParameter("pass", password);
		query.setMaxResults(1);
		return (SystemUserEntity) query.getSingleResult();
	}

	public SystemUserEntity getSystemUserByEmail(String email) {
		Query query = entityManager
				.createQuery("FROM SystemUserEntity WHERE lower(emailId) = :email And ISREMOVE =:isRemove");
		query.setParameter("email", email.toLowerCase());
		query.setParameter("isRemove", 0);
		query.setMaxResults(1);
		return (SystemUserEntity) query.getSingleResult();
	}

	public void updateSystemUserGroupStatus(UserGroupEntity userGroupEntity) {
		Query query = entityManager.createQuery(
				"update SystemUserEntity set hasActive = :is_Active  WHERE userGroup = :groupId And ISREMOVE =:isRemove");
		query.setParameter("groupId", userGroupEntity.getId());
		query.setParameter("is_Active", userGroupEntity.getIsActive());
		query.setParameter("isRemove", 0);
		query.executeUpdate();
	}

	public void updateSystemUserBranch(int oldSOLId, int newSOLId) {
		Query query = entityManager
				.createQuery("update SystemUserEntity set branchCode = :newSOLId  WHERE branchCode = :oldSOLId");
		query.setParameter("oldSOLId", oldSOLId);
		query.setParameter("newSOLId", newSOLId);
		query.executeUpdate();
	}

	public String getSystemUserNameById(int id) {
		Query query = entityManager.createQuery(
				"SELECT sue.userShortName FROM SystemUserEntity sue WHERE sue.id = :id And ISREMOVE =:isRemove");
		query.setParameter("id", id);
		query.setParameter("isRemove", 0);
		query.setMaxResults(1);
		return query.getSingleResult().toString();
	}
}
