/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package edu.daw.paniculas;

import edu.daw.paniculas.Entidades.ValidacionesBackend;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 *
 * @author anton
 */
@WebServlet(name = "Comentarios", urlPatterns = {"/Paniculas/html/Peliculas/Pelicula/Comentarios","/html/Peliculas/Pelicula/Comentarios"})
public class Comentarios extends HttpServlet {

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
        
        HttpSession session = request.getSession(false);
        String vista = null;
        String msgComentario = null;
        
        if (session == null && session.getAttribute("usuario") == null) {
            
            response.sendRedirect("/Paniculas");
        }
        
        Connection conn = null;
        PreparedStatement psComentario = null;
        
        int filasAfectadasComentario = 0;
        
        try {        
            String comentario = request.getParameter("comentario");
            String valoracion = request.getParameter("valoracion");
            String idPelicula= request.getParameter("peliculaId");
            System.out.println(valoracion);
            if(valoracion.trim().isEmpty()) valoracion = "-1";
            if(!ValidacionesBackend.validarValoracion(valoracion)) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // Establece el código de estado 400
                response.getWriter().write("La valoracion es incorrecta.");
            } else {
               Context ctx = new InitialContext();
               DataSource dataSource = (DataSource) ctx.lookup("jdbc/javaWebAppPaniculas");
               conn = dataSource.getConnection();

               // Deshabilitar el autocommit para iniciar una transacción
               conn.setAutoCommit(false);

               // Preparar la consulta SQL para insertar en la tabla Credenciales
               String sqlComentario = "INSERT INTO Comentarios (usuario, pelicula_id, mensaje, valoracion) VALUES (?, ?, ?, ?)";
               psComentario = conn.prepareStatement(sqlComentario);
               psComentario.setString(1, (String) session.getAttribute("usuario"));
               psComentario.setString(2, idPelicula);
               psComentario.setString(3, comentario);
               psComentario.setString(4, valoracion);

               // Ejecutar la consulta SQL para la tabla Credenciales
               filasAfectadasComentario = psComentario.executeUpdate();

               if (filasAfectadasComentario > 0) {
                   conn.commit();
                   msgComentario = "<p>OK: Inserción de pelicula realizada correctamente</p>";
               } else {
                   // Si alguna de las inserciones falla, se revierte la transacción
                   conn.rollback();
                   response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // Establece el código de estado 400
                   response.getWriter().write("La valoracion es incorrecta.");
                   msgComentario = "<p>ERROR: Ha fallado la inserción de la pelicula</p>";
   //                response.sendRedirect("formulario.html");
               }   
            }
        } catch (NamingException | SQLException ex) {
            // Manejo de excepciones
            msgComentario = "<p>ERROR: " + ex.getMessage() + "</p>";
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // Establece el código de estado 400
            response.getWriter().write("La valoracion es incorrecta.");
            ex.printStackTrace();
            response.sendRedirect("/Paniculas/html/Perfil/AdministarPeliculas");
        } finally {
            try {
                // Cerrar recursos y restaurar el autocommit en caso de excepción
                if (psComentario != null) {
                    psComentario.close();
                }
                if (conn != null) {
                    conn.setAutoCommit(true); // Restaurar el autocommit
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        processRequest(request, response);
    }
    
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    HttpSession session = request.getSession(false);
    String vista = null;
    String msgComentario = null;

    if (session == null && session.getAttribute("usuario") == null) {
        response.sendRedirect("/Paniculas");
        return;
    }

    Connection conn = null;PreparedStatement psObtenerComentario = null, psEliminarComentario = null;

    try {
        String idComentario = request.getParameter("idComentario");

        Context ctx = new InitialContext();
        DataSource dataSource = (DataSource) ctx.lookup("jdbc/javaWebAppPaniculas");
        conn = dataSource.getConnection();
        
        String sqlObtenerComentario = "SELECT * FROM Comentarios WHERE id = ?";
        psObtenerComentario = conn.prepareStatement(sqlObtenerComentario);
        psObtenerComentario.setString(1, idComentario);
        
        ResultSet rs = psObtenerComentario.executeQuery();
        
        String usuario = "";
        if(rs.next()) {
            usuario = rs.getString("usuario");
        }

        if(session.getAttribute("rol").equals("2") || usuario.equals(session.getAttribute("usuario"))) {
            String sqlEliminarComentario = "DELETE FROM Comentarios WHERE id = ?";
            psEliminarComentario = conn.prepareStatement(sqlEliminarComentario);
            psEliminarComentario.setString(1, idComentario);

            int filasAfectadas = psEliminarComentario.executeUpdate();

            if (filasAfectadas > 0) {
                msgComentario = "<p>OK: Comentario eliminado correctamente</p>";
            } else {
                msgComentario = "<p>ERROR: No se pudo eliminar el comentario</p>";
            }
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // Establece el código de estado 400
            response.getWriter().write("No tienes permisos para eliminar este mensaje.");
        }
    } catch (NamingException | SQLException ex) {
        msgComentario = "<p>ERROR: " + ex.getMessage() + "</p>";
        ex.printStackTrace();
        response.sendRedirect("/Paniculas/html/Perfil/AdministarPeliculas");
    } finally {
        try {
            if (psEliminarComentario != null) {
                psEliminarComentario.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // Se puede enviar una respuesta con un mensaje, si es necesario
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    out.println(msgComentario);
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
