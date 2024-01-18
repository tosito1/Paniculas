/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package edu.daw.paniculas;

import edu.daw.paniculas.Entidades.Credenciales;
import edu.daw.paniculas.Entidades.Datosadicionales;
import edu.daw.paniculas.Entidades.PasswordEncryption;
import edu.daw.paniculas.Entidades.Usuariocategorias;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
@WebServlet(name = "CredencialesDAO", urlPatterns = {"/Paniculas/html/Perfil/CredencialesDAO","/html/Perfil/CredencialesDAO"})
public class CredencialesDAO extends HttpServlet {

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

        Credenciales credenciales;
        Datosadicionales datosAdicionales;
        Usuariocategorias[] categorias;

        Connection conn = null;
        PreparedStatement psCredenciales = null;
        PreparedStatement psDatosAdicionales = null;
        PreparedStatement psCategorias = null;

        int filasAfectadasCredenciales = 0;
        int filasAfectadasDatosAdicionales = 0;
        int filasAfectadasCategorias = 0;
        
        String accion = request.getParameter("accion");
        
        switch (accion) {
            case "actualizarPerfil" :
                int filasAfectadasCredencialesNuevas = 0;
                int filasAfectadasCredencialesActuales = 0;
                try {
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String nombreActual = request.getParameter("nombreactual");
            String nombreNuevo = request.getParameter("nombrenuevo");
            String rol = request.getParameter("rol");
            String direccion = request.getParameter("direccion");
            String codigoPostal = request.getParameter("codigo_postal");
            String telefono = request.getParameter("telefono");
//            String[] categoriasSeleccionadas = request.getParameterValues("categorias[]");
            System.out.println("email: " + email);
            System.out.println("usuario actual: " + nombreActual);
            System.out.println("contraseña: " + password);
            String passwordEncryptado = PasswordEncryption.encryptPassword(password);
            
            // Crear un objeto Usuario con los datos del formulario
            credenciales = new Credenciales(nombreNuevo, passwordEncryptado);
            
//            categorias = new Usuariocategorias[categoriasSeleccionadas.length];
//            for (int i=0; i<categoriasSeleccionadas.length;i++) {
//                categorias[i] = new Usuariocategorias(0,categoriasSeleccionadas[i]);
//                categorias[i].setUsuario(credenciales);
//            }
            
            Context ctx = new InitialContext();
            DataSource dataSource = (DataSource) ctx.lookup("jdbc/javaWebAppPaniculas");
            conn = dataSource.getConnection();

            // Deshabilitar el autocommit para iniciar una transacción
            conn.setAutoCommit(false);
            
            if(!nombreActual.equals(nombreNuevo)) {
                // Preparar la consulta SQL para insertar en la tabla Credenciales
                System.out.println("El nombre del usuario si ha cmabiado");
                String sqlCredenciales = "INSERT INTO Credenciales (usuario, password, rol_id) VALUES (?, ?, ?)";
                psCredenciales = conn.prepareStatement(sqlCredenciales);
                psCredenciales.setString(1, nombreNuevo);
                psCredenciales.setString(2, passwordEncryptado);
                psCredenciales.setString(3, rol);

                // Ejecutar la consulta SQL para la tabla Credenciales
                filasAfectadasCredencialesNuevas = psCredenciales.executeUpdate();
                
            } else {
                System.out.println("El nombre del usuario no ha cmabiado");
                String sqlCredenciales = "UPDATE Credenciales SET password = ?, rol_id = ? WHERE usuario = ?";
                psCredenciales = conn.prepareStatement(sqlCredenciales);
                psCredenciales.setString(1, credenciales.getPassword());
                psCredenciales.setString(2, rol);
                psCredenciales.setString(3, nombreActual);
                filasAfectadasCredencialesNuevas = psCredenciales.executeUpdate();
            }

            String sqlDatosAdicionales = "UPDATE DatosAdicionales SET usuario = ?, email = ?, direccion = ?, codigo_postal = ?, telefono = ? WHERE usuario = ?";
            psDatosAdicionales = conn.prepareStatement(sqlDatosAdicionales);
            psDatosAdicionales.setString(1, nombreNuevo);
            psDatosAdicionales.setString(2, email);
            psDatosAdicionales.setString(3, direccion);
            psDatosAdicionales.setString(4, codigoPostal);
            psDatosAdicionales.setString(5, telefono);
            psDatosAdicionales.setString(6, nombreActual);

            // Ejecutar la consulta SQL para la tabla DatosAdicionales
            filasAfectadasDatosAdicionales = psDatosAdicionales.executeUpdate();

//             Preparar la consulta SQL para insertar en la tabla UsuarioCategorias
            String sqlCategorias = "UPDATE UsuarioCategorias SET usuario = ? WHERE usuario = ?";
            psCategorias = conn.prepareStatement(sqlCategorias);
            psCategorias.setString(1, nombreNuevo);
//            psCategorias.setString(2, "Accion");
            psCategorias.setString(2, nombreActual);
            filasAfectadasCategorias = psCategorias.executeUpdate();
            
            if(!nombreActual.equals(nombreNuevo)) {
                // Preparar la consulta SQL para insertar en la tabla Credenciales
                String sqlCredenciales = "DELETE FROM Credenciales WHERE usuario = ?";
                psCredenciales = conn.prepareStatement(sqlCredenciales);
                psCredenciales.setString(1, nombreActual);

                // Ejecutar la consulta SQL para la tabla Credenciales
                filasAfectadasCredencialesActuales = psCredenciales.executeUpdate();
                System.out.println("Borra al usuario anterior");
                
            }
            
            
            // Confirmar la transacción si todas las inserciones se ejecutan correctamente
            if (filasAfectadasCredencialesNuevas > 0 || filasAfectadasCredencialesActuales > 0 || filasAfectadasDatosAdicionales > 0 || filasAfectadasCategorias > 0) {
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
//                response.sendRedirect("/Paniculas/html/Perfil/datosUsuario");
            }
        } catch (NamingException | SQLException ex) {
            // Manejo de excepciones
            msgCredenciales = "<p>ERROR: " + ex.getMessage() + "</p>";
            msgDatosAdicionales = "<p>ERROR: " + ex.getMessage() + "</p>";
            msgCategorias = "<p>ERROR: " + ex.getMessage() + "</p>";
            ex.printStackTrace();
//            response.sendRedirect("/Paniculas/html/Perfil/datosUsuario");
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
            break;
                
            default: System.out.println("No deberia estar aqui");
        }

        try {
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String nombre = request.getParameter("nombre");
            // Otros campos del formulario
            String direccion = request.getParameter("direccion");
            String codigoPostal = request.getParameter("codigo_postal");
            String telefono = request.getParameter("telefono");
            String[] categoriasSeleccionadas = request.getParameterValues("categorias[]");

            String passwordEncryptado = PasswordEncryption.encryptPassword(password);
            
            // Crear un objeto Usuario con los datos del formulario
            credenciales = new Credenciales(nombre, passwordEncryptado);
            
            datosAdicionales = new Datosadicionales(0, email, direccion, codigoPostal, telefono);
            
            categorias = new Usuariocategorias[categoriasSeleccionadas.length];
            for (int i=0; i<categoriasSeleccionadas.length;i++) {
                categorias[i] = new Usuariocategorias(0,categoriasSeleccionadas[i]);
                categorias[i].setUsuario(credenciales);
            }
            
            Context ctx = new InitialContext();
            DataSource dataSource = (DataSource) ctx.lookup("jdbc/javaWebAppPaniculas");
            conn = dataSource.getConnection();

            // Deshabilitar el autocommit para iniciar una transacción
            conn.setAutoCommit(false);

            // Preparar la consulta SQL para insertar en la tabla Credenciales
            String sqlCredenciales = "INSERT INTO Credenciales (usuario, password, rol_id) VALUES (?, ?, ?)";
            psCredenciales = conn.prepareStatement(sqlCredenciales);
            psCredenciales.setString(1, credenciales.getUsuario());
            psCredenciales.setString(2, credenciales.getPassword());
            psCredenciales.setString(3, "1");

            // Ejecutar la consulta SQL para la tabla Credenciales
            filasAfectadasCredenciales = psCredenciales.executeUpdate();

            String sqlDatosAdicionales = "INSERT INTO DatosAdicionales (usuario, email, direccion, codigo_postal, telefono) VALUES (?, ?, ?, ?, ?)";
            psDatosAdicionales = conn.prepareStatement(sqlDatosAdicionales);
            psDatosAdicionales.setString(1, credenciales.getUsuario());
            psDatosAdicionales.setString(2, datosAdicionales.getEmail());
            psDatosAdicionales.setString(3, datosAdicionales.getDireccion());
            psDatosAdicionales.setString(4, datosAdicionales.getCodigoPostal());
            psDatosAdicionales.setString(5, datosAdicionales.getTelefono());

            // Ejecutar la consulta SQL para la tabla DatosAdicionales
            filasAfectadasDatosAdicionales = psDatosAdicionales.executeUpdate();

            // Preparar la consulta SQL para insertar en la tabla UsuarioCategorias
            String sqlCategorias = "INSERT INTO UsuarioCategorias (usuario, categoria) VALUES (?, ?)";
            psCategorias = conn.prepareStatement(sqlCategorias);

            // Insertar cada categoría seleccionada para el usuario
            for (String categoria : categoriasSeleccionadas) {
                psCategorias.setString(1, credenciales.getUsuario()); // Reemplaza con el método adecuado para obtener el ID del usuario
                psCategorias.setString(2, categoria);

                // Agregar la consulta al lote (batch)
                psCategorias.addBatch();
            }

            // Ejecutar el lote de inserciones para categorías
            int[] resultadosCategorias = psCategorias.executeBatch();
            for (int resultado : resultadosCategorias) {
                filasAfectadasCategorias += resultado;
            }

            // Confirmar la transacción si todas las inserciones se ejecutan correctamente
            if (filasAfectadasCredenciales > 0 && filasAfectadasDatosAdicionales > 0 && filasAfectadasCategorias == categoriasSeleccionadas.length) {
                conn.commit();
                msgCredenciales = "<p>OK: Inserción de credenciales realizada correctamente</p>";
                msgDatosAdicionales = "<p>OK: Inserción de datos adicionales realizada correctamente</p>";
                msgCategorias = "<p>OK: Inserción de categorías realizada correctamente</p>";
                response.sendRedirect("/Paniculas/html/Peliculas/Inicio");
            } else {
                // Si alguna de las inserciones falla, se revierte la transacción
                conn.rollback();
                msgCredenciales = "<p>ERROR: Ha fallado la inserción de credenciales</p>";
                msgDatosAdicionales = "<p>ERROR: Ha fallado la inserción de datos adicionales</p>";
                msgCategorias = "<p>ERROR: Ha fallado la inserción de categorías</p>";
                response.sendRedirect("formulario.html");
            }
        } catch (NamingException | SQLException ex) {
            // Manejo de excepciones
            msgCredenciales = "<p>ERROR: " + ex.getMessage() + "</p>";
            msgDatosAdicionales = "<p>ERROR: " + ex.getMessage() + "</p>";
            msgCategorias = "<p>ERROR: " + ex.getMessage() + "</p>";
            ex.printStackTrace();
            response.sendRedirect("formulario.html");
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
        processRequest(request, response);
    }
    
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
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
