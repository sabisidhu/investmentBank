package civilianBank.test.controller.systemUser;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;

import civilianBank.controller.systemUser.SystemUserAuthenticationController;
import civilianBank.entity.systemUser.AuthenticationRequest;
import civilianBank.service.systemUser.SystemUserAuthenticationService;
import civilianBank.test.configuration.systemUser.SyetemUserTestConfiguration;
import civilianBank.test.configuration.systemUser.SytemUserTestAuthentication;
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class SystemUserAuthenticationTest {
	Logger logger = LoggerFactory.getLogger(SystemUserAuthenticationTest.class);
	MockMvc mock;
	@Mock
	SystemUserAuthenticationService service;
	@InjectMocks
	SystemUserAuthenticationController controller;

	@Before
	public void setup() {
		logger.debug("Test Start");
		this.mock = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void doLoginPost() throws Exception {
		when(service.login(SytemUserTestAuthentication.authenticationRequest())).thenReturn(SyetemUserTestConfiguration.loginResponse());
		
		AuthenticationRequest request = new AuthenticationRequest();
		request.setPassword(SytemUserTestAuthentication.password);
		request.setUsername(SytemUserTestAuthentication.userName);
		
		mock.perform(post("/login").contentType(MediaType.APPLICATION_JSON).content(convertObjectToJson(request)))
		.andExpect(status().isCreated())
//		.andExpect(jsonPath("$.authToken").value(ServiceTestConfiguration.AUTH_TOKEN))
//		.andExpect(jsonPath("$.accountid").value(ServiceTestConfiguration.PROFILE_ID.intValue()))
		.andDo(print());
	}
	private byte[] convertObjectToJson(AuthenticationRequest request) throws Exception{
		ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(Include.NON_NULL);
        return mapper.writeValueAsBytes(request);
	}

}
