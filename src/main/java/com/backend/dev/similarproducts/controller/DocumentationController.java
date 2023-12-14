package com.backend.dev.similarproducts.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller for documentation.
 */
@RestController
@RequestMapping("/documentation/")
public class DocumentationController {

	public DocumentationController() {
		super();
	}

	/**
	 * Open SimilarProducts swagger with API information.
	 * 
	 * @return ModelAndView Swagger page.
	 */
	@GetMapping(path = "")
	public ModelAndView redirectDocumentationSwagger() {
		return new ModelAndView("redirect:/documentation/swagger");
	}

	/**
	 * Open SimilarProducts swagger with API information.
	 * 
	 * @return ModelAndView Swagger page.
	 */
	@GetMapping(path = "swagger")
	public ModelAndView openDocumentationSwagger() {
		return new ModelAndView("/similarproducts_swagger.html");
	}

}
