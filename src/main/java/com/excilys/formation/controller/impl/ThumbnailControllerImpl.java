package com.excilys.formation.controller.impl;

import java.util.Calendar;
import java.util.List;

import javax.validation.Valid;

import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.formation.controller.ThumbnailController;
import com.excilys.formation.dto.ThumbnailRequest;
import com.excilys.formation.model.Operation;
import com.excilys.formation.service.OperationService;
import com.excilys.formation.service.ThumbnailService;

@Controller
public class ThumbnailControllerImpl implements ThumbnailController {

	@Autowired
	ThumbnailService thumbnailService;
	
	@Autowired
	OperationService operationService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String welcomePage(Model model) {
	    model.addAttribute("thumbnailRequest", new ThumbnailRequest()); // the Category object is used as a template to generate the form
	    return "index";
	}

	@RequestMapping(value = "/thumbnailsGen", method = RequestMethod.POST)
	public ModelAndView processImages(@Valid @ModelAttribute("thumbnailRequest") ThumbnailRequest thumbnailRequest,
			BindingResult result) {
		// TODO Auto-generated method stub
		
		Operation operation;
		
		System.out.println("post");
		
		ModelAndView model = new ModelAndView();
		

		if (result.hasErrors()) {
			System.out.println("if");
			model.setViewName("index");
			model.addObject("message", "Problème de formulaire");
			return model;
		}
		else{
			System.out.println("else");
			model.setViewName("Resultat");
			List<String> imageList;
			if(thumbnailRequest.getLimit()>0){
				imageList = thumbnailService.processImages(thumbnailRequest.getWidth(), thumbnailRequest.getHeight(), thumbnailRequest.getLimit());
				System.out.println("imageList = " + imageList);
				operation = new Operation();
				operation.setTime(new LocalDateTime(Calendar.getInstance().getTimeInMillis()));
				operation.setMessage("imageList: " + imageList + " - " + "thumbnailRequest " + thumbnailRequest);
				operationService.save(operation);
			}
			else{
				imageList = thumbnailService.processImages(thumbnailRequest.getWidth(), thumbnailRequest.getHeight());
				System.out.println("imageList = " + imageList);
				operation = new Operation();
				operation.setTime(new LocalDateTime(Calendar.getInstance().getTimeInMillis()));
				operation.setMessage("imageList: " + imageList + " - " + "thumbnailRequest " + thumbnailRequest);
				operationService.save(operation);
			}

			model.addObject("imageList", imageList);
		}
		return model;
	}

	@Override
	public ModelAndView doGet() {
		// TODO Auto-generated method stub
		System.out.println("get");
		return null;
	}

}
