<%-- 
    Document   : administrarUsuarios
    Created on : 22-nov-2023, 20:36:32
    Author     : anton
--%>

<%@page import="edu.daw.paniculas.Entidades.Peliculas"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Paniculas</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="${pageContext.request.contextPath}/html/css/estiloTemplate.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/html/css/estiloAdministrarPeliculas.css" rel="stylesheet" type="text/css"/>
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
                    <li class="ventanaMenu"><a href="../Peliculas/Inicio">Inicio</a></li>
                    <li class="submenu ventanaMenu">
                        <a href="../Peliculas/Categorias">Categorías</a>
                        <ul class="submenu-categorias">
                            <li><a href="../Peliculas/Categorias#accion">Acción</a></li>
                            <li><a href="../Peliculas/Categorias#aventura">Aventura</a></li>
                            <li><a href="../Peliculas/Categorias#comedia">Comedia</a></li>
                            <li><a href="../Peliculas/Categorias#drama">Drama</a></li>
                            <li><a href="../Peliculas/Categorias#ficcion">Ciencia ficción</a></li>
                            <li><a href="../Peliculas/Categorias#fantasia">Fantasía</a></li>
                            <li><a href="../Peliculas/Categorias#terror">Terror</a></li>
                            <li><a href="../Peliculas/Categorias#romance">Romance</a></li>
                            <li><a href="../Peliculas/Categorias#suspense">Suspense</a></li>
                            <li><a href="../Peliculas/Categorias#animacion">Animación</a></li>
                            <li><a href="../Peliculas/Categorias#documental">Documental</a></li>
                            <li><a href="../Peliculas/Categorias#guerra">Guerra</a></li>
                        </ul>
                    </li>
                    <li class="ventanaMenu"><a href="../Peliculas/Articulos">Artículos</a></li> 
                    <li class="ventanaMenu"><a href="./DatosUsuario">Perfil</a></li>
                </ul>
            </nav>

        </header>
    <body>
        <button class="btnNuevaPelicula" id="btnNuevaPelicula" onclick="nuevaPelicula()">Nueva Peliculas</button>
        <button class="btnVerDatosPelicula" onclick="verTabla()">Ver Peliculas</button>
        <h1 class="tituloTabla" >Tabla de Peliculas</h1>

        <table class="tabla-peliculas">
            <thead>
                <tr class="encabezado-tabla">
                    <th>Imagen</th>
                    <th>Nombre</th>
                    <th>Categoria</th>
                    <th>Director</th>
                    <th>Año</th>
                    <th>Valoración</th>
                    <th>Sinopsis</th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<Peliculas> lPeliculas = (List<Peliculas>)request.getAttribute("peliculas");
                    for(int i=0;i<lPeliculas.size();i++) {
                        out.println("<tr id=\"fila" + i + "\">");
    //                    out.println("<td><button class=\"btn-accion\" onclick=\"mostrarFormularioDeEdicion(this)\">Editar</button></td>");
                        out.println("<td><img src=\"/Paniculas/html/" + lPeliculas.get(i).getImagen()+ "\"></td>");
                        out.println("<td>" + lPeliculas.get(i).getNombre()+ "</td>");
                        out.println("<td>" + lPeliculas.get(i).getCategoria()+ "</td>");
                        out.println("<td>" + lPeliculas.get(i).getDirector()+ "</td>");
                        out.println("<td>" + lPeliculas.get(i).getAnio()+ "</td>");
                        out.println("<td>" + lPeliculas.get(i).getValoracion()+ "</td>");
                        out.println("<td>" + lPeliculas.get(i).getSinopsis()+ "</td>");
                        out.println("<td>"
                        + "<button class=\"btnEliminarPelicula\" onclick=\"actualizarPelicula(\'" + lPeliculas.get(i).getNombre() + "\')\" >Actualizar Pelicula</button>"
                        + "<button class=\"btnEliminarPelicula\" onclick=\"eliminarPelicula(\'" + lPeliculas.get(i).getNombre() + "\')\" >Eliminar Pelicula</button>"
                        + "</td>");
                        out.println("</tr>");
                    }
                %>
                </div>
            </tbody>
        </table>
            
        <div class="contenedor-datos-peliculas">
            <form action="InsertarPeliculas" method="post" enctype="multipart/form-data" class="registro-form">
                <div class="campo">
                    <label for="nombreactual" class="form-label nombre_label">Nombre Actual</label>
                    <input type="text" name="nombreactual" class="form-input" value=""">
                    <div class="error-message"></div>
                </div>
                <div class="campo">
                    <label for="imagen" class="form-label file-label imagen_label">Seleccionar Cartel Película</label>
                    <input type="file" id="imagen" name="imagen" class="upload form-input" accept="image/*">
                    <span id="file-name" class="file-name">Ningún archivo seleccionado</span>
                    <div class="error-message"></div>
                </div>
                <div class="campo">
                    <label for="nombrenuevo" class="form-label nombre_label">Nombre</label>
                    <input type="text" name="nombrenuevo" class="form-input" required>
                    <div class="error-message"></div>
                </div>
                <div class="campo">
                    <label for="categoria" class="form-label categoria_label">Categoria</label>
                    <input type="text" id="categoria" name="categoria" class="form-input obligatorio" required>
                    <div class="error-message"></div>
                </div>

                <div class="campo">
                    <label for="director" class="form-label director-label">Director</label>
                    <input type="text" id="director" name="director" class="form-input obligatorio" required>
                    <div class="error-message"></div>
                </div>

                <div class="campo">
                    <label for="anio" class="form-label anio-label">Año</label>
                    <input type="text" id="anio" name="anio" class="form-input obligatorio" required>
                    <div class="error-message"></div>
                </div>

                <div class="campo">
                    <label for="valoracion" class="form-label">Valoración</label>
                    <input type="text" id="valoracion" name="valoracion" class="form-input" required>
                </div>    
                <div class="campo">
                    <label for="sinopsis" class="form-label sinopsis-label">Sinopsis</label>
                    <input type="text" id="sinopsis" name="sinopsis" class="form-input obligatorio" required>
                    <div class="error-message"></div>
                </div>
                <button type="submit" id="btnCambiarDatosPelicula" class="form-submit">Actualizar Pelicula</button>
            </form>
        </div>
    </body>
    <script src="${pageContext.request.contextPath}/html/js/administrarPeliculas.js" type="text/javascript"></script>

</html>
