<%-- 
    Document   : pelicula
    Created on : 21-nov-2023, 20:27:23
    Author     : anton
--%>

<%@page import="edu.daw.paniculas.Entidades.Peliculas"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Paniculas</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="${pageContext.request.contextPath}/css/estiloTemplate.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Sofia&effect=fire">
        <link href="${pageContext.request.contextPath}/css/estiloPelicula.css" rel="stylesheet" type="text/css"/>
        <!--<script src="${pageContext.request.contextPath}/js/pelicula.js" type="text/javascript"></script>-->
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
              <img src="/Paniculas/<% out.print(pelicula.getImagen());%>" alt="Imagen de la película">
          </div>
          <div class="pelicula-detalles">
              <h1 class="nombre-peli"><% out.println(pelicula.getNombre());%></h1>
              <p class="sinopsis-peli"><% out.println(pelicula.getSinopsis());%></p>
              <p class="director-peli">Director: <% out.println(pelicula.getDirector());%></p>
              <p class="anio-peli">Año: <% out.println(pelicula.getAnio());%></p>
              <p class="genero-peli">Género: <% out.println(pelicula.getCategoria());%></p>
              <p class="valoracion-peli">Valoración: <% out.println(pelicula.getValoracion());%></p>
              
          </div>
        </div>
        
        <div class="comentarios">
            <h2>Comentarios</h2>
            <div class="comentario">
              <p>Usuario 1: ¡Excelente película!</p>
            </div>
            <div class="comentario">
              <p>Usuario 2: Me encantó la actuación de los protagonistas.</p>
            </div>
          </div>
      
            <div class="comentarioUsuario">
                <form>
                    <input type="text" name="comentario" placeholder="Escribe tu comentario aquí">
                    <button type="submit">Enviar</button>
                </form>
            </div>
        </div>

       <footer>
            <p>&copy; 2023 Antonio José Muriel Gálvez</p>
       </footer>
    </body>
</html>

