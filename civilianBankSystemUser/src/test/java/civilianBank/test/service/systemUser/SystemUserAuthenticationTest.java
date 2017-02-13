package civilianBank.test.service.systemUser;

import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import civilianBank.entity.systemUser.SystemUserEntity;
import civilianBank.exception.systemUser.NoRecordsFoundException;
import civilianBank.repository.systemUser.SystemUserRepository;
import civilianBank.service.systemUser.SystemUserAuthenticationService;
import civilianBank.test.configuration.systemUser.SyetemUserTestConfiguration;
import civilianBank.test.configuration.systemUser.SytemUserTestAuthentication;
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class SystemUserAuthenticationTest {
	Logger logger = LoggerFactory.getLogger(SystemUserAuthenticationTest.class);
	MockMvc mock;
	@Mock
	SystemUserRepository repository;
	@InjectMocks
	SystemUserAuthenticationService auth;

	@Before
	public void setup() {
		logger.debug("Test Start");
		this.mock = MockMvcBuilders.standaloneSetup(auth).build();
	}

	@Test
	public void login() {
		logger.debug("Test Start");
		SystemUserEntity entity = new SystemUserEntity();
		entity = SyetemUserTestConfiguration.user();
		
		when(repository.checkUserNameAndPassword(SytemUserTestAuthentication.authenticationRequest()))
				.thenReturn(entity);
		auth.login(SytemUserTestAuthentication.authenticationRequest());
	}
	

}
