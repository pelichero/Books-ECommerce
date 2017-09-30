package br.edu.caelum.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
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
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", REQUIRED);		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", REQUIRED);
		
		Product product = (Product) target;
		
		if(product.getNumberOfPages() == 0){
			errors.rejectValue("numberOfPages", REQUIRED);
		}
	}

}
