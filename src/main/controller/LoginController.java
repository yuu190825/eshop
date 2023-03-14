package main.controller;

import main.model.User;
import main.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;

	/**
	 * @return
	 */
	@RequestMapping("/login")
	public String showLoginPage() {
		return "login";
	}

	/**
	 * @return
	 */
	@RequestMapping("/forbidden")
	public String showForbiddenError() {
		return "403";
	}

	/**
	 * @param model
	 * @return
	 */
	@GetMapping("/sign-up")
	public String signUp(Model model) {
		model.addAttribute("user", new User());
		return "customer-form-signup";
	}

	/**
	 * @param user
	 * @param redirectAttributes
	 * @return
	 */
	@PostMapping("/process-sign-up")
	public String processSignup(@ModelAttribute User user, RedirectAttributes redirectAttributes) {
		boolean errors = false;

		if(!user.getPassword().equals(user.getConfirmedPassword())) {
			redirectAttributes.addAttribute("differentPasswords", "前後兩次密碼輸入不一致");
			errors = true;
		}

		if(userService.loginExists(user.getLogin())) {
			redirectAttributes.addAttribute("loginExists", "該用戶在資料庫已存在");
			errors = true;
		}

		if(errors) {
			return "redirect:/sign-up";
		}

		userService.createNewAccount(user);
		return "login";
	}
	
}
