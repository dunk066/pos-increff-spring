package com.increff.pos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/ui")
public class AppUiController extends AbstractUiController {

	@RequestMapping(value = "/home")
	public ModelAndView home() {
		return mav("home.html");
	}

}
