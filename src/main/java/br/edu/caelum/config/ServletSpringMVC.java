package br.edu.caelum.config;


import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class ServletSpringMVC extends AbstractAnnotationConfigDispatcherServletInitializer{

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[]{AppWebConfiguration.class, JPAConfiguration.class};
	}

	@Override
	protected void customizeRegistration(Dynamic registration){
		registration.setMultipartConfig(new MultipartConfigElement(""));
	}

	@Override
	protected String[] getServletMappings() {
		return new String[]{"/"};
	}

}
