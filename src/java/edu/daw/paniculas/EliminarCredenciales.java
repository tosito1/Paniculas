/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package edu.daw.paniculas;

import edu.daw.paniculas.Entidades.Credenciales;
import edu.daw.paniculas.Entidades.Datosadicionales;
import edu.daw.paniculas.Entidades.Usuariocategorias;
import java.io.IOException;
import java.io.PrintWriter;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
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

/**
 *
 * @author anton
 */
@WebServlet(name = "EliminarCredenciales", urlPatterns = {"/Paniculas/html/Perfil/EliminarCredenciales","/html/Perfil/EliminarCredenciales"})
public class EliminarCredenciales extends HttpServlet {

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
        String msgCredenciales;
        String msgDatosAdicionales;
        String msgCategorias;
        System.out.println("Intentando eliminar");
        Connection conn = null;
        PreparedStatement psCredenciales = null;
        PreparedStatement psDatosAdicionales = null;
        PreparedStatement psCategorias = null;

        int filasAfectadasCredenciales = 0;
        int filasAfectadasDatosAdicionales = 0;
        int filasAfectadasCategorias = 0;

        try {
            String nombreactual = request.getParameter("usuario");
            
            System.out.println(nombreactual);
            System.out.println("aqui ha llegado");
            Context ctx = new InitialContext();
            DataSource dataSource = (DataSource) ctx.lookup("jdbc/javaWebAppPaniculas");
            conn = dataSource.getConnection();

            // Deshabilitar el autocommit para iniciar una transacción
            conn.setAutoCommit(false);

            // Preparar la consulta SQL para insertar en la tabla Credenciales

            String sqlDatosAdicionales = "DELETE FROM DatosAdicionales WHERE usuario = ?";
            psDatosAdicionales = conn.prepareStatement(sqlDatosAdicionales);
            psDatosAdicionales.setString(1, nombreactual);

            // Ejecutar la consulta SQL para la tabla DatosAdicionales
            filasAfectadasDatosAdicionales = psDatosAdicionales.executeUpdate();

            // Preparar la consulta SQL para insertar en la tabla UsuarioCategorias
            String sqlCategorias = "DELETE FROM UsuarioCategorias WHERE usuario = ?";
            psCategorias = conn.prepareStatement(sqlCategorias);
            psCategorias.setString(1, nombreactual);

            filasAfectadasCategorias = psCategorias.executeUpdate();

            String sqlCredenciales = "DELETE FROM Credenciales WHERE usuario = ?";
            psCredenciales = conn.prepareStatement(sqlCredenciales);
            psCredenciales.setString(1, nombreactual);

            // Ejecutar la consulta SQL para la tabla Credenciales
            filasAfectadasCredenciales = psCredenciales.executeUpdate();
            // Confirmar la transacción si todas las inserciones se ejecutan correctamente
            if (filasAfectadasCredenciales > 0 && filasAfectadasDatosAdicionales > 0 && filasAfectadasCategorias > 0) {
                conn.commit();
                msgCredenciales = "<p>OK: Inserción de credenciales realizada correctamente</p>";
                msgDatosAdicionales = "<p>OK: Inserción de datos adicionales realizada correctamente</p>";
                msgCategorias = "<p>OK: Inserción de categorías realizada correctamente</p>";
                response.sendRedirect("/Paniculas/html/Perfil/AdministrarUsuarios");
            } else {
                // Si alguna de las inserciones falla, se revierte la transacción
                conn.rollback();
                msgCredenciales = "<p>ERROR: Ha fallado la inserción de credenciales</p>";
                msgDatosAdicionales = "<p>ERROR: Ha fallado la inserción de datos adicionales</p>";
                msgCategorias = "<p>ERROR: Ha fallado la inserción de categorías</p>";
//                response.sendRedirect("/Paniculas/html/Perfil/AdministrarUsuarios");
            }
        } catch (NamingException | SQLException ex) {
            // Manejo de excepciones
            msgCredenciales = "<p>ERROR: " + ex.getMessage() + "</p>";
            msgDatosAdicionales = "<p>ERROR: " + ex.getMessage() + "</p>";
            msgCategorias = "<p>ERROR: " + ex.getMessage() + "</p>";
//            response.sendRedirect("/Paniculas/html/Perfil/AdministrarUsuarios");
        } finally {
            try {
                // Cerrar recursos y restaurar el autocommit en caso de excepción
                if (psCredenciales != null) {
                    psCredenciales.close();
                }
                if (psDatosAdicionales != null) {
                    psDatosAdicionales.close();
                }
                if (psCategorias != null) {
                    psCategorias.close();
                }
                if (conn != null) {
                    conn.setAutoCommit(true); // Restaurar el autocommit
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        // Enviar mensajes de respuesta al cliente
        response.getWriter().println(msgCredenciales);
        response.getWriter().println(msgDatosAdicionales);
        response.getWriter().println(msgCategorias);
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
