package br.edu.caelum.service;

import br.edu.caelum.models.PaymentData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@Service
public class ProcessPayementService {

	@Autowired
	private RestTemplate rest;

	public String processPayment(BigDecimal total) throws Exception{
		try{
			String uri = "http://book-payment.herokuapp.com/payment";
			return rest.postForObject(uri, new PaymentData(total), String.class);
		}catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}
}
