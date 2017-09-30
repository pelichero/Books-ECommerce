package br.edu.caelum.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.edu.caelum.models.Product;

@Repository
public class ProductDAO {
	
	private static final String LIST_PRODUCTS = "SELECT DISTINCT(p) FROM Product p JOIN FETCH p.prices";
	
	@PersistenceContext
	private EntityManager manager;
	
	public void save(Product product){
		manager.persist(product);
	}
	
	public List<Product> list(){
		return manager.createQuery(LIST_PRODUCTS, Product.class).getResultList();
	}
}
