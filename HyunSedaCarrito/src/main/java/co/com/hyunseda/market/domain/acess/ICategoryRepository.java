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
public interface ICategoryRepository {
    
    List<Product> products = new ArrayList<>();    
    boolean save(Category category);
    
    List<Category> findAll();
    
    boolean edit(int categoryId, Category category);
    boolean delete(int categoryId);
    Category findById(int categoryId);
    Category findByName(Long categoryName);
    
    
    List<Category> findAllByName(String name);
    
    List<Category> findAllByCategoryId(int categoryId);
    
}
