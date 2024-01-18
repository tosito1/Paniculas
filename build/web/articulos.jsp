<%-- 
    Document   : articulos
    Created on : 21-nov-2023, 19:37:26
    Author     : anton
--%>

<%@page import="edu.daw.paniculas.Entidades.Peliculas"%>
<%@page import="java.io.UnsupportedEncodingException"%>
<%@page import="java.net.URLEncoder"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Paniculas</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estiloTemplate.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estiloArticulos.css">
        <!--<script src="${pageContext.request.contextPath}/js/articulos.js" type="text/javascript"></script>-->
        <script src="${pageContext.request.contextPath}/js/articulosColorTabla.js" type="text/javascript"></script>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Sofia&effect=fire">
    </head>
    <body>
        <header>
            <div class="contenedorPortada">
                <!--<img class="imagenLogo" src="Img/pPanitas-blanco.png" alt="Logo Panitas"/>-->
                <h1 class="logo">P</h1>
                <h1 class="titulo">PANICULAS</h1>            
            </div>
            <nav class="menu">
                <ul>
                    <li class="ventanaMenu"><a href="./Inicio">Inicio</a></li>
                    <li class="submenu ventanaMenu">
                        <a href="./Categorias">Categorías</a>
                        <ul class="submenu-categorias">
                            <li><a href="./Categorias#accion">Acción</a></li>
                            <li><a href="./Categorias#aventura">Aventura</a></li>
                            <li><a href="./Categorias#comedia">Comedia</a></li>
                            <li><a href="./Categorias#drama">Drama</a></li>
                            <li><a href="./Categorias#ficcion">Ciencia ficción</a></li>
                            <li><a href="./Categorias#fantasia">Fantasía</a></li>
                            <li><a href="./Categorias#terror">Terror</a></li>
                            <li><a href="./Categorias#romance">Romance</a></li>
                            <li><a href="./Categorias#suspense">Suspense</a></li>
                            <li><a href="./Categorias#animacion">Animación</a></li>
                            <li><a href="./Categorias#documental">Documental</a></li>
                            <li><a href="./Categorias#guerra">Guerra</a></li>
                        </ul>
                    </li>
                    <li class="ventanaMenu"><a href="/Articulos">Artículos</a></li> 
                    <li class="ventanaMenu"><a href="../Perfil/DatosUsuario">Perfil</a></li>
                </ul>
            </nav>

        </header>
        <main>
            <table id="tablaPeliculas">
                <thead>
                  <tr>
                    <th>Imagen</th>
                    <th>Nombre</th>
                    <th>Género</th>
                    <th>Detalles</th>
                  </tr>
                </thead>
                <tbody id="cuerpoTabla">
                    <%
                        List<Peliculas> lp = (List<Peliculas>) request.getAttribute("peliculas");
                        for (Peliculas pelicula : lp) {
                            out.println("<tr>");
                            out.println("<td class=\"detallesArticulo\" ><img src=\"/Paniculas/" + pelicula.getImagen() + "\" alt=\"Imagen de la película\"></td>");
                            out.println("<td class=\"nombreArticulo\" >" + pelicula.getNombre() + "</td>");
                            out.println("<td class=\"categoriaArticulo\" >" + pelicula.getCategoria() + "</td>");
                            String nombreCodificado = URLEncoder.encode(pelicula.getNombre(), "UTF-8");
                            out.println("<td class=\"detallesArticulo\" ><a href=\"Pelicula/" + nombreCodificado + "\">Ver detalles</a></td>");
                            out.println("</tr>");
                        }
                    %>
                </tbody>
            </table>
        </main>
        <footer>
            <p>&copy; 2023 Antonio José Muriel Gálvez</p>
        </footer>
    </body>

</html>
