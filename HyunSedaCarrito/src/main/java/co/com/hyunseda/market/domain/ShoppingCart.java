package co.com.hyunseda.market.domain;

import java.util.List;
import java.util.ArrayList;
/**
 *
 * @author USUARIO
 */
public class ShoppingCart {
    
    List<ItemShoppingCart> itemsShoppingCart ;

     public ShoppingCart() {
        List<ItemShoppingCart> itemsShoppingCart = new ArrayList<>();
    }

    public ShoppingCart(List<ItemShoppingCart> itemsShoppingCart) {
        this.itemsShoppingCart = itemsShoppingCart;
    }

    public List<ItemShoppingCart> getItemsShoppingCart() {
        return itemsShoppingCart;
    }

    public void setItemsShoppingCart(List<ItemShoppingCart> itemsShoppingCart) {
        this.itemsShoppingCart = itemsShoppingCart;
    }

    public void addToCart(Product product, int amount){
        product.setProductStock(product.getProductStock()-1);
        ItemShoppingCart newItemShoppingCart = new ItemShoppingCart(product, amount);
        itemsShoppingCart.add(newItemShoppingCart);
    }
    
}
