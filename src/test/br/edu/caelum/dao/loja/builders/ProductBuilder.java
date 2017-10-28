package br.edu.caelum.dao.loja.builders;

import br.edu.caelum.models.BookType;
import br.edu.caelum.models.Price;
import br.edu.caelum.models.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ProductBuilder {

    private List<Product> products = new ArrayList<>();

    private ProductBuilder(Product product){
        products.add(product);
    }

    public static ProductBuilder newProduct(BookType bookType, BigDecimal value){
        Product book = create("Book 1", BookType.COMBO, BigDecimal.TEN);
        return new ProductBuilder(book);
    }

    public static ProductBuilder newProduct(){
        Product book = create("Book 1", BookType.COMBO, BigDecimal.TEN);
        return new ProductBuilder(book);
    }

    private static Product create(String bookName, BookType bookType, BigDecimal value){
        Product book = new Product();
        book.setTitle(bookName);
        book.setLaunchDate(Calendar.getInstance());
        book.setNumberOfPages(150);
        book.setDescription("Great book");

        Price price = new Price();
        price.setBookType(bookType);
        price.setValue(value);
        book.getPrices().add(price);

        return book;
    }

    public ProductBuilder more(int number){
        Product base = products.get(0);
        Price price = base.getPrices().get(0);
        for(int i=0;i< number; i++){
            products.add(create("Book "+i, price.getBookType(),price.getValue()));
        }

        return this;
    }

    public Product buildOne(){
        return  products.get(0);
    }

    public List<Product> buildAll() {
        return products;
    }
}
