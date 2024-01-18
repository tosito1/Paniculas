<%-- 
    Document   : categorias
    Created on : 21-nov-2023, 12:41:39
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
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estiloTemplate.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estiloCategorias.css">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Sofia&effect=fire">
        <script src="/Paniculas/js/categorias.js" type="text/javascript"></script>
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
                    <li class="ventanaMenu"><a href="./Articulos">Artículos</a></li> 
                    <li class="ventanaMenu"><a href="../Perfil/DatosUsuario">Perfil</a></li>
                </ul>
            </nav>

        </header>
        <main>
            
            <% 
                List<Peliculas> lp = (List<Peliculas>) request.getAttribute("peliculas");
            %>
            
            <div class="contenedor">
                <div class="categoria div-genero-Acción" id="accion">
                    <h2 class="nombreCategoria" data-descripcion="Descripción de Acción">Acción</h2>
                    <div class="peliculas-container">
                        <button class="flecha flechaIzq" onclick="moverPeliculas(this.parentNode.parentNode, 'izquierda')"><</button>
                        <button class="flecha flechaDer" onclick="moverPeliculas(this.parentNode.parentNode, 'derecha')">></button>
                        <div class="contenedorPeliculas">
                            <% for (Peliculas pelicula: lp) {
                                if(pelicula.getCategoria().equals("Acción")) {
                                    out.println("<div class=\"div-pelicula\" >");
                                        out.println("<div class=\"div-cartel\" >");
                                            out.println("<img class=\"cartel\" src=\"/Paniculas/" + pelicula.getImagen() + "\" alt=\"Imagen de la película\" >");
                                        out.println("</div>");
                                        out.println("<div>");
                                            out.println("<p class=\"nombrePelicula\" >" + pelicula.getNombre() + "</p>");
                                        out.println("</div>");
                                    out.println("</div>");
                                }
                               }
                            %>
                        </div>
                    </div>
                </div>
              </div>

              <div class="contenedor">
                <div class="categoria div-genero-Aventura" id="aventura">
                    <h2 class="nombreCategoria" data-descripcion="Descripción de Aventura">Aventura</h2>
                    <div class="peliculas-container">
                        <button class="flecha flechaIzq"><</button>
                        <button class="flecha flechaDer">></button>
                        <div class="contenedorPeliculas">
                            <% for (Peliculas pelicula: lp) {
                                if(pelicula.getCategoria().equals("Aventura")) {
                                out.println("<div class=\"div-pelicula\" >");
                                    out.println("<div class=\"div-cartel\" >");
                                        out.println("<img class=\"cartel\" src=\"/Paniculas/" + pelicula.getImagen() + "\" alt=\"Imagen de la película\" >");
                                    out.println("</div>");
                                    out.println("<div>");
                                        out.println("<p class=\"nombrePelicula\" >" + pelicula.getNombre() + "</p>");
                                    out.println("</div>");
                                out.println("</div>");
                                }}
                            %>
                        </div>
                    </div>
                </div>
              </div>

              <div class="contenedor">
                <div class="categoria div-genero-Comedia" id="comedia">
                    <h2 class="nombreCategoria" data-descripcion="Descripción de Comedia">Comedia</h2>
                    <div class="peliculas-container">
                        <button class="flecha flechaIzq"><</button>
                        <button class="flecha flechaDer">></button>
                        <div class="contenedorPeliculas">
                            <% for (Peliculas pelicula: lp) {
                                if(pelicula.getCategoria().equals("Comedia")) {
                                out.println("<div class=\"div-pelicula\" >");
                                    out.println("<div class=\"div-cartel\" >");
                                        out.println("<img class=\"cartel\" src=\"/Paniculas/" + pelicula.getImagen() + "\" alt=\"Imagen de la película\" >");
                                    out.println("</div>");
                                    out.println("<div>");
                                        out.println("<p class=\"nombrePelicula\" >" + pelicula.getNombre() + "</p>");
                                    out.println("</div>");
                                out.println("</div>");
                                }}
                            %>
                        </div>
                    </div>
                </div>
              </div>

              <div class="contenedor">
                <div class="categoria div-genero-Drama" id="drama">
                    <h2 class="nombreCategoria" data-descripcion="Descripción de Drama">Drama</h2>
                    <div class="peliculas-container">
                        <button class="flecha flechaIzq"><</button>
                        <button class="flecha flechaDer">></button>
                        <div class="contenedorPeliculas">
                            <% for (Peliculas pelicula: lp) {
                                if(pelicula.getCategoria().equals("Drama")) {
                                out.println("<div class=\"div-pelicula\" >");
                                    out.println("<div class=\"div-cartel\" >");
                                        out.println("<img class=\"cartel\" src=\"/Paniculas/" + pelicula.getImagen() + "\" alt=\"Imagen de la película\" >");
                                    out.println("</div>");
                                    out.println("<div>");
                                        out.println("<p class=\"nombrePelicula\" >" + pelicula.getNombre() + "</p>");
                                    out.println("</div>");
                                out.println("</div>");
                                }}
                            %>
                        </div>
                    </div>
                </div>
              </div>

              <div class="contenedor">
                <div class="categoria div-genero-Ficción" id="ficcion">
                    <h2 class="nombreCategoria" data-descripcion="Descripción de Ciencia ficción">Ciencia ficción</h2>
                    <div class="peliculas-container">
                        <button class="flecha flechaIzq"><</button>
                        <button class="flecha flechaDer">></button>
                        <div class="contenedorPeliculas">
                            <% for (Peliculas pelicula: lp) {
                                if(pelicula.getCategoria().equals("Ficción")) {
                                out.println("<div class=\"div-pelicula\" >");
                                    out.println("<div class=\"div-cartel\" >");
                                        out.println("<img class=\"cartel\" src=\"/Paniculas/" + pelicula.getImagen() + "\" alt=\"Imagen de la película\" >");
                                    out.println("</div>");
                                    out.println("<div>");
                                        out.println("<p class=\"nombrePelicula\" >" + pelicula.getNombre() + "</p>");
                                    out.println("</div>");
                                out.println("</div>");
                                }}
                            %>
                        </div>
                    </div>
                </div>
              </div>

              <div class="contenedor">
                <div class="categoria div-genero-Fantasía" id="fantasia">
                    <h2 class="nombreCategoria" data-descripcion="Descripción de Fantasía">Fantasía</h2>
                    <div class="peliculas-container">
                        <button class="flecha flechaIzq"><</button>
                        <button class="flecha flechaDer">></button>
                        <div class="contenedorPeliculas">
                            <% for (Peliculas pelicula: lp) {
                                if(pelicula.getCategoria().equals("Fantasía")) {
                                out.println("<div class=\"div-pelicula\" >");
                                    out.println("<div class=\"div-cartel\" >");
                                        out.println("<img class=\"cartel\" src=\"/Paniculas/" + pelicula.getImagen() + "\" alt=\"Imagen de la película\" >");
                                    out.println("</div>");
                                    out.println("<div>");
                                        out.println("<p class=\"nombrePelicula\" >" + pelicula.getNombre() + "</p>");
                                    out.println("</div>");
                                out.println("</div>");
                                }}
                            %>
                        </div>
                    </div>
                </div>
              </div>

              <div class="contenedor">  
                <div class="categoria div-genero-Terror" id="terror">
                    <h2 class="nombreCategoria" data-descripcion="Descripción de Terror">Terror</h2>
                    <div class="peliculas-container">
                        <button class="flecha flechaIzq"><</button>
                        <button class="flecha flechaDer">></button>
                        <div class="contenedorPeliculas">
                            <% for (Peliculas pelicula: lp) {
                                if(pelicula.getCategoria().equals("Terror")) {
                                out.println("<div class=\"div-pelicula\" >");
                                    out.println("<div class=\"div-cartel\" >");
                                        out.println("<img class=\"cartel\" src=\"/Paniculas/" + pelicula.getImagen() + "\" alt=\"Imagen de la película\" >");
                                    out.println("</div>");
                                    out.println("<div>");
                                        out.println("<p class=\"nombrePelicula\" >" + pelicula.getNombre() + "</p>");
                                    out.println("</div>");
                                out.println("</div>");
                                }}
                            %>
                        </div>
                    </div>
                </div>
              </div>

              <div class="contenedor">
                <div class="categoria div-genero-Romance" id="romance">
                    <h2 class="nombreCategoria" data-descripcion="Descripción de Romance">Romance</h2>
                    <div class="peliculas-container">
                        <button class="flecha flechaIzq"><</button>
                        <button class="flecha flechaDer">></button>
                        <div class="contenedorPeliculas">
                            <% for (Peliculas pelicula: lp) {
                                if(pelicula.getCategoria().equals("Romance")) {
                                out.println("<div class=\"div-pelicula\" >");
                                    out.println("<div class=\"div-cartel\" >");
                                        out.println("<img class=\"cartel\" src=\"/Paniculas/" + pelicula.getImagen() + "\" alt=\"Imagen de la película\" >");
                                    out.println("</div>");
                                    out.println("<div>");
                                        out.println("<p class=\"nombrePelicula\" >" + pelicula.getNombre() + "</p>");
                                    out.println("</div>");
                                out.println("</div>");
                                }}
                            %>
                        </div>
                    </div>
                </div>
              </div>

              <div class="contenedor">
                <div class="categoria div-genero-Suspense" id="suspense">
                    <h2 class="nombreCategoria" data-descripcion="Descripción de Suspense">Suspense</h2>
                    <div class="peliculas-container">
                        <button class="flecha flechaIzq"><</button>
                        <button class="flecha flechaDer">></button>
                        <div class="contenedorPeliculas">
                            <% for (Peliculas pelicula: lp) {
                                if(pelicula.getCategoria().equals("Suspense")) {
                                out.println("<div class=\"div-pelicula\" >");
                                    out.println("<div class=\"div-cartel\" >");
                                        out.println("<img class=\"cartel\" src=\"/Paniculas/" + pelicula.getImagen() + "\" alt=\"Imagen de la película\" >");
                                    out.println("</div>");
                                    out.println("<div>");
                                        out.println("<p class=\"nombrePelicula\" >" + pelicula.getNombre() + "</p>");
                                    out.println("</div>");
                                out.println("</div>");
                                }}
                            %>
                        </div>
                    </div>
                </div>
              </div>

              <div class="contenedor">
                <div class="categoria div-genero-Animación" id="animacion">
                    <h2 class="nombreCategoria" data-descripcion="Descripción de Animación">Animación</h2>
                    <div class="peliculas-container">
                        <button class="flecha flechaIzq"><</button>
                        <button class="flecha flechaDer">></button>
                        <div class="contenedorPeliculas">
                            <% for (Peliculas pelicula: lp) {
                                if(pelicula.getCategoria().equals("Animación")) {
                                out.println("<div class=\"div-pelicula\" >");
                                    out.println("<div class=\"div-cartel\" >");
                                        out.println("<img class=\"cartel\" src=\"/Paniculas/" + pelicula.getImagen() + "\" alt=\"Imagen de la película\" >");
                                    out.println("</div>");
                                    out.println("<div>");
                                        out.println("<p class=\"nombrePelicula\" >" + pelicula.getNombre() + "</p>");
                                    out.println("</div>");
                                out.println("</div>");
                                }}
                            %>
                        </div>
                    </div>
                </div>
              </div>

              <div class="contenedor">
                <div class="categoria div-genero-Documental" id="documental">
                    <h2 class="nombreCategoria" data-descripcion="Descripción de Documental">Documental</h2>
                    <div class="peliculas-container">
                        <button class="flecha flechaIzq"><</button>
                        <button class="flecha flechaDer">></button>
                        <div class="contenedorPeliculas">
                            <% for (Peliculas pelicula: lp) {
                                if(pelicula.getCategoria().equals("Documental")) {
                                out.println("<div class=\"div-pelicula\" >");
                                    out.println("<div class=\"div-cartel\" >");
                                        out.println("<img class=\"cartel\" src=\"/Paniculas/" + pelicula.getImagen() + "\" alt=\"Imagen de la película\" >");
                                    out.println("</div>");
                                    out.println("<div>");
                                        out.println("<p class=\"nombrePelicula\" >" + pelicula.getNombre() + "</p>");
                                    out.println("</div>");
                                out.println("</div>");
                                }}
                            %>
                        </div>
                    </div>
                </div>
              </div>

              <div class="contenedor">
                <div class="categoria div-genero-Guerra" id="guerra">
                    <h2 class="nombreCategoria" data-descripcion="Descripción de Guerra">Guerra</h2>
                    <div class="peliculas-container">
                        <button class="flecha flechaIzq"><</button>
                        <button class="flecha flechaDer">></button>
                        <div class="contenedorPeliculas">
                            <% for (Peliculas pelicula: lp) {
                                if(pelicula.getCategoria().equals("Guerra")) {
                                out.println("<div class=\"div-pelicula\" >");
                                    out.println("<div class=\"div-cartel\" >");
                                        out.println("<img class=\"cartel\" src=\"/Paniculas/" + pelicula.getImagen() + "\" alt=\"Imagen de la película\" >");
                                    out.println("</div>");
                                    out.println("<div>");
                                        out.println("<p class=\"nombrePelicula\" >" + pelicula.getNombre() + "</p>");
                                    out.println("</div>");
                                out.println("</div>");
                                }}
                            %>
                        </div>
                    </div>
                </div>
              </div>
        </main>
        <footer>
            <p>&copy; 2023 Antonio José Muriel Gálvez</p>
        </footer>
    </body>

</html>
