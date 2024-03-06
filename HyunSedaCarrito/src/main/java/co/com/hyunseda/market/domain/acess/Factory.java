package co.com.hyunseda.market.domain.acess;

/**
 *
 * @author Laura Sofia
 */
/**
 * Fabrica que se encarga de instanciar ProductRepository o cualquier otro que
 * se cree en el futuro.
 */
public class Factory {
     
    private static Factory instance;

    private Factory() {
    }

    /**
     * Clase singleton
     *
     * @return
     */
    public static Factory getInstance() {

        if (instance == null) {
            instance = new Factory();
        }
        return instance;
    }

    /**
     * Método que crea una instancia concreta de la jerarquia IProductRepository
     *
     * @param type cadena que indica qué tipo de clase hija debe instanciar
     * @return una clase hija de la abstracción IProductRepository
     */
   public IProductRepository getProductRepository(String type) {

        IProductRepository result = null;

        switch (type) { //Viola el principio abierto cerrado pero por simplidad lo dejamos por ahora ahi
            case "default":
                result = new ProductRepository();
                break;
            case "mongo":
//                result = new ProductMongoRepository();
        }

        return result;

    }
    
    public ICategoryRepository getCategoryRepository(String type) {

        ICategoryRepository result = null;

        switch (type) { //Viola el principio abierto cerrado pero por simplidad lo dejamos por ahora ahi
            case "default":
                result = new CategoryRepository();
                break;
            case "mongo":
//                result = new ProductMongoRepository();
        }

        return result;

    }
}


