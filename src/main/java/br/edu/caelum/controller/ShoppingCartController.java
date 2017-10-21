package br.edu.caelum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.caelum.dao.ProductDAO;
import br.edu.caelum.models.BookType;
import br.edu.caelum.models.Product;
import br.edu.caelum.models.ShoppingCart;
import br.edu.caelum.models.ShoppingItem;
import br.edu.caelum.service.ProcessPayementService;

import java.util.concurrent.Callable;

@Controller
@RequestMapping("/shopping")
public class ShoppingCartController {

	@Autowired
	private ProductDAO dao;
	
	@Autowired
	private ShoppingCart shoppingCart;
	
	@Autowired
	private ProcessPayementService paymentService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String list(){
		return "shoppingcart/items";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView add(Integer productId, @RequestParam BookType bookType){
		shoppingCart.add(createItem(productId, bookType));
		return new ModelAndView("redirect:/products");
	}
	
	@RequestMapping(value="/checkout", method=RequestMethod.POST)
	public Callable<String> checkout(RedirectAttributes redirectAtt){
		return () -> {
			try {
				paymentService.processPayment(shoppingCart.getTotal());
				redirectAtt.addFlashAttribute("sucesso", "Pagamento xpto foi gerado com sucesso.")
				return "redirect:/produto";
			} catch (Exception e) {
				redirectAtt.addFlashAttribute("erro", "Não foi possível gerar o pagamento.")
				return "redirect:/shopping";
			}
		}
	}

	private ShoppingItem createItem(Integer productId, BookType bookType) {
		Product product = dao.find(productId);
		return new ShoppingItem(product, bookType);
	}
}
