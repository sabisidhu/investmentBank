package civilianBank.service.systemUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import civilianBank.entity.systemUser.AuthenticationRequest;
import civilianBank.entity.systemUser.SystemUserEntity;
import civilianBank.entity.systemUser.UserGroupEntity;
import civilianBank.repository.systemUser.SystemUserRepository;
import civilianBank.repository.systemUser.UserGroupRepository;

import java.util.*;

/**
 * Created by Parshotam
 */
@Service
@Transactional
public class SystemUserService {

	@Autowired
	private SystemUserRepository systemUserRepository;

	@Autowired
	private UserGroupRepository userGroupRepository;

	@Value("${superAdmin.UserGroup.Id}")
	private int superAdminUserGroupId;

	public List<UserGroupEntity> getAllActiveUserGroupList() {
		return userGroupRepository.getAllActiveGroups();
		// return userGroupRepository.getAllUserGroupWithoutSuperAdmin();

	}

	public List<UserGroupEntity> getUserGroupList() {
		return userGroupRepository.getAllActiveGroups();
		// return userGroupRepository.getAllUserGroupWithoutSuperAdmin();

	}

	// public void createSystemUser(SystemUserEntity systemUserEntity) {
	// systemUserRepository.create(systemUserEntity);
	// }

	public UserGroupEntity getUserGroupByUserGroupId(int groupId) {
		return userGroupRepository.getUserGroupById(groupId);
	}

	public List<SystemUserEntity> getAllSystemUser() {
		return systemUserRepository.getAll();
	}

	public int getSystemUserTotalRecordNumber(String searchUserName, String searchUserShortName, String searchEmail,
			int branchCode) {
		return systemUserRepository.getSystemUserTotalRecordNumber(searchUserName, searchUserShortName, searchEmail,
				branchCode);
	}

	public SystemUserEntity getSystemUserById(int id) {
		return systemUserRepository.getSystemUserByUserId(id);
	}

	// public void updateSystemUser(SystemUserEntity systemUserEntity) {
	// systemUserRepository.update(systemUserEntity);
	// }

	public List<SystemUserEntity> getAllAuthorizedSystemUser(String menuCode, int branchCode) {
		return systemUserRepository.getAllAuthorizedSystemUser(menuCode, branchCode);
	}

	public SystemUserEntity getSystemUserByUserName(String userName) {
		return systemUserRepository.getSystemUserByUserName(userName);
	}

	public boolean checkOldPassword(int id, String password) {
		SystemUserEntity systemUserEntity = systemUserRepository.checkPassword(id, password);
		if (systemUserEntity != null) {
			return true;
		} else
			return false;
	}

	// public void createUserGroup(UserGroupEntity userGroupEntity) {
	// userGroupRepository.create(userGroupEntity);
	// }

	// public void updateUserGroup(UserGroupEntity userGroupEntity) {
	// if(userGroupEntity.getId() != superAdminUserGroupId)
	// {
	// systemUserRepository.updateSystemUserGroupStatus(userGroupEntity);
	// }
	// userGroupRepository.update(userGroupEntity);
	// }

	// public CustomerTokenEntity getCustomerTokenEntityByCustomerAccountId(int
	// customerAccountId) {
	// return
	// customerTokenRepository.getCustomerTokenEntityByCustomerAccountId(customerAccountId);
	// }
	//
	// public void deleteCustomerTokenEntity(CustomerTokenEntity
	// customerTokenEntity) {
	// customerTokenRepository.delete(customerTokenEntity);
	// }

	public SystemUserEntity getSystemUserByEmail(String searchKey) {
		return systemUserRepository.getSystemUserByEmail(searchKey);
	}

	public SystemUserEntity getSystemUserEntityUserNameCheck(String userName) {
		return systemUserRepository.getSystemUserByUserName(userName);
	}

	public SystemUserEntity getSystemUserEntityEmailCheck(String email) {
		return systemUserRepository.getSystemUserByEmail(email);
	}

	public String getSystemUserNameById(int id) {

		return systemUserRepository.getSystemUserNameById(id);
	}

	public int systemUserCreate(SystemUserEntity entity) {
		return systemUserRepository.systemUserSave(entity);
	}

	public SystemUserEntity login(AuthenticationRequest authenticationRequest) {
		return systemUserRepository.checkUserNameAndPassword(authenticationRequest.getUsername(),
				authenticationRequest.getPassword());

	}
}
