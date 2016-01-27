package com.telenav.tnassets.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.telenav.tnassets.service.TnAssetsService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	@Autowired
	TnAssetsService tnassetsService;
	
//	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		return "home";
	}
	
	@RequestMapping(value = "/instances", method = RequestMethod.GET)
	public String topTalker(Locale locale, Model model) {
		return "instances";
	}
	
	@RequestMapping(value = "/import30", method = RequestMethod.GET)
	public String import30Days() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				tnassetsService.importLastDays(30);
			}
			
		}).start();;
		return "redirect:/";
	}
	
	
	
}
