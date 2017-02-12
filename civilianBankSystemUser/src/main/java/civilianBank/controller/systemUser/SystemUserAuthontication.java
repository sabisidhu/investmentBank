package civilianBank.controller.systemUser;

import javax.ws.rs.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import civilianBank.entity.systemUser.AuthenticationRequest;
import civilianBank.entity.systemUser.SystemUserEntity;
import civilianBank.service.systemUser.SystemUserService;

@RestController
@RequestMapping(value = "adminstration/systemuser")
public class SystemUserAuthontication {
	@Autowired
	SystemUserService services;
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public SystemUserEntity login(@RequestBody AuthenticationRequest authenticationRequest) {
		
		
	SystemUserEntity entity =	services.login(authenticationRequest);
	return entity;

	}

}
