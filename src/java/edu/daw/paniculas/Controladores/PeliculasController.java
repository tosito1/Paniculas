/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package edu.daw.paniculas.Controladores;

import edu.daw.paniculas.Entidades.Comentarios;
import edu.daw.paniculas.Entidades.Credenciales;
import edu.daw.paniculas.Entidades.Peliculas;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "PeliculasController", urlPatterns = {"/Paniculas/html/Peliculas/*", "/html/Peliculas/*"})
public class PeliculasController extends HttpServlet {

    
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
        HttpSession session = request.getSession(false);
        
        if (session != null && session.getAttribute("usuario") != null) {
            // La sesión existe y el usuario está autenticado
            String usuario = (String) session.getAttribute("usuario");
            String rol = (String) session.getAttribute("rol");
        } else {            
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Redireccionando...</title>");
            out.println("<script>");
            out.println("window.location.href = '/Paniculas';");
            out.println("</script>");
            out.println("</head>");
            out.println("<body>");
            out.println("</body>");
            out.println("</html>");

            out.close();
            response.sendRedirect("/Paniculas");
        }
        
        String accion;
        List<Peliculas> lPeliculas = new ArrayList<Peliculas>();
        accion = request.getPathInfo();
        String vista = null;
        accion = request.getPathInfo();
        Connection conn = null;
        response.getWriter().println(accion);
        
        ResultSet rsDatosPelicula = null;
        PreparedStatement psDatosPelicula = null;
        
        Context ctx = null;
        
        String nombrePelicula = "";
        if(accion.startsWith("/Pelicula")) {
            String[] partesURL = accion.split("/");
            accion = "/Pelicula";
            nombrePelicula = partesURL[partesURL.length - 1];
            response.getWriter().println(accion );
            response.getWriter().println(nombrePelicula);
        }
        
        switch (accion) {
            case "/Inicio":
                try {
                    ctx = new InitialContext();
                    DataSource dataSource = (DataSource) ctx.lookup("jdbc/javaWebAppPaniculas");
                    conn = dataSource.getConnection();

                    String sqlDatosPelicula = "SELECT * FROM Peliculas";
                    psDatosPelicula = conn.prepareStatement(sqlDatosPelicula);
                    rsDatosPelicula = psDatosPelicula.executeQuery();


                    while (rsDatosPelicula.next()) {
                        Peliculas pelicula = new Peliculas();
                        pelicula.setId(rsDatosPelicula.getInt("id"));
                        pelicula.setNombre(rsDatosPelicula.getString("nombre"));
                        pelicula.setCategoria(rsDatosPelicula.getString("categoria"));
                        pelicula.setAnio(rsDatosPelicula.getInt("anio"));
                        pelicula.setValoracion(rsDatosPelicula.getBigDecimal("valoracion"));
                        pelicula.setDirector(rsDatosPelicula.getString("director"));
                        pelicula.setSinopsis(rsDatosPelicula.getString("sinopsis"));
                        pelicula.setImagen(rsDatosPelicula.getString("imagen"));
                        lPeliculas.add(pelicula);
                    }
                        request.setAttribute("peliculas", lPeliculas);
                    
                    rsDatosPelicula.close();
                    psDatosPelicula.close();

                    vista = "/html/jsp/inicioPeliculas.jsp";
                    
                } catch (NamingException | SQLException ex) {
                    Logger.getLogger(PeliculasController.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    try {
                        // Cerrar recursos
                        if (rsDatosPelicula != null) {
                            rsDatosPelicula.close();
                        }
                        if (psDatosPelicula != null) {
                            psDatosPelicula.close();
                        }
                        if (conn != null) {
                            conn.close();
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                } 
                break;
            case "/Categorias":
                try {
                    ctx = new InitialContext();
                    DataSource dataSource = (DataSource) ctx.lookup("jdbc/javaWebAppPaniculas");
                    conn = dataSource.getConnection();

//                    String sqlDatosPelicula = "SELECT id, nombre, categoria, imagen FROM Peliculas";
                    String sqlDatosPelicula = "SELECT * FROM Peliculas";
                    psDatosPelicula = conn.prepareStatement(sqlDatosPelicula);
                    rsDatosPelicula = psDatosPelicula.executeQuery();


                    while (rsDatosPelicula.next()) {
                        Peliculas pelicula = new Peliculas();
                        pelicula.setId(rsDatosPelicula.getInt("id"));
                        pelicula.setNombre(rsDatosPelicula.getString("nombre"));
                        pelicula.setCategoria(rsDatosPelicula.getString("categoria"));
                        pelicula.setImagen(rsDatosPelicula.getString("imagen"));
                        lPeliculas.add(pelicula);
                    }
                        request.setAttribute("peliculas", lPeliculas);
                    
                    rsDatosPelicula.close();
                    psDatosPelicula.close();

                    vista = "/html/jsp/categorias.jsp";
                    
                } catch (NamingException | SQLException ex) {
                    Logger.getLogger(PeliculasController.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    try {
                        // Cerrar recursos
                        if (rsDatosPelicula != null) {
                            rsDatosPelicula.close();
                        }
                        if (psDatosPelicula != null) {
                            psDatosPelicula.close();
                        }
                        if (conn != null) {
                            conn.close();
                        }
                        if (ctx != null) {
                            ctx.close();
                        }
                    } catch (SQLException | NamingException ex) {
                        ex.printStackTrace();
                    }
                } 
                break;
            case "/Articulos": 
                try {
                    ctx = new InitialContext();
                    DataSource dataSource = (DataSource) ctx.lookup("jdbc/javaWebAppPaniculas");
                    conn = dataSource.getConnection();

//                    String sqlDatosPelicula = "SELECT id, nombre, categoria, imagen FROM Peliculas";
                    String sqlDatosPelicula = "SELECT * FROM Peliculas";
                    psDatosPelicula = conn.prepareStatement(sqlDatosPelicula);
                    rsDatosPelicula = psDatosPelicula.executeQuery();


                    while (rsDatosPelicula.next()) {
                        Peliculas pelicula = new Peliculas();
                        pelicula.setId(rsDatosPelicula.getInt("id"));
                        pelicula.setNombre(rsDatosPelicula.getString("nombre"));
                        pelicula.setCategoria(rsDatosPelicula.getString("categoria"));
                        pelicula.setImagen(rsDatosPelicula.getString("imagen"));
                        lPeliculas.add(pelicula);
                    }
                        request.setAttribute("peliculas", lPeliculas);
                    
                    rsDatosPelicula.close();
                    psDatosPelicula.close();

                    vista = "/html/jsp/articulos.jsp";
                    
                } catch (NamingException | SQLException ex) {
                    Logger.getLogger(PeliculasController.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    try {
                        // Cerrar recursos
                        if (rsDatosPelicula != null) {
                            rsDatosPelicula.close();
                        }
                        if (psDatosPelicula != null) {
                            psDatosPelicula.close();
                        }
                        if (conn != null) {
                            conn.close();
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                } 
                break;
            case "/Pelicula":
                PreparedStatement psComentario = null;
                ResultSet rsComentario = null;
                int filasAfectadasComentario = 0;
                
                try {
                    ctx = new InitialContext();
                    DataSource dataSource = (DataSource) ctx.lookup("jdbc/javaWebAppPaniculas");
                    conn = dataSource.getConnection();

//                    String sqlDatosPelicula = "SELECT id, nombre, categoria, imagen FROM Peliculas";
                    String sqlDatosPelicula = "SELECT * FROM Peliculas WHERE nombre = ?";
                    psDatosPelicula = conn.prepareStatement(sqlDatosPelicula);
                    psDatosPelicula.setString(1, nombrePelicula);
                    rsDatosPelicula = psDatosPelicula.executeQuery();
                    
                    if(rsDatosPelicula.next()) {
                        Peliculas pelicula = new Peliculas();
                        pelicula.setId(rsDatosPelicula.getInt("id"));
                        pelicula.setNombre(rsDatosPelicula.getString("nombre"));
                        pelicula.setCategoria(rsDatosPelicula.getString("categoria"));
                        pelicula.setAnio(rsDatosPelicula.getInt("anio"));
                        pelicula.setValoracion(rsDatosPelicula.getBigDecimal("valoracion"));
                        pelicula.setDirector(rsDatosPelicula.getString("director"));
                        pelicula.setSinopsis(rsDatosPelicula.getString("sinopsis"));
                        pelicula.setImagen(rsDatosPelicula.getString("imagen"));
                        
                        request.setAttribute("pelicula", pelicula);
                    
                        rsDatosPelicula.close();
                        psDatosPelicula.close();

                        String sqlComentarios = "SELECT * FROM Comentarios WHERE pelicula_id = ?";
                        psComentario = conn.prepareStatement(sqlComentarios);
                        psComentario.setInt(1, pelicula.getId());
                        rsComentario = psComentario.executeQuery();
                        
                        ArrayList<Comentarios> lComentarios = new ArrayList<Comentarios>();
                        while(rsComentario.next()) {
                            Comentarios comentario = new Comentarios();
                            Credenciales credencial = new Credenciales();
                            credencial.setUsuario(rsComentario.getString("usuario"));
                            comentario.setId(rsComentario.getInt(("id")));
                            comentario.setMensaje(rsComentario.getString("mensaje"));
                            comentario.setValoracion(rsComentario.getInt("valoracion"));
                            comentario.setUsuario(credencial);
                            lComentarios.add(comentario);
                        }
                        request.setAttribute("comentarios",lComentarios);
                        psComentario.close();
                        rsComentario.close();

                    }

                    vista = "/html/jsp/pelicula.jsp";
                    
                } catch (NamingException | SQLException ex) {
                    Logger.getLogger(PeliculasController.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    try {
                        // Cerrar recursos
                        if (rsDatosPelicula != null) {
                            rsDatosPelicula.close();
                        }
                        if (psDatosPelicula != null) {
                            psDatosPelicula.close();
                        }
                        if (psComentario != null) {
                            psComentario.close();
                        }
                        if (rsComentario != null) {
                            rsComentario.close();
                        }
                        if (conn != null) {
                            conn.close();
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                } 
                break;
            default:
                System.out.println("NO deberia salir esto");
                response.getWriter().println("Que url es este?");
        }
        RequestDispatcher rd = request.getRequestDispatcher(vista);
        rd.forward(request, response);
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
