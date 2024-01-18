/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package edu.daw.paniculas;

import edu.daw.paniculas.Entidades.PasswordEncryption;
import edu.daw.paniculas.Entidades.ValidacionesBackend;
import java.io.IOException;
import java.io.PrintWriter;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.annotation.Resource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author anton
 */
@WebServlet(name = "VerificarCredenciales", urlPatterns = {"/html/VerificarCredenciales","/Paniculas/html/VerificarCredenciales"})
public class VerificarCredenciales extends HttpServlet {

    @Resource(name = "javaWebAppPaniculas")
    private DataSource javaWebAppPaniculas;

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
    Connection conn = null;
    PreparedStatement psCredenciales = null;
    ResultSet rs = null;

    try {
        String password = request.getParameter("password");
        String nombre = request.getParameter("username");        
        
        if(!ValidacionesBackend.validarClave(password)) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // Establece el código de estado 400
            response.getWriter().write("La clave debe tener al menos 6 caracteres e incluir un número.");
        } 
        if(!ValidacionesBackend.validarNombre(nombre)) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // Establece el código de estado 400
            response.getWriter().write("El nombre solo debe contener letras y números.");
        }
        
        Context ctx = new InitialContext();
        DataSource dataSource = (DataSource) ctx.lookup("jdbc/javaWebAppPaniculas");
        conn = dataSource.getConnection();

        // Consulta SQL para buscar el usuario y contraseña en la base de datos
        String sqlCredenciales = "SELECT * FROM Credenciales WHERE usuario = ?";
        psCredenciales = conn.prepareStatement(sqlCredenciales);
        psCredenciales.setString(1, nombre);

        // Ejecutar la consulta SELECT
        rs = psCredenciales.executeQuery();
//
//        // Verificar si se encontró el usuario y contraseña en la base de datos
        if (rs.next()) {
            rs.getString("password");
            if(PasswordEncryption.verifyPassword(password,rs.getString("password"))) {
                HttpSession session = request.getSession();
                session.setAttribute("usuario", rs.getString("usuario"));
                session.setAttribute("rol", rs.getString("rol_id"));
                session.setAttribute("password", password);

                response.getWriter().write("Las credenciales son correctas");
            } else {
//                response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // Establece el código de estado 400
                response.getWriter().write("Error: El la contraseña es incorrecta");
            }
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // Establece el código de estado 400
            response.getWriter().write("Error: El usuario no exsiste");
        }

    } catch (NamingException | SQLException ex) {
        // Manejo de excepciones
        ex.printStackTrace();
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
