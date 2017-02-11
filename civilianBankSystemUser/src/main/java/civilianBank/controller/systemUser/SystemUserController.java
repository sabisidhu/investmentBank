package civilianBank.controller.systemUser;
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
//@PreAuthorize("denyAll")
public class SystemUserController {
	@Autowired
	SystemUserService service;
	
	/**
	 * REST call to save the account with the given system user object
	 * 
	 * @param system user entity
	 * @return user id
	 */
	@RequestMapping(value = "/account/{id}", method = RequestMethod.POST)
	public ResponseEntity<SystemUserEntity> create(@RequestBody SystemUserEntity systemUser, UriComponentsBuilder builder) {

//		logger.info("AccountController.find: id=" + id);
//		int id = service. 
		
		int id =service.systemUserCreate(systemUser);
		HttpHeaders headers = new HttpHeaders();
		 
		headers.setLocation(builder.path("/account/{id}").buildAndExpand(id).toUri());
		return new ResponseEntity<>(headers,HttpStatus.CREATED);


	}


}
