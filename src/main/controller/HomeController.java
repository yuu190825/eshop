package main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	

	@RequestMapping("/")
	public String getHome() {
//		StringBuilder sBuilder = new StringBuilder("")
//				.append("a");
		return "index";
		// return "index2";
	}

}
