package br.edu.caelum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.edu.caelum.dao.ProductDAO;
import br.edu.caelum.models.BookType;
import br.edu.caelum.models.Product;
import br.edu.caelum.models.ShoppingCart;
import br.edu.caelum.models.ShoppingItem;

@Controller
@RequestMapping("/shopping")
public class ShoppingCartController {

	@Autowired
	private ProductDAO dao;
	
	@Autowired
	private ShoppingCart shoppingCart;
	
	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView add(Integer productId, @RequestParam BookType bookType){
		shoppingCart.add(createItem(productId, bookType));
		return new ModelAndView("redirect:/products");
	}

	private ShoppingItem createItem(Integer productId, BookType bookType) {
		Product product = dao.find(productId);
		return new ShoppingItem(product, bookType);
	}
}
