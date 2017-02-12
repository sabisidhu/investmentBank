package civilianBank.test.configuration.systemUser;

import static org.mockito.Mockito.mock;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import civilianBank.entity.systemUser.SystemUserEntity;
import civilianBank.repository.systemUser.SystemUserRepository;

@Configuration
@Profile("test")
public class SyetemUserTestConfiguration {
	static public int id = 12;
	static public String userCode = "anyCode";
	static public UUID loginCode = UUID.randomUUID();
	static public String userName = "Sabi";
	static public String userShortName = "Sabi";
	static public String empCode = "mngr";//
	static public int branchCode = 321;
	static public int userGroup = 3;
	static public int userStatus = 1;
	static public String emailId = "dummy@gmail.com";
	static public String password = "anyPassword";
	static public char pwdChangedForced;
	static public int pwdChangePeriodDays = 1;
	static public Date pwdChangePreviousDate = new Date();
	static public Date pwdChangeNextDate = new Date();
	static public char minLifeReqForcedYN = 'Y';
	static public int minLifePeriodDays = 3;
	static public int maxBadLifePeriodDay = 3;
	static public int pwdLockedPeriodMinute = 2;
	static public char hasActive = 'Y';
	static public int isRemove = 0;
	static public Date lastLoginDate = new Date();
	static public int numberOfBadLogin = 0;

	@Bean
	public static SystemUserEntity user() {
		SystemUserEntity entity = new SystemUserEntity();
		entity.setBranchCode(branchCode);
		entity.setEmailId(emailId);
		entity.setEmpCode(empCode);
		entity.setHasActive(hasActive);
		entity.setId(id);
		entity.setIsRemove(isRemove);
		entity.setLastLoginDate(lastLoginDate);
		entity.setLoginCode(loginCode);
		entity.setMaxBadLifePeriodDay(maxBadLifePeriodDay);
		entity.setMinLifePeriodDays(minLifePeriodDays);
		entity.setMinLifeReqForcedYN(minLifeReqForcedYN);
		entity.setNumberOfBadLogin(numberOfBadLogin);
		entity.setPassword(password);
		entity.setPwdChangedForced(pwdChangedForced);
		entity.setPwdChangeNextDate(pwdChangeNextDate);
		entity.setPwdChangePeriodDays(pwdChangePeriodDays);
		entity.setPwdChangePreviousDate(pwdChangePreviousDate);
		entity.setPwdLockedPeriodMinute(pwdLockedPeriodMinute);
		entity.setUserCode(userCode);
		entity.setUserGroup(userGroup);
		entity.setUserName(userName);
		entity.setUserShortName(userShortName);
		entity.setUserStatus(userStatus);
		return entity;

	}

	@Bean
	public static Map<String, Object> loginResponse() {
		Map<String, Object> loginResponse = new HashMap<String, Object>();

		loginResponse.put("authToken", UUID.randomUUID());
		loginResponse.put("accountid", SyetemUserTestConfiguration.id);
		return loginResponse;
	}
	@Bean
	public static SystemUserRepository systemUserRpository(){
		SystemUserRepository repository = Mockito.mock(SystemUserRepository.class);
		return repository;
	}
}
