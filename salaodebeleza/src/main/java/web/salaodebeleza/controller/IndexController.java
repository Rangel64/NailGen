package web.salaodebeleza.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

	@GetMapping(value = {"/", "/index.html"})
	public String index(Model model) {
		return "index";
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}

}