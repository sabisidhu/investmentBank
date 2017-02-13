package civilianBank.controller.systemUser;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import civilianBank.entity.systemUser.AuthenticationRequest;
import civilianBank.entity.systemUser.SystemUserEntity;
import civilianBank.service.systemUser.SystemUserAuthenticationService;
import civilianBank.service.systemUser.SystemUserService;

@RestController
// @RequestMapping(value = "adminstration/systemuser")
public class SystemUserAuthenticationController {
	@Autowired
	SystemUserAuthenticationService services;
	Logger logger = LoggerFactory.getLogger(SystemUserAuthenticationController.class);

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public Map<String, Object> login(@RequestBody AuthenticationRequest authenticationRequest) {
		logger.debug("test1");
		Map<String, Object> response = new HashMap<String, Object>();
		response = services.login(authenticationRequest);
		logger.debug("test2");
		return response;

	}

	@RequestMapping(value = "/logout{id)", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public SystemUserEntity logout(@PathVariable("id") final int id) {
		SystemUserEntity entity = services.logout(id);
		return entity;

	}

}
