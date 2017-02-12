package civilianBank.controller.systemUser;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import civilianBank.entity.systemUser.SystemUserEntity;
import civilianBank.service.systemUser.SystemUserService;

/**
 * Created by Parshotam
 */
@RequestMapping(value = "/administration/systemuser")
@Controller
// @PreAuthorize("denyAll")
public class SystemUserController {
	@Autowired
	SystemUserService service;

	/**
	 * REST call to save the account with the given system user object
	 * 
	 * @param system
	 *            user entity
	 * @return user id
	 */
	@RequestMapping(value = "/account", method = RequestMethod.POST)
	public ResponseEntity<SystemUserEntity> create(@RequestBody SystemUserEntity systemUser,
			UriComponentsBuilder builder) {

		// logger.info("AccountController.find: id=" + id);
		// int id = service.

		int id = service.systemUserCreate(systemUser);
		HttpHeaders headers = new HttpHeaders();

		headers.setLocation(builder.path("/account/{id}").buildAndExpand(id).toUri());
		return new ResponseEntity<SystemUserEntity>(headers, HttpStatus.CREATED);

	}

	/**
	 * REST call to save the account with the given system user object
	 * 
	 * @param system
	 *            user entity
	 * @return user id
	 */
	@RequestMapping(value = "/account/", method = RequestMethod.POST)
	public ResponseEntity<List<SystemUserEntity>> getAllActiveUserGroupList() {
		List<SystemUserEntity> ls;

//		ls = service.ge

		HttpHeaders headers = new HttpHeaders();

		return new ResponseEntity<List<SystemUserEntity>>(headers, HttpStatus.OK);

	}
	
	@RequestMapping(value = "/account/{id}", method = RequestMethod.GET)
	public ResponseEntity<SystemUserEntity> getUserById(@PathVariable("id" ) final int id ) {
		SystemUserEntity ls ;

		ls = service.getSystemUserById(id);

		HttpHeaders headers = new HttpHeaders();

		return new ResponseEntity<SystemUserEntity>(headers, HttpStatus.OK);

	}
	@RequestMapping(value = "/account/email/{email}", method = RequestMethod.GET)
	public ResponseEntity<SystemUserEntity> getUserByEmail(@PathVariable("email" ) final String email ) {
		SystemUserEntity ls ;

		ls = service.getSystemUserByEmail(email);

		HttpHeaders headers = new HttpHeaders();

		return new ResponseEntity<SystemUserEntity>(headers, HttpStatus.OK);

	}

}
