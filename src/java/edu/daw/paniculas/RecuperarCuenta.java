/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package edu.daw.paniculas;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 *
 * @author anton
 */
@WebServlet(name = "RecuperarCuenta", urlPatterns = {"/Paniculas/html/RecuperarCuenta", "/html/RecuperarCuenta"})
public class RecuperarCuenta extends HttpServlet {
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
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
        processRequest(request, response);
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
        String usuario = request.getParameter("usuario");
        
        Connection conn = null;
        PreparedStatement psCredenciales = null;
        ResultSet rs = null;
        
        try {
            Context ctx = new InitialContext();
            DataSource dataSource = (DataSource) ctx.lookup("jdbc/javaWebAppPaniculas");
            conn = dataSource.getConnection();
            
            String sqlCredenciales = "SELECT * FROM Datosadicionales WHERE usuario = ?";
            psCredenciales = conn.prepareStatement(sqlCredenciales);
            psCredenciales.setString(1, usuario);

            rs = psCredenciales.executeQuery();
            
            if (rs.next()) {
                String email = rs.getString("email");
                response.getWriter().write("Se enviará al correo " + email);
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // Establece el código de estado 400
                response.getWriter().write("Error: El usuario no exsiste"); 
            }
        } catch (SQLException | NamingException ex) {
            Logger.getLogger(RecuperarCuenta.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
        try {
            // Cerrar recursos
            if (rs != null) {
                rs.close();
            }
            if (psCredenciales != null) {
                psCredenciales.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
        
        processRequest(request, response);
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
