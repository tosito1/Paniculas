/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package edu.daw.paniculas.Controladores;

import edu.daw.paniculas.Entidades.Datosadicionales;
import edu.daw.paniculas.Entidades.Credenciales;
import edu.daw.paniculas.Entidades.Peliculas;
import edu.daw.paniculas.Entidades.Roles;
import edu.daw.paniculas.Entidades.Usuariocategorias;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
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
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
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
@WebServlet(name = "PerfilController", urlPatterns = {"/Paniculas/html/Perfil/*","/html/Perfil/*"})
public class PerfilController extends HttpServlet {

//    EntityManagerFactory emf = Persistence.createEntityManagerFactory("PaniculasServerPU");

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
        String accion, vista = null;
        accion = request.getPathInfo();
        
        HttpSession session = request.getSession(false);
        String usuarioSesion = null, rolSesion = null;
        if (session != null && session.getAttribute("usuario") != null) {
            // La sesión existe y el usuario está autenticado
            usuarioSesion = (String) session.getAttribute("usuario");
            rolSesion = (String) session.getAttribute("rol");

        } else {
            response.sendRedirect("/Paniculas");
        }
        
//        EntityManager em = emf.createEntityManager();
        Connection conn = null;
        response.getWriter().println(accion);
        
        PreparedStatement psDatosUsuario = null;
        ResultSet rsDatosUsuario = null;
        
        switch (accion) {
            case "/DatosUsuario":
                Context ctx;
                try {
                    
                    ctx = new InitialContext();
                    DataSource dataSource = (DataSource) ctx.lookup("jdbc/javaWebAppPaniculas");
                    conn = dataSource.getConnection();
                    
                    String sqlDatosUsuario = "SELECT Credenciales.usuario, Credenciales.password, Roles.rol, " +
                             "DatosAdicionales.email, DatosAdicionales.direccion, DatosAdicionales.codigo_postal, DatosAdicionales.telefono " +
                             "FROM Credenciales " +
                             "JOIN Roles ON Credenciales.rol_id = Roles.id " +
                             "LEFT JOIN DatosAdicionales ON Credenciales.usuario = DatosAdicionales.usuario " +
                             "WHERE Credenciales.usuario = ?";
                
                    psDatosUsuario = conn.prepareStatement(sqlDatosUsuario);
                    psDatosUsuario.setString(1, usuarioSesion);
                    rsDatosUsuario = psDatosUsuario.executeQuery();

                    ArrayList<Usuariocategorias> lCategorias = new ArrayList<Usuariocategorias>();
                    
                    String sqlCategorias = "SELECT * FROM UsuarioCategorias WHERE usuario = ?";
                    PreparedStatement psCategorias = conn.prepareStatement(sqlCategorias);
                    psCategorias.setString(1, usuarioSesion);
                    ResultSet rsCategorias = psCategorias.executeQuery();
                    
                    while(rsCategorias.next()) {
                        Usuariocategorias categoria = new Usuariocategorias();
                        categoria.setCategoria(rsCategorias.getString("categoria"));
                        lCategorias.add(categoria);
                        System.out.println(categoria.getCategoria());
                    }
                    
                    if (rsDatosUsuario.next()) {
                        String usuario = rsDatosUsuario.getString("usuario");
                        String password = rsDatosUsuario.getString("password");
                        String rol = rsDatosUsuario.getString("rol");
                        String email = rsDatosUsuario.getString("email");
                        String direccion = rsDatosUsuario.getString("direccion");
                        String codigoPostal = rsDatosUsuario.getString("codigo_postal");
                        String telefono = rsDatosUsuario.getString("telefono");
//                        String categoria = rsDatosUsuario.getString("categoria");
                        request.setAttribute("email", email); 
                        request.setAttribute("usuario", usuario); 
                        request.setAttribute("direccion", direccion); 
                        request.setAttribute("codigo_postal", codigoPostal);
                        request.setAttribute("telefono", telefono);
                        request.setAttribute("categorias", lCategorias);
                        request.setAttribute("password", password);
                        request.setAttribute("rol", rolSesion);
                        
                        
                        
                        vista="/html/jsp/perfil.jsp";
                        inicializarVista(request, response, vista);
                    }
                } catch (SQLException | NamingException ex) {
                    response.getWriter().println("<p>Error "+ ex.getCause() + ": " + ex.getMessage() + ex.getLocalizedMessage());
                } finally {
                    try {
                        // Cerrar recursos
                        if (rsDatosUsuario != null) {
                            rsDatosUsuario.close();
                        }
                        if (psDatosUsuario != null) {
                            psDatosUsuario.close();
                        }
                        if (conn != null) {
                            conn.close();
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(PerfilController.class.getName()).log(Level.SEVERE, null, ex);

                    }
                }
            break;
            case "/CambiarDatos" :
                
                try {
                    ctx = new InitialContext();
                    DataSource dataSource = (DataSource) ctx.lookup("jdbc/javaWebAppPaniculas");
                    conn = dataSource.getConnection();
                    
                    String sqlDatosUsuario = "SELECT Credenciales.usuario, Credenciales.password, Roles.rol, " +
                             "DatosAdicionales.email, DatosAdicionales.direccion, DatosAdicionales.codigo_postal, DatosAdicionales.telefono, " +
                             "UsuarioCategorias.categoria " +
                             "FROM Credenciales " +
                             "JOIN Roles ON Credenciales.rol_id = Roles.id " +
                             "LEFT JOIN DatosAdicionales ON Credenciales.usuario = DatosAdicionales.usuario " +
                             "LEFT JOIN UsuarioCategorias ON Credenciales.usuario = UsuarioCategorias.usuario " +
                             "WHERE Credenciales.usuario = ?";
                
                    psDatosUsuario = conn.prepareStatement(sqlDatosUsuario);
                    psDatosUsuario.setString(1, usuarioSesion);
                    rsDatosUsuario = psDatosUsuario.executeQuery();

                    
                    if (rsDatosUsuario.next()) {
                        String usuario = rsDatosUsuario.getString("usuario");
                        String password = rsDatosUsuario.getString("password");
                        String email = rsDatosUsuario.getString("email");
                        String direccion = rsDatosUsuario.getString("direccion");
                        String codigoPostal = rsDatosUsuario.getString("codigo_postal");
                        String telefono = rsDatosUsuario.getString("telefono");
                        String categoria = rsDatosUsuario.getString("categoria");
                        request.setAttribute("email", email); 
                        request.setAttribute("usuario", usuario); 
                        request.setAttribute("direccion", direccion); 
                        request.setAttribute("codigo_postal", codigoPostal);
                        request.setAttribute("telefono", telefono);
                        request.setAttribute("categorias", categoria);
                        request.setAttribute("rol", rolSesion);
                        request.setAttribute("password", password);
                        
                        vista = "/html/jsp/cambiarDatosPerfil.jsp"; 
                        inicializarVista(request, response, vista);}
                } catch (SQLException | NamingException ex) {
                    response.getWriter().println("<p>Error "+ ex.getCause() + ": " + ex.getMessage() + ex.getLocalizedMessage());
                } finally {
                    try {
                        // Cerrar recursos
                        if (rsDatosUsuario != null) {
                            rsDatosUsuario.close();
                        }
                        if (psDatosUsuario != null) {
                            psDatosUsuario.close();
                        }
                        if (conn != null) {
                            conn.close();
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(PerfilController.class.getName()).log(Level.SEVERE, null, ex);

                    }
                }
                break;
            case "/AdministrarUsuarios":
                if(session.getAttribute("rol").equals("2")) {
                    try {
    //                    Context ctx;
                        ctx = new InitialContext();
                        DataSource dataSource = (DataSource) ctx.lookup("jdbc/javaWebAppPaniculas");
                        conn = dataSource.getConnection();

                        String sqlDatosUsuario = "SELECT Credenciales.usuario, Credenciales.password, Credenciales.rol_id, " +
                                 "DatosAdicionales.email, DatosAdicionales.direccion, DatosAdicionales.codigo_postal, DatosAdicionales.telefono "  +
                                 "FROM Credenciales " +
                                 "INNER JOIN DatosAdicionales ON Credenciales.usuario = DatosAdicionales.usuario ";

                        psDatosUsuario = conn.prepareStatement(sqlDatosUsuario);
                        rsDatosUsuario = psDatosUsuario.executeQuery();

                        List<Credenciales> lCredenciales = new ArrayList<Credenciales>();
                        List<Datosadicionales> lDatos = new ArrayList<Datosadicionales>();

                        while (rsDatosUsuario.next()) {
                            String usuario = rsDatosUsuario.getString("usuario");
                            String password = rsDatosUsuario.getString("password");
                            int rol = rsDatosUsuario.getInt("rol_id");
                            String email = rsDatosUsuario.getString("email");
                            String direccion = rsDatosUsuario.getString("direccion");
                            String codigoPostal = rsDatosUsuario.getString("codigo_postal");
                            String telefono = rsDatosUsuario.getString("telefono");
                            Roles r = new Roles(rol);
                            Credenciales c = new Credenciales(usuario,password);
                            c.setRolId(r);
                            lCredenciales.add(c);
                            lDatos.add(new Datosadicionales(0, email, direccion, codigoPostal, telefono));

                        }
                        request.setAttribute("credenciales", lCredenciales);
                        request.setAttribute("datos", lDatos);

                        vista="/html/jsp/administrarUsuarios.jsp";
                        inicializarVista(request, response, vista);
                    } catch (SQLException | NamingException ex) {
                        response.getWriter().println("<p>Error "+ ex.getCause() + ": " + ex.getMessage() + ex.getLocalizedMessage());
                    } finally {
                        try {
                            // Cerrar recursos
                            if (rsDatosUsuario != null) {
                                rsDatosUsuario.close();
                            }
                            if (psDatosUsuario != null) {
                                psDatosUsuario.close();
                            }
                            if (conn != null) {
                                conn.close();
                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(PerfilController.class.getName()).log(Level.SEVERE, null, ex);

                        }
                    }
                } else {                    
                    response.sendRedirect("/Paniculas/html/Perfil/DatosUsuario");
                }
                break;
            case "/AdministarPeliculas":
                if(session.getAttribute("rol").equals("2")) {
                    try {
    //                    Context ctx;
                        ctx = new InitialContext();
                        DataSource dataSource = (DataSource) ctx.lookup("jdbc/javaWebAppPaniculas");
                        conn = dataSource.getConnection();

                        String sqlDatosPeliculas = "SELECT * FROM Peliculas";

                        PreparedStatement psDatosPeliculas = conn.prepareStatement(sqlDatosPeliculas);
                        ResultSet rsDatosPeliculas = psDatosPeliculas.executeQuery();

                        List<Peliculas> lPeliculas = new ArrayList<>();

                        while (rsDatosPeliculas.next()) {
                            Peliculas p = new Peliculas();
                            String nombre = rsDatosPeliculas.getString("nombre");
                            String categoria = rsDatosPeliculas.getString("categoria");
                            int inio = rsDatosPeliculas.getInt("anio");
                            BigDecimal valoracion = rsDatosPeliculas.getBigDecimal("valoracion");
                            String director = rsDatosPeliculas.getString("director");
                            String sinopsis = rsDatosPeliculas.getString("sinopsis");
                            String imagen = rsDatosPeliculas.getString("imagen");

                            p.setNombre(nombre);
                            p.setCategoria(categoria);
                            p.setAnio(inio);
                            p.setValoracion(valoracion);
                            p.setDirector(director);
                            p.setSinopsis(sinopsis);
                            p.setImagen(imagen);

                            lPeliculas.add(p);
                        }
                        request.setAttribute("peliculas", lPeliculas);

                        vista="/html/jsp/administrarPeliculas.jsp";
                        inicializarVista(request, response, vista);
                    } catch (SQLException | NamingException ex) {
                        response.getWriter().println("<p>Error "+ ex.getCause() + ": " + ex.getMessage() + ex.getLocalizedMessage());
                    } finally {
                        try {
                            // Cerrar recursos
                            if (rsDatosUsuario != null) {
                                rsDatosUsuario.close();
                            }
                            if (psDatosUsuario != null) {
                                psDatosUsuario.close();
                            }
                            if (conn != null) {
                                conn.close();
                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(PerfilController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } else {
                    response.sendRedirect("/Paniculas/html/Perfil/DatosUsuario");
                }
                break;
            case "/CerrarSesion" :
                session.invalidate();
                response.sendRedirect("/Paniculas");                
                break;
            default:
                response.sendRedirect("/Paniculas");

        }
    }

    private void inicializarVista(HttpServletRequest request, HttpServletResponse response, String vista) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher(vista);
        
        rd.forward(request, response);
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

//    public void persist(Object object) {
//        EntityManager em = emf.createEntityManager();
//        try {
//            em.getTransaction().begin();
//            em.persist(object);
//            em.getTransaction().commit();
//        } catch (Exception e) {
//            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
//            em.getTransaction().rollback();
//        } finally {
//            em.close();
//        }
//    }

}
