<%-- 
    Document   : pelicula
    Created on : 21-nov-2023, 20:27:23
    Author     : anton
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="edu.daw.paniculas.Entidades.Comentarios"%>
<%@page import="edu.daw.paniculas.Entidades.Peliculas"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Paniculas</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="${pageContext.request.contextPath}/html/css/estiloTemplate.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Sofia&effect=fire">
        <link href="${pageContext.request.contextPath}/html/css/estiloPelicula.css" rel="stylesheet" type="text/css"/>
        <script src="${pageContext.request.contextPath}/html/js/pelicula.js" type="text/javascript"></script>
    </head>
    <header>
        <div class="contenedorPortada">
            <!--<img class="imagenLogo" src="Img/pPanitas-blanco.png" alt="Logo Panitas"/>-->
            <h1 class="logo">P</h1>
            <h1 class="titulo">PANICULAS</h1>            
        </div>
        <nav class="menu">
            <ul>
                <li class="ventanaMenu"><a href="../Inicio">Inicio</a></li>
                <li class="submenu ventanaMenu">
                    <a href="../Categorias">Categorías</a>
                    <ul class="submenu-categorias">
                        <li><a href="../Categorias#accion">Acción</a></li>
                        <li><a href="../Categorias#aventura">Aventura</a></li>
                        <li><a href="../Categorias#comedia">Comedia</a></li>
                        <li><a href="../Categorias#drama">Drama</a></li>
                        <li><a href="../Categorias#ficcion">Ciencia ficción</a></li>
                        <li><a href="../Categorias#fantasia">Fantasía</a></li>
                        <li><a href="../Categorias#terror">Terror</a></li>
                        <li><a href="../Categorias#romance">Romance</a></li>
                        <li><a href="../Categorias#suspense">Suspense</a></li>
                        <li><a href="../Categorias#animacion">Animación</a></li>
                        <li><a href="../Categorias#documental">Documental</a></li>
                        <li><a href="../Categorias#guerra">Guerra</a></li>
                    </ul>
                </li>
                <li class="ventanaMenu"><a href="../Articulos">Artículos</a></li> 
                    <li class="ventanaMenu"><a href="../../Perfil/DatosUsuario">Perfil</a></li>
            </ul>
        </nav>

    </header>
    <body>
        <% Peliculas pelicula = (Peliculas) request.getAttribute("pelicula"); %>
        <div class="contenedor">
        <div class="pelicula-info">
          <div class="pelicula-imagen">
              <img src="/Paniculas/html/<% out.print(pelicula.getImagen());%>" alt="<% out.print(pelicula.getId()); %>">
          </div>
          <div class="pelicula-detalles">
              <h1 class="nombre-peli"><% out.print(pelicula.getNombre());%></h1>
              <p class="sinopsis-peli"><% out.print(pelicula.getSinopsis());%></p>
              <p class="director-peli">Director: <% out.print(pelicula.getDirector());%></p>
              <p class="anio-peli">Año: <% out.print(pelicula.getAnio());%></p>
              <p class="genero-peli">Género: <% out.print(pelicula.getCategoria());%></p>
              <p class="valoracion-peli">Valoración: <% out.print(pelicula.getValoracion());%></p>
              
          </div>
        </div>
        
        <% ArrayList<Comentarios>lComentarios = (ArrayList) request.getAttribute("comentarios"); %>
        <div class="comentarios">
            <h2>Comentarios</h2>
            
            <% 
                if(lComentarios.size()==0) {
                    out.println("<div class=\"comentario\"> <p> Aún no hay ningún comentario </p></div>");
                }
                for (Comentarios comentario : lComentarios) {%>
                <div class="comentario" data-id="<% out.print(comentario.getId()); %>">
                    <p><%out.print(comentario.getUsuario().getUsuario());%>: <% out.print(comentario.getMensaje());%></p>
                    <span class="valoracion">(Valoración: <%out.print(comentario.getValoracion());%>)</span>
                    
                    <% if((comentario.getUsuario().getUsuario()).equals(session.getAttribute("usuario")) || session.getAttribute("rol").equals("2")){ %>
                    <button onclick="eliminarComentario('<% out.print(comentario.getId()); %>')" class="eliminar">Eliminar</button>
                    <% }%>
                </div>
            <% } %>
      
            <div class="comentarioUsuario">
                <input type="text" id="comentarioInput" placeholder="Escribe tu comentario aquí">
                <input type="number" id="valoracionInput" placeholder="Valoración (0-5)">
                <button onclick="enviarComentario()">Enviar</button>
            </div>
        </div>
        </div>

       <footer>
            <p>&copy; 2023 Antonio José Muriel Gálvez</p>
       </footer>
    </body>
</html>

