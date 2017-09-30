package br.edu.caelum.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.edu.caelum.form.UpdateProductForm;

public class UpdateProductFormValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return UpdateProductForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {}

}
