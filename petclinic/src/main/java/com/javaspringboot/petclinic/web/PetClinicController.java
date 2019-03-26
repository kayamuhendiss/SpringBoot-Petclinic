package com.javaspringboot.petclinic.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.javaspringboot.petclinic.service.PetClinicService;

@Controller
public class PetClinicController {
	
		
	@Autowired
    private PetClinicService petClinicService;
	

	
	@RequestMapping("/pcs")
	@ResponseBody
	public String welcome() {
		return "Welcomee PetClinic";
	}
	
	@RequestMapping("/owners")
	public ModelAndView getOwners() {
		ModelAndView model=new ModelAndView();
		model.addObject("owners", petClinicService.findOwners());
		model.setViewName("owners");
		return model;
		
	}
	

}
 