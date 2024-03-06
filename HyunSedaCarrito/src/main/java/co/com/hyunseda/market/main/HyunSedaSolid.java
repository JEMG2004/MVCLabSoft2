package co.com.hyunseda.market.main;

import co.com.hyunseda.market.domain.Product;
import co.com.hyunseda.market.domain.acess.Factory;
import co.com.hyunseda.market.domain.acess.IProductRepository;
import co.com.hyunseda.market.presentation.GUIAGREGARPRODUCT;
import co.com.hyunseda.market.presentation.GUIBUSCPRODUCTO;
import co.com.hyunseda.market.presentation.GUILOGIN;
import co.com.hyunseda.market.service.ProductService;
import java.util.Scanner;

/**
 *
 * @author Laura Sofia
 */
public class HyunSedaSolid {

    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        IProductRepository repository = Factory.getInstance().getProductRepository("default");
        //Instancia el modelo - observado
        ProductService service = new ProductService(repository);
        
        GUILOGIN guiLogin = new GUILOGIN(service);

        guiLogin.setSize(400, 300);
        guiLogin.setVisible(true);
        
        //Instanciar las vistas - observers
        GUIBUSCPRODUCTO guiBuscProducto = new GUIBUSCPRODUCTO(guiLogin, true,service);
        GUIAGREGARPRODUCT guiAgregarProduct = new GUIAGREGARPRODUCT(service);
        
        /*
        //Establecer observers del modelo
        service.addObserver(guiBuscProducto);
        service.addObserver(guiAgregarProduct);
        
        //Crea un usuario
        Product product = new Product (1, "Carro", "Rojo");
       
        //Al fijar el usuario al modelo, notifica a los observers
        service.setProduct(product);
        */
        
        //Mostrar las interfaces gráficas
        
    }
}