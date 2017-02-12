package civilianBank.test.configuration.systemUser;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import civilianBank.entity.systemUser.AuthenticationRequest;

@Configuration
@Profile("test")
public class SytemUserTestAuthentication {
	public static String userName="sabi";
	public static String password="password";
	public static AuthenticationRequest authenticationRequest(){
		AuthenticationRequest request = new AuthenticationRequest();
		request.setPassword(password);
		request.setUsername(userName);
		return request;
		
	}
	

}
