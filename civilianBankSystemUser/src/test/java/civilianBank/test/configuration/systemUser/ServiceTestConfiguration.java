package civilianBank.test.configuration.systemUser;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class ServiceTestConfiguration {
	static public int id = 12;
	static public String userCode = "anyCode";
	static public UUID loginCode = UUID.randomUUID();
	static public String userName = "Sabi";
	static public String userShortName = "Sabi";
	static public String empCode = "mngr";//
	static public int branchCode = 321;
	static public int userGroup = 3;
	static public int userStatus1;
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

	public static Map<String, Object> loginResponse() {
		Map<String, Object> loginResponse = new HashMap<String, Object>();

		loginResponse.put("authToken", UUID.randomUUID());
		loginResponse.put("accountid", ServiceTestConfiguration.id);
		return loginResponse;
	}
}
