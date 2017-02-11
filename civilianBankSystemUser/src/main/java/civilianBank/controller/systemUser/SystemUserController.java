package civilianBank.controller.systemUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
	
	


}
