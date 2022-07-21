package DBAccess;

import Entity.Product;
import Entity.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

public class Accessors {

    private static Connection conn = null;
    //Product Statements
    private static PreparedStatement addProductStatement = null;
     private static PreparedStatement deleteProductStatement = null;
     private static PreparedStatement editProductStatement = null;
    private static PreparedStatement updateProductStatement = null;
    private static PreparedStatement selectAllProductsStatement = null;
    //User Statements
    private static PreparedStatement loginStatement = null;
    private static PreparedStatement registerUserStatement = null;

    private Accessors() {
    }

    private static void init() throws ClassNotFoundException, SQLException {
        if (conn == null) {
            Class.forName(ConnectionParameters.DRIVERCLASS);
            conn = ConnectionManager.getConnection(ConnectionParameters.URL, ConnectionParameters.USERNAME, ConnectionParameters.PASSWORD);
            addProductStatement = conn.prepareStatement("INSERT INTO product(productName, productDescription, availableQty, price, internationalShipment, photo)values(?,?,?,?,?,?)");
            deleteProductStatement = conn.prepareStatement("delete from product where productID = ? ");
            editProductStatement = conn.prepareStatement("select * from product where productID = ? ");
            updateProductStatement = conn.prepareStatement("UPDATE product SET productName = ?, productDescription = ? , availableQty = ?, price = ?, internationalShipment = ?, photo = ? where productID = ? ");
            selectAllProductsStatement = conn.prepareStatement("SELECT * FROM product");
            
            loginStatement = conn.prepareStatement("SELECT * FROM user where email= ?");
            registerUserStatement = conn.prepareStatement("INSERT INTO user(firstName, lastName, email, password)values(?,?,?,?)");
        }
    }

    public static User login(String email) throws ClassNotFoundException, SQLException {
        User user = new User(0, "testFirstName", "testLastName", "testEmail", "testPassword");
        try {
            init();
            loginStatement.setString(1, email);
            ResultSet rs = loginStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("userID");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString ("lastName");
                String em = rs.getString("email");
                String password = rs.getString("password");
                user = new User(id, firstName, lastName, em, password);
            }
        } catch (SQLException ex) {
            System.err.println(ex);

        }
        return user;
    }

    public static boolean insertUser(User user) throws ClassNotFoundException, SQLException {
        boolean res;

        try {
            init();

            registerUserStatement.setString(1, user.getFirstName());
            registerUserStatement.setString(2, user.getLastName());
            registerUserStatement.setString(3, user.getEmail());
            registerUserStatement.setString(4, user.getPassword());

            int rowCount = registerUserStatement.executeUpdate();
            res = (rowCount == 1);

        } catch (SQLException ex) {

            res = false;
        }
        return res;
    }

    public static List<Product> getAllProducts() throws ClassNotFoundException, SQLException {
        List<Product> products = new ArrayList();
        try {
            init();
            ResultSet rs = selectAllProductsStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("productID");
                String productName = rs.getString("productName");
                String productDescription = rs.getString("productDescription");
                int availableQty = rs.getInt("availableQty");
                int price = rs.getInt("price");
                boolean internationalShipment = rs.getBoolean("internationalShipment");
                String photo = rs.getString("photo");

                Product product = new Product(id, productName, productDescription, availableQty, price, internationalShipment, photo);
                products.add(product);
            }
        } catch (SQLException ex) {
            System.err.println(ex);
            products = new ArrayList();
        }
        return products;
    }

    public static boolean addProduct(Product product) throws ClassNotFoundException, SQLException {
        boolean res;

        try {
            init();

            addProductStatement.setString(1, product.getProductName());
            addProductStatement.setString(2, product.getProductDescription());
            addProductStatement.setInt(3, product.getAvailableQty());
            addProductStatement.setInt(4, product.getPrice());
            addProductStatement.setBoolean(5, product.isInternationalShipment());
            addProductStatement.setString(6, product.getPhoto());
            int rowCount = addProductStatement.executeUpdate();
            res = (rowCount == 1);

        } catch (SQLException ex) {

            res = false;
        }
        return res;
    }

    public static boolean deleteProduct(Product product) throws ClassNotFoundException, SQLException {
        boolean res;
        try {
            init();
            deleteProductStatement.setInt(1, product.getProductID());

            int rowCount = deleteProductStatement.executeUpdate();
            res = (rowCount == 1);

        } catch (SQLException ex) {
            res = false;

        }
        return res;
    }

    public static boolean deleteProductById(int id) throws ClassNotFoundException, SQLException {
        Product test = new Product(id, "testPName", "testDescription", 0, 0, false, "image.jpg");
        return deleteProduct(test);

    }

    public static Product getProduct(int id) throws ClassNotFoundException, SQLException {
        Product test = new Product(id, "testPName", "testDescription", 0, 0, false, "image.jpg");
        try {

            init();
            editProductStatement.setInt(1, id);
            ResultSet rs = editProductStatement.executeQuery();
            while (rs.next()) {
                int prodID = rs.getInt("productID");
                String productName = rs.getString("productName");
                String productDescription = rs.getString("productDescription");
                int availableQty = rs.getInt("availableQty");
                int price = rs.getInt("price");
                boolean internationalShipment = rs.getBoolean("internationalShipment");
                String photo = rs.getString("photo");

                test = new Product(prodID, productName, productDescription, availableQty, price, internationalShipment, photo);

            }
        } catch (SQLException ex) {
            System.err.println(ex);

        }
        return test;
    }

    public static boolean updateProduct(Product product) throws ClassNotFoundException, SQLException {
        boolean res;

        try {
            init();

            updateProductStatement.setString(1, product.getProductName());
            updateProductStatement.setString(2, product.getProductDescription());
            updateProductStatement.setInt(3, product.getAvailableQty());
            updateProductStatement.setInt(4, product.getPrice());
            updateProductStatement.setBoolean(5, product.isInternationalShipment());
            updateProductStatement.setString(6, product.getPhoto());
            updateProductStatement.setInt(7, product.getProductID());

            int rowCount = updateProductStatement.executeUpdate();
            res = (rowCount == 1);

        } catch (SQLException ex) {

            res = false;
        }
        return res;
    }
}
