<%-- 
    Document   : perfil
    Created on : 20-nov-2023, 17:22:09
    Author     : anton
--%>

<%@page import="edu.daw.paniculas.Entidades.Usuariocategorias"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Paniculas</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="${pageContext.request.contextPath}/html/css/estiloTemplate.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/html/css/estiloPerfil.css" rel="stylesheet" type="text/css"/>
        <script src="${pageContext.request.contextPath}/html/js/perfilDatos.js" type="text/javascript"></script>
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
    <main>
        <div id="opcionesAdmin">
            <button id="btnCerrarSesion" data-url="./CerrarSesion">Cerrar Sesion</button>
            <%if(request.getAttribute("rol").equals("2")) {%>
                <button id="btnAdminUsuarios" data-url="AdministrarUsuarios">Administrar Usuarios</button>
                <button id="btnAdminPeliculas" data-url="AdministarPeliculas">Administrar Películas</button>
        <%} else {}%>
        </div>
        <div class="contenedor-datos-usuario">
            <h2>Datos Personales</h2>
            <div class="datos-usuario">
                <div>
                    <h4>Email</h4>
                    <% out.println("<span>" + request.getAttribute("email") + "</span>");%>    
                </div>
                <div>
                    <h4>Nombre</h4>
                    <% out.println("<span>" + request.getAttribute("usuario") + "</span>");%>
                </div>
                <div>
                    <h4>Contraseña</h4>
                    <% out.println("<span><input class=\"password\" type=\"password\" value=\"" + request.getAttribute("password")+ "\"readOnly></span>");%>
                </div>
                <div>
                    <h4>Dirección</h4>
                    <% out.println("<span>" + request.getAttribute("direccion") + "</span>");%>
                </div>
                <div>
                    <h4>Código Postal</h4>
                    <% out.println("<span>" + request.getAttribute("codigo_postal") + "</span>");%>
                </div>
                <div>
                    <h4>Teléfono</h4>
                    <% out.println("<span>" + request.getAttribute("telefono") + "</span>");%>
                </div>
                <div>
                    <h4>Categorías de Interés</h4>
                    <% String categorias = "";
                    ArrayList<Usuariocategorias> lCategorias = (ArrayList)request.getAttribute("categorias");
                    for (Usuariocategorias elem : lCategorias) {
                        categorias += "- " + elem.getCategoria() + " ";
                    } %>
                    <% out.println("<span>" + categorias.substring(1) + "</span>");%>
                </div>
            </div>
        </div>
        <button id="btnCambiarDatosUsuario" data-url="./CambiarDatos">Cambiar Datos de Usuario</button>
    </main>
        <footer>
            <p>&copy; 2023 Antonio José Muriel Gálvez</p>
        </footer>
    </body>
</html>
