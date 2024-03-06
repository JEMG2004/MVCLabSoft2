package co.com.hyunseda.market.domain;
/**
 *
 * @author USUARIO
 */
public class ItemShoppingCart {
    private Product product;
    private int amount;
    
     public ItemShoppingCart() {
        this.product = new Product();
        this.amount = 0;
    }

    public ItemShoppingCart(Product product, int amount) {
        this.product = product;
        this.amount = amount;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

}
