package civilianBank.test.service.systemUser;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import civilianBank.entity.systemUser.SystemUserEntity;
import civilianBank.repository.systemUser.SystemUserRepository;
import civilianBank.service.systemUser.SystemUserService;
import civilianBank.test.configuration.systemUser.SyetemUserTestConfiguration;

public class SystemUserAuthenticationTest {
	MockMvc mock;
	@Mock
	SystemUserRepository repository;
	@InjectMocks
	SystemUserService service;

	@Before
	public void setup() {
		this.mock = MockMvcBuilders.standaloneSetup(service).build();
	}
	@Test
	public void login(){
		SystemUserEntity entity = new SystemUserEntity();
		entity = SyetemUserTestConfiguration.user();
		
//		when(repository.checkUserNameAndPassword());
	}

}
