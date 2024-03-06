package co.com.hyunseda.market.domain.acess;

import co.com.hyunseda.market.domain.Category;
import co.com.hyunseda.market.service.CategoryService;

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
public class CategoryRepository implements ICategoryRepository{
    private Connection conn;
    
    public CategoryRepository() {
        initDatabase();
    }
   
    @Override
    public boolean save(Category newCategory){
         try {
            //Validate Category
            if (newCategory == null || newCategory.getCategoryId() < 0 || newCategory.getCategoryName().isBlank()) {
                return false;
            }
            //this.connect();

            String sql = "INSERT INTO Categories (CategoryName ) "
                    + "VALUES ( ?, ? ,?)";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            //pstmt.setInt(1, newCategory.getCategoryId());
            pstmt.setString(1, newCategory.getCategoryName());
            pstmt.executeUpdate();
            //this.disconnect();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CategoryService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
  
    private void initDatabase() {
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS Category (\n"
                + "	categoryId integer PRIMARY KEY AUTOINCREMENT,\n"
                + "	categoryName text NOT NULL"
                + ");";

        try {
            this.connect();
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            //this.disconnect();

        } catch (SQLException ex) {
            Logger.getLogger(CategoryService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void connect() {
        // SQLite connection string
        //String url = "jdbc:sqlite:./mydatabase.db";
        String url = "jdbc:sqlite::memory:";

        try {
            conn = DriverManager.getConnection(url);

        } catch (SQLException ex) {
            Logger.getLogger(CategoryService.class.getName()).log(Level.SEVERE, null, ex);
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
    public List<Category> findAll() {
        
        List<Category> categories = new ArrayList<>();
        try {

            String sql = "SELECT * FROM Categories";
            //this.connect();

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Category newCategory = new Category();
                newCategory.setCategoryId(rs.getInt("categoryId"));
                newCategory.setCategoryName(rs.getString("categoryName"));
                
                categories.add(newCategory);
            }
            //this.disconnect();

        } catch (SQLException ex) {
            Logger.getLogger(CategoryService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return categories;
    }

    @Override
    public boolean edit(int categoryId, Category category) {
       try {
            //Validate category
            if (categoryId <= 0 || category == null) {
                return false;
            }
            //this.connect();

            String sql = "UPDATE  categories "
                    + "SET categoryName=? "
                    + "WHERE categoryId = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, category.getCategoryName());
            pstmt.setLong(2, categoryId);
            pstmt.executeUpdate();
            //this.disconnect();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CategoryService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean delete(int categoryId) {
       try {
            //Validate category
            if (categoryId <= 0) {
                return false;
            }
            //this.connect();

            String sql = "DELETE FROM categories "
                    + "WHERE categoryId = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, categoryId);
            pstmt.executeUpdate();
            //this.disconnect();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CategoryService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public Category findById(int categoryId) {
          try {

            String sql = "SELECT * FROM categories  "
                    + "WHERE categoryId = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, categoryId);

            ResultSet res = pstmt.executeQuery();

            if (res.next()) {
                Category prod = new Category();
                prod.setCategoryId(res.getInt("categoryId"));
                prod.setCategoryName(res.getString("categoryName"));
                return prod;
            } else {
                return null;
            }
            //this.disconnect();

        } catch (SQLException ex) {
            Logger.getLogger(CategoryService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
      
    }

    @Override
    public Category findByName(Long categoryName) {
       throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody 
    }

    @Override
    public List<Category> findAllByName(String categoryName) {
     
         List<Category> categories = new ArrayList<>();
        try {

            String sql = "SELECT * FROM categories  "
                    + "WHERE categoryName like ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, categoryName);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Category newCategory = new Category();
                newCategory.setCategoryId(rs.getInt("categoryId"));
                newCategory.setCategoryName(rs.getString("categoryName"));

                categories.add(newCategory);
            }
            return categories;
        } catch (SQLException ex) {
            Logger.getLogger(CategoryService.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return categories;
        }
    }

    @Override
    public List<Category> findAllByCategoryId(int categoryId) {
        List<Category> categories = new ArrayList<>();
        try {

            String sql = "SELECT * FROM categories  "
                    + "WHERE categoryId = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, categoryId);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Category newCategory = new Category();
                newCategory.setCategoryId(rs.getInt("categoryId"));
                newCategory.setCategoryName(rs.getString("categoryName"));

                categories.add(newCategory);
            }
            return categories;
        } catch (SQLException ex) {
            Logger.getLogger(CategoryService.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return categories;
        }
    }
    
}
    
   
