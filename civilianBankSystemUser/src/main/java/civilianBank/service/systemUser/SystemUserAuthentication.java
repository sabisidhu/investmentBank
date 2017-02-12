package civilianBank.service.systemUser;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import civilianBank.entity.systemUser.AuthenticationRequest;
import civilianBank.entity.systemUser.SystemUserEntity;
import civilianBank.exception.systemUser.NoRecordsFoundException;
import civilianBank.repository.systemUser.SystemUserRepository;

@Service
public class SystemUserAuthentication {
	@Autowired
	SystemUserRepository repository;

	public Map<String, Object> login(AuthenticationRequest request) {
		SystemUserEntity entity;
		entity = repository.checkUserNameAndPassword(request);
		Map<String, Object> response = null;

		if (entity != null) {
			entity.setLoginCode(UUID.randomUUID());
			response = new HashMap<String, Object>();
			response.put("loginCode", entity.getLoginCode());
			response.put("userId", entity.getId());

		} else {
			throw new NoRecordsFoundException();
		}
		return response;

	}

	public void passwordChangeRequest() {

	}

	public SystemUserEntity logout(int userId) {
		SystemUserEntity entity;
		entity = repository.getSystemUserByUserId(userId);
		entity.setLoginCode(null);
		return entity;

	}

}
