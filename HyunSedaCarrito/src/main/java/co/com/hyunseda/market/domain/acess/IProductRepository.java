/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package co.com.hyunseda.market.domain.acess;
import co.com.hyunseda.market.domain.Category;
import co.com.hyunseda.market.domain.Product;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Laura Sofia
 */
public interface IProductRepository {
    
    List<Category> categories = new ArrayList<>();    
    List<Product> findAllByCategoryId(int categoryId);
    
    boolean save(Product newProduct); //Lo guarda en un repositorio
    List<Product> findAll();
    List<Product> findAllByName(String productName);
    List<Product> findByCategoryId(int category);
    
    
    boolean edit(int productId, Product product);
    boolean delete(int productId);
    Product findById(int productId);
    
    
  
}
    

