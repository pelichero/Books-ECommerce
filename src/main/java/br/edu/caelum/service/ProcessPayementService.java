package br.edu.caelum.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.edu.caelum.models.PaymentData;

@Service
public class ProcessPayementService {

	@Autowired
	private RestTemplate rest;

	public String processPayment(BigDecimal total){
		try{
			String uri = "http://book-payment.herokuapp.com/payment";
			return rest.postForObject(uri, new PaymentData(total), String.class);
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
