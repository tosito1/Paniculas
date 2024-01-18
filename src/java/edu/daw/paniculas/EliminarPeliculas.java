/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package edu.daw.paniculas;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.annotation.Resource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.sql.DataSource;

/**
 *
 * @author anton
 */
@WebServlet(name = "EliminarPeliculas", urlPatterns = {"/Paniculas/html/Perfil/EliminarPeliculas","/html/Perfil/EliminarPeliculas"})
@MultipartConfig
public class EliminarPeliculas extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet EliminarPeliculas</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EliminarPeliculas at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        
        String msgPelicula;
        
        Connection conn = null;
        PreparedStatement psPelicula = null;

        int filasAfectadasPelicula = 0;

        try {
            String nombreactual = request.getParameter("nombreactual");
            String categoria = request.getParameter("categoria");
            
            String rutaPelicula = "C:\\Users\\anton\\OneDrive\\Escritorio\\Uni\\1 Cuatri\\DAW\\Practicav2\\PaniculasServer\\web\\html\\img\\" + categoria + "/";
            
            File archivo = new File(rutaPelicula + "\\" + nombreactual + ".jpg");
            if (archivo.exists()) {
                System.out.println("El archivo existe en la ruta especificada.");
                if (archivo.delete()) {
                    System.out.println("El archivo ha sido borrado exitosamente.");
                } else {
                    System.out.println("No se pudo borrar el archivo.");
                }
            }
            
            Context ctx = new InitialContext();
            DataSource dataSource = (DataSource) ctx.lookup("jdbc/javaWebAppPaniculas");
            conn = dataSource.getConnection();

            // Deshabilitar el autocommit para iniciar una transacción
            conn.setAutoCommit(false);

            // Preparar la consulta SQL para insertar en la tabla Credenciales

            String sqlPeliculas = "DELETE FROM Peliculas WHERE nombre = ?";
            psPelicula = conn.prepareStatement(sqlPeliculas);
            psPelicula.setString(1, nombreactual);

            // Ejecutar la consulta SQL para la tabla Credenciales
            filasAfectadasPelicula = psPelicula.executeUpdate();

            
            // Confirmar la transacción si todas las inserciones se ejecutan correctamente
            if (filasAfectadasPelicula > 0) {
                conn.commit();
                msgPelicula = "<p>OK: Pelicula eliminada correctamente</p>";
                response.sendRedirect("/Paniculas/html/Perfil/AdministarPeliculas");
            } else {
                // Si alguna de las inserciones falla, se revierte la transacción
                conn.rollback();
                msgPelicula = "<p>ERROR: Ha fallado la eliminación de la pelicula</p>";
//                response.sendRedirect("formulario.html");
            }
        } catch (NamingException | SQLException ex) {
            // Manejo de excepciones
            msgPelicula = "<p>ERROR: " + ex.getMessage() + "</p>";
            ex.printStackTrace();
            response.sendRedirect("/Paniculas/html/Perfil/AdministarPeliculas");
        } finally {
            try {
                // Cerrar recursos y restaurar el autocommit en caso de excepción
                if (psPelicula != null) {
                    psPelicula.close();
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
        response.getWriter().println(msgPelicula);
        
        processRequest(request, response);
        
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
