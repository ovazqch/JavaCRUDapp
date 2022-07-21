package Controller;

import DBAccess.Accessors;
import Entity.Product;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet(name = "AddNewProduct", urlPatterns = {"/AddNewProduct"})
@MultipartConfig
public class AddNewProduct extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");

        ServletContext ctx = this.getServletContext();

        Part p = request.getPart("photo");
        InputStream is = p.getInputStream();
        String filename = p.getSubmittedFileName();
        String t = ctx.getRealPath("/images/" + filename);
        FileOutputStream f = new FileOutputStream(t);
        f.write(is.readAllBytes());
        f.close();

        String prodName = request.getParameter("productName");
        String prodDescription = request.getParameter("productDescription");
        int setProdAvaQty = Integer.parseInt(request.getParameter("availableQty"));
        int setProdPrice = Integer.parseInt(request.getParameter("price"));
        boolean setIntlShip = Boolean.parseBoolean("internationalShipment");
        Product newProduct = new Product(888, prodName, prodDescription, setProdAvaQty, setProdPrice, setIntlShip, filename);

        boolean created = Accessors.addProduct(newProduct);
        String nextPage;
        if (created) {
            PrintWriter out = response.getWriter();
            out.println("<script>");
            out.println("alert('Item Added');");
            // out.println("window.location.replace('index.jsp');");
            out.println("location='DisplayAllProducts';");
            out.println("</script>");

        } else {

            nextPage = "/error.jsp";
            request.setAttribute("ERROR", "product couldn't be added, please try again ");
            RequestDispatcher rd = request.getRequestDispatcher(nextPage);
            rd.forward(request, response);
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AddNewProduct.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AddNewProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AddNewProduct.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AddNewProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Add product to products list";
    }// </editor-fold>

}
