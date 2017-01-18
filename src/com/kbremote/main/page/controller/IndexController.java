package com.kbremote.main.page.controller;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {

	@RequestMapping(value = { "/", "/login" }, method = RequestMethod.GET)
	public String getDefaultPage(HttpServletRequest request, ModelMap model) {
		model.addAttribute("localIp", request.getLocalAddr());
		return "login";
	}

	@RequestMapping(value = "/admin/config", method = RequestMethod.GET)
	public String getConfigurationPage(ModelMap model) {
		return "config";
	}

	@RequestMapping(value = "/user/remote", method = RequestMethod.GET)
	public String getRemotePage(ModelMap model) {

		return "remote";
	}
	
	@RequestMapping(value = "/user/camera", method = RequestMethod.GET)
	public String getCameraPage(ModelMap model) {

		return "camera";
	}

	@RequestMapping(value = { "/user", "/user/angles" }, method = RequestMethod.GET)
	public String getAnglesPage(ModelMap model) {

		return "angles";
	}
	
	@RequestMapping(value = "/user/index", method = RequestMethod.GET)
	public String getUserIndex(ModelMap model) {

		return "common/index";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request,
			HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Başarıyla çıkış yaptınız."));
		return "redirect:/login?logout";
	}

}
