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


	// public void createSystemUser(SystemUserEntity systemUserEntity) {
	// systemUserRepository.create(systemUserEntity);
	// }

	public UserGroupEntity getUserGroupByUserGroupId(int groupId) {
		return userGroupRepository.getUserGroupById(groupId);
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

}
