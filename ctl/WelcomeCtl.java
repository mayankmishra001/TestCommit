/*
 * @author Mayank
 */
package in.co.rays.project_0.ctl;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * The Class WelcomeCtl.
 */
@Controller
@RequestMapping("/Welcome")
public class WelcomeCtl {

	/**
	 * Display.
	 *
	 * @param model the model
	 * @return the string
	 */
	@RequestMapping(method = RequestMethod.GET)
     public String display(ModelMap model){
		System.out.println("welcome page");
		return "Welcome";
	}
}
