package br.senac.tads.pw.jsonserver.webmvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@Controller
public class IndexController {

	@GetMapping
	public String index() {
		return "index-template";
	}

	@ModelAttribute("serverDomain")
	public String getServerDomain() {
		return ServletUriComponentsBuilder.fromCurrentContextPath().toUriString();
	}

}
