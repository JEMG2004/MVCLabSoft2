package co.com.hyunseda.market.domain.acess;

import co.com.hyunseda.market.domain.Category;
import co.com.hyunseda.market.domain.Product;
import co.com.hyunseda.market.service.ProductService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Laura Sofia
 */
public class ProductRepository implements IProductRepository{
     private Connection conn;
     
     //Constructor que inicia la base de datos
    public ProductRepository() {
        initDatabase();
    }

    @Override
    public boolean save(Product newProduct) {

        try {
            //Validate product
            if (newProduct == null || newProduct.getProductId() < 0 || newProduct.getProductName().isBlank()) {
                return false;
            }
            //this.connect();

            String sql = "INSERT INTO Product ( ProductId, productName, productDescripcion,categoryId ) "
                    + "VALUES ( ?, ?, ?,? )";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, newProduct.getProductId());
            pstmt.setString(2, newProduct.getProductName());
            pstmt.setString(3, newProduct.getProductDescripcion());
            pstmt.setLong(3, newProduct.getCategory().getCategoryId());
            pstmt.executeUpdate();
            //this.disconnect();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public List<Product> findAll() {
        
        List<Product> products = new ArrayList<>();
        try {

            String sql = "SELECT * FROM Product";
            //this.connect();

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Product newProduct = new Product();
                newProduct.setProductId(rs.getInt("productId"));
                newProduct.setProductName(rs.getString("productName"));
                newProduct.setProductDescripcion(rs.getString("productDescripcion"));
                
                Category newCategory = new Category(rs.getInt("categoryId"), "");
                newProduct.setCategory(newCategory);
                products.add(newProduct);
            }
            //this.disconnect();

        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return products;
    }
    
    private void initDatabase() {
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS category (\n"
                + "	categoryId integer PRIMARY KEY AUTOINCREMENT,\n"
                + "	name text NOT NULL\n"
                + ");";

        try {
            this.connect();
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            sql = "CREATE TABLE IF NOT EXISTS Product (\n"
                + "	productId integer PRIMARY KEY,\n"
                + "	productName text NOT NULL,\n"
                + "	productDescripcion text NOT NULL,\n"
                 + "	categoryId integer FOREING KEY REFERENCES category (categoryId)"
                + ");";
            stmt.execute(sql);
            //this.disconnect();

        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void connect() {
        // SQLite connection string
        //String url = "jdbc:sqlite:./mydatabase.db";
        String url = "jdbc:sqlite::memory:";

        try {
            conn = DriverManager.getConnection(url);

        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void disconnect() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }


  
    @Override
    public boolean edit(int productId, Product product) {
         try {
            //Validate product
            if (productId <= 0 || product == null) {
                return false;
            }
            //this.connect();

            String sql = "UPDATE  product "
                    + "SET productName=?, productDescripcion=? , categoryId=?"
                    + "WHERE productId = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, product.getProductName());
            pstmt.setString(2, product.getProductDescripcion());
            pstmt.setInt(3, productId);
            pstmt.setInt(3, product.getCategory().getCategoryId());
            pstmt.executeUpdate();
            //this.disconnect();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean delete(int productId) {
         try {
            //Validate product
            if (productId <= 0) {
                return false;
            }
            //this.connect();

            String sql = "DELETE FROM Product "
                    + "WHERE productId = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, productId);
            pstmt.executeUpdate();
            //this.disconnect();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public Product findById(int productId) {
        try {

            String sql = "SELECT * FROM products  "
                    + "WHERE productId = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, productId);

            ResultSet res = pstmt.executeQuery();

            if (res.next()) {
                Product prod = new Product();
                prod.setProductId(res.getInt("productId"));
                prod.setProductName(res.getString("ProductName"));
                prod.setProductDescripcion(res.getString("productDescription"));
                
                Category cat = new Category(res.getInt("categoryId"), "");
                prod.setCategory(cat);
                return prod;
            } else {
                return null;
            }
            //this.disconnect();

        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    
    }

      @Override
    public List<Product> findAllByName(String productName) {
        
         List<Product> products = new ArrayList<>();
        try {

            String sql = "SELECT * FROM products  "
                    + "WHERE name like ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, productName);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Product newProduct = new Product();
                newProduct.setProductId(rs.getInt("productId"));
                newProduct.setProductName(rs.getString("productName"));
                newProduct.setProductDescripcion(rs.getString("productDescripcion"));

                Category cat = new Category(rs.getInt("categoryId"), "");
                newProduct.setCategory(cat);
                products.add(newProduct);
            }
            return products;
        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return products;
        }
    }

    @Override
    public List<Product> findByCategoryId(int category) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Product> findAllByCategoryId(int categoryId) {
        List<Product> products = new ArrayList<>();
        try {

            String sql = "SELECT * FROM products  "
                    + "WHERE categoryId = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, categoryId);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Product newProduct = new Product();
                newProduct.setProductId(rs.getInt("productId"));
                newProduct.setProductName(rs.getString("productName"));
                newProduct.setProductDescripcion(rs.getString("productDescripcion"));

                Category newCategory = new Category(rs.getInt("categoryId"), "");
                newProduct.setCategory(newCategory);

                products.add(newProduct);
            }
            return products;
        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return products;
        }
    }

    
}