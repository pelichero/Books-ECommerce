package br.edu.caelum.controller;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.caelum.dao.ProductDAO;
import br.edu.caelum.models.BookType;
import br.edu.caelum.models.Product;
import br.edu.caelum.validator.ProductValidator;

@Controller
@RequestMapping("/products")
public class ProductsController {

	@Autowired
	private ProductDAO dao;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder){
		binder.setValidator(new ProductValidator());
	}

	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView list(){
		ModelAndView modelAndView = new ModelAndView("products/list");
		modelAndView.addObject("products", dao.list());
		return modelAndView;
	}
	
	@RequestMapping("/form")
	public ModelAndView form(Product product){
		ModelAndView modelAndView = new ModelAndView("products/form");
		modelAndView.addObject("types", BookType.values());
		return modelAndView;
	}
	
	@Transactional
	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView save(@Valid Product product, BindingResult bindingResult, RedirectAttributes redirectAtt){
		if(bindingResult.hasErrors()){
			return form(product);
		}
		
		dao.save(product);
		redirectAtt.addFlashAttribute("sucesso", "Produto cadastrado com sucesso");
		return new ModelAndView("redirect:products");
	}
}