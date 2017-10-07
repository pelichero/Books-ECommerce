package br.edu.caelum.validator;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.edu.caelum.models.Product;

public class ProductValidator implements Validator{

	private static final String REQUIRED = "field.required";

	@Override
	public boolean supports(Class<?> clazz) {
		return Product.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		Product product = (Product) target;
	
		if(StringUtils.isEmpty(product.getTitle())){
			errors.rejectValue("title", REQUIRED);
		}
		
		if(StringUtils.isEmpty(product.getDescription())){
			errors.rejectValue("description", REQUIRED);
		}
		
		if(product.getNumberOfPages() == 0){
			errors.rejectValue("numberOfPages", REQUIRED);
		}
	}

}
