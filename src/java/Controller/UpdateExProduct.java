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

@WebServlet(name = "UpdateExProduct", urlPatterns = {"/UpdateExProduct"})
@MultipartConfig
public class UpdateExProduct extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        
        int id = Integer.parseInt(request.getParameter("productID"));
        String prodName = request.getParameter("productName");
        String prodDescription = request.getParameter("productDescription");
        int setProdAvaQty = Integer.parseInt(request.getParameter("availableQty"));
        int setProdPrice = Integer.parseInt(request.getParameter("price"));
        boolean setIntlShip = Boolean.parseBoolean("internationalShipment");
        String photo = Accessors.getProduct(id).getPhoto();
        Product newProduct = new Product(id, prodName, prodDescription, setProdAvaQty, setProdPrice, setIntlShip, photo);
        

        ServletContext ctx = this.getServletContext();
        Part p = request.getPart("photo");

        if (p.getSize() != 0) {
            InputStream is = p.getInputStream();
            String filename = p.getSubmittedFileName();
            String t = ctx.getRealPath("/images/" + filename);
            FileOutputStream f = new FileOutputStream(t);
            f.write(is.readAllBytes());
            f.close();
            newProduct = new Product(id, prodName, prodDescription, setProdAvaQty, setProdPrice, setIntlShip, photo);
        }

        boolean created = Accessors.updateProduct(newProduct);
        String nextPage;
        if (created) {
            PrintWriter out = response.getWriter();
            out.println("<script>");
            out.println("alert('Item updated');");
            // out.println("window.location.replace('index.jsp');");
            out.println("location='DisplayAllProducts';");
            out.println("</script>");
        } else {
            nextPage = "/error.jsp";
            request.setAttribute("error", "could not update item ");
            RequestDispatcher rd = request.getRequestDispatcher(nextPage);
            rd.forward(request, response);

        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UpdateExProduct.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UpdateExProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UpdateExProduct.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UpdateExProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
