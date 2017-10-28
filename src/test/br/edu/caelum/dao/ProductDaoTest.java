package br.edu.caelum.dao.loja;

import br.edu.caelum.config.JPAConfiguration;
import br.edu.caelum.dao.ProductDAO;
import br.edu.caelum.dao.loja.builders.ProductBuilder;
import br.edu.caelum.models.BookType;
import br.edu.caelum.models.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {ProductDAO.class, JPAConfiguration.class})
public class ProductDaoTest {

	@Autowired
	private ProductDAO productDAO;

	@Test
	@Transactional
	public void shouldSumAllPricesOfEachBookPerType(){

		List<Product> printedBooks = ProductBuilder
									.newProduct(BookType.PRINTED, BigDecimal.TEN)
									.more(4)
									.buildAll();
		printedBooks.stream().forEach(productDAO::save);

		List<Product> eBooks = ProductBuilder
								.newProduct(BookType.EBOOK, BigDecimal.TEN)
								.more(4)
								.buildAll();
		eBooks.stream().forEach(productDAO::save);

		BigDecimal value = productDAO.sumPricesPerType(BookType.PRINTED);

		assertEquals(new BigDecimal(50).setScale(2), value);
	}

}