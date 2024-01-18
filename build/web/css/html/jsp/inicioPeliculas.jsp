<%-- 
    Document   : inicioPeliculas
    Created on : 21-nov-2023, 8:48:16
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
        <link rel="stylesheet" href="${pageContext.request.contextPath}/html/css/estiloTemplate.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/html/css/estiloIndex.css">
        <script src="${pageContext.request.contextPath}/html/js/Inicio/index.js" type="text/javascript"></script>
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
                    <li class="ventanaMenu"><a href="./Articulos">Artículos</a></li> 
                    <li class="ventanaMenu"><a href="../Perfil/DatosUsuario">Perfil</a></li>
                </ul>
            </nav>

        </header>
        <main>
            <div class="buscador">
                <input type="text" id="inputBusqueda" placeholder="Buscar películas..." oninput="buscarPeliculas()">
            </div>
            <div class="div-peliculas">
                <% 
                    List<Peliculas> lp = (List<Peliculas>) request.getAttribute("peliculas");
                    for (Peliculas pelicula : lp) {
                        out.println("<div class=\"pelicula\" >");
                            out.println("<div class=\"div-cartel\" >");
                                out.println("<img class=\"cartel\" src=\"/Paniculas/html/" + pelicula.getImagen() + "\" alt=\"Imagen de la película\" >");
                            out.println("</div>");
                            out.println("<div class=\"div-nombre\" >");
                                out.println("<h1 class=\"nombrePeli\" >" + pelicula.getNombre() + "</h1>");
                                out.println("<p class=\"sinopsisPeli\" >" + pelicula.getSinopsis()+ "</p>");
                                out.println("<p class=\"directorPeli\" >" + pelicula.getDirector()+ "</p>");
                                out.println("<p class=\"anioPeli\" >" + pelicula.getAnio()+ "</p>");
                                out.println("<p class=\"generoPeli\" >" + pelicula.getCategoria()+ "</p>");
                                out.println("<p class=\"valoracionPeli\" >" + pelicula.getValoracion()+ "</p>");
                            out.println("</div>");
                        out.println("</div>");
                    }
                    out.println();
                %>
                
            </div>
            <div id="botonesPaginas"></div>
        </main>
        <footer>
            <p>&copy; 2023 Antonio José Muriel Gálvez</p>
        </footer>
    </body>
    <script src="/Paniculas/html/js/Inicio/paginarPeliculas.js" type="text/javascript"></script>
    <script src="/Paniculas/html/js/Inicio/buscador.js" type="text/javascript"></script>
</html>
<!--
-- Películas de Acción
INSERT INTO Peliculas (nombre, categoria, anio, valoracion, director, sinopsis, imagen)
VALUES 
('Mad Max: Fury Road', 'Acción', 2015, 8.1, 'George Miller', 'En un mundo apocalíptico, Furiosa, una mujer rebelde, y Max, un hombre de acción, se unen para luchar contra Immortan Joe y liberar a un grupo de mujeres cautivas.', 'img/Accion/Mad_Max_Fury_Road.jpg'),
('Top Gun: Maverick', 'Acción', 2022, 7.5, 'Joseph Kosinski', 'Pete "Maverick" Mitchell, antiguo piloto de Top Gun, vuelve para entrenar a una nueva generación de pilotos, incluido el hijo del difunto Nick "Goose" Bradshaw.', 'img/Accion/Top_Gun_Maverick.jpg'),
('Die Hard', 'Acción', 1988, 8.2, 'John McTiernan', 'Un policía de Nueva York, John McClane, intenta salvar a su esposa y a otros rehenes atrapados por terroristas durante una fiesta navideña en un rascacielos de Los Ángeles.', 'img/Accion/Die_Hard.jpg'),
('John Wick 4', 'Acción', 2022, 7.5, 'Chad Stahelski', 'En esta cuarta entrega de la saga de acción, John Wick, interpretado por Keanu Reeves, se encuentra nuevamente en una situación límite...', 'img/Accion/JonhWick4.jpg');

-- Películas de Aventura
INSERT INTO Peliculas (nombre, categoria, anio, valoracion, director, sinopsis, imagen)
VALUES 
('Indiana Jones and the Raiders of the Lost Ark', 'Aventura', 1981, 8.4, 'Steven Spielberg', 'La historia sigue al arqueólogo y aventurero Indiana Jones (interpretado por Harrison Ford) en su búsqueda del Arca de la Alianza...', 'img/Aventura/Indiana_Jones_and_th_Raiders_of_the_Lost Ark.jpg'),
('Pirates of the Caribbean: La venganza de Salazar', 'Aventura', 2017, 6.5, 'Joachim Rønning, Espen Sandberg', 'En esta entrega de la saga, el temible Capitán Salazar escapa del Triángulo del Diablo...', 'img/Aventura/Piratas-del-Caribe-La-venganza-de-Salazar.jpg'),
('El Señor de los Anillos: La Comunidad del Anillo', 'Aventura', 2001, 8.8, 'Peter Jackson', 'En esta primera entrega de la trilogía, un joven hobbit llamado Frodo Baggins hereda un anillo mágico...', 'img/Aventura/el_señor_de_los_anillos_la_comunidad_del_anillo.jpg');

-- Películas de Comedia
INSERT INTO Peliculas (nombre, categoria, anio, valoracion, director, sinopsis, imagen)
VALUES 
('Supersalidos', 'Comedia', 2007, 7.6, 'Greg Mottola', 'La historia sigue a dos amigos de secundaria, Seth y Evan, que están a punto de graduarse y se embarcan en una aventura para tratar de comprar alcohol...', 'img/Comedia/Supersalidos.jpg'),
('El Mundo es Nuestro', 'Comedia', 2012, 6.3, 'Alfonso Sánchez', 'Esta comedia narra la historia de Rafi y Fali, dos amigos sevillanos que están cansados de su situación y deciden robar un cajero automático...', 'img/Comedia/El_mundo_es_nuestro.jpg'),
('Crazy Stupid Love', 'Comedia', 2011, 7.4, 'Glenn Ficarra, John Requa', 'La película sigue la vida de Cal Weaver, un hombre cuya vida matrimonial se desmorona después de que su esposa, Emily, le pide el divorcio...', 'img/Comedia/Crazy_Stupid_Love.jpg');

-- Películas de Drama
INSERT INTO Peliculas (nombre, categoria, anio, valoracion, director, sinopsis, imagen)
VALUES 
('La lista de Schindler', 'Drama', 1993, 8.9, 'Steven Spielberg', 'La película está ambientada durante la Segunda Guerra Mundial y sigue la historia de Oskar Schindler...', 'img/Drama/La_lista_de_Schindler.jpg'),
('Cadena Perpetua', 'Drama', 1994, 9.3, 'Frank Darabont', 'La película sigue la historia de Andy Dufresne, un banquero condenado a cadena perpetua por el asesinato de su esposa...', 'img/Drama/Cadena_perpetua.jpg'),
('El Padrino', 'Drama', 1972, 9.2, 'Francis Ford Coppola', 'La película está basada en la novela de Mario Puzo y sigue la historia de la familia criminal Corleone...', 'img/Drama/El_Padrino.jpg');

-- Películas de Ficción
INSERT INTO Peliculas (nombre, categoria, anio, valoracion, director, sinopsis, imagen)
VALUES 
('Blade Runner', 'Ficción', 1982, 8.1, 'Ridley Scott', 'La película está ambientada en un futuro distópico en el que los androides llamados "replicantes" son empleados en tareas peligrosas en la Tierra...', 'img/Ficción/Blade_Runner.jpg'),
('Origen', 'Ficción', 2010, 8.8, 'Christopher Nolan', 'La película sigue a Dom Cobb, un ladrón especializado en el arte de la extracción...', 'img/Ficción/origen.jpg'),
('Matrix', 'Ficción', 1999, 8.7, 'Hermanas Wachowski', 'La película sigue a Thomas Anderson, un programador de software conocido como Neo...', 'img/Ficción/Matrix.jpg');

-- Películas de Fantasía
INSERT INTO Peliculas (nombre, categoria, anio, valoracion, director, sinopsis, imagen)
VALUES 
('Harry Potter y la Piedra Filosofal', 'Fantasía', 2001, 7.6, 'Chris Columbus', 'La película cuenta la historia de un joven mago llamado Harry Potter...', 'img/Fantasia/Harry_Potter_y_la_Piedra_Filosofal.jpg'),
('Las crónicas de Narnia: El león, la bruja y el armario', 'Fantasía', 2005, 6.9, 'Andrew Adamson', 'La película cuenta la historia de cuatro hermanos...', 'img/Fantasia/Las_cronicas_de_Narnia_El_leon_la_bruja_y_el_armario.jpg'),
('Harry Potter y la cámara secreta', 'Fantasía', 2002, 7.4, 'Chris Columbus', 'La película continúa la historia de Harry Potter en su segundo año en Hogwarts...', 'img/Fantasia/Harry_Potter_y_la_camara_secreta.jpg');

-- Películas de Terror
INSERT INTO Peliculas (nombre, categoria, anio, valoracion, director, sinopsis, imagen)
VALUES 
('El Resplandor', 'Terror', 1980, 8.4, 'Stanley Kubrick', 'La película sigue a Jack Torrance, un escritor y aspirante a autor, que acepta un trabajo como cuidador de invierno en el Hotel Overlook...', 'img/Terror/El_Resplandor.jpg'),
('Dejame Salir', 'Terror', 2017, 7.7, 'Jordan Peele', 'La película sigue la historia de Chris Washington, un joven afroamericano que visita la finca de la familia de su novia Rose...', 'img/Terror/Dejame_salir.jpg'),
('Hereditary', 'Terror', 2018, 7.3, 'Ari Aster', 'La película sigue a la familia Graham, que comienza a experimentar eventos aterradores después de la muerte de la abuela materna...', 'img/Terror/Hereditary.jpg');

-- Películas de Romance
INSERT INTO Peliculas (nombre, categoria, anio, valoracion, director, sinopsis, imagen)
VALUES 
('El Diario de Noa', 'Romance', 2004, 7.8, 'Nick Cassavetes', 'La película narra la historia de amor entre Noah Calhoun y Allie Hamilton, dos jóvenes enamorados que provienen de diferentes clases sociales...', 'img/Romance/El_Diario_de_Noa.jpg'),
('Olvidate de mi', 'Romance', 2004, 8.3, 'Michel Gondry', 'La película narra la historia de Joel Barish y Clementine Kruczynski, quienes deciden someterse a un procedimiento científico para borrar los recuerdos de su tumultuosa relación sentimental...', 'img/Romance/Olvidate_de_mi.jpg'),
('La La Land', 'Romance', 2016, 8.0, 'Damien Chazelle', 'La película sigue la historia de Mia, una aspirante a actriz, y Sebastian, un pianista de jazz, mientras luchan por perseguir sus sueños en la competitiva y glamurosa ciudad de Los Ángeles...', 'img/Romance/La_La_Land.jpg');

-- Películas de Suspense
INSERT INTO Peliculas (nombre, categoria, anio, valoracion, director, sinopsis, imagen)
VALUES 
('Seven', 'Suspense', 1995, 8.6, 'David Fincher', 'La película sigue la historia de dos detectives, Somerset y Mills, que investigan una serie de asesinatos brutales inspirados en los siete pecados capitales. A medida que persiguen al asesino, se sumergen en un oscuro y perturbador mundo de perversiones humanas y moralidad retorcida. La trama se desarrolla en un ambiente sombrío y opresivo, mientras los detectives luchan por capturar al asesino antes de que complete su siniestro plan.', 'img/Suspense/Seven.jpg'),
('La Isla Siniestra', 'Suspense', 2010, 8.2, 'David Fincher', 'La película sigue la historia de dos detectives, Somerset y Mills, que investigan una serie de asesinatos brutales inspirados en los siete pecados capitales...', 'img/Suspense/La_Isla_Siniestra.jpg'),
('Prisioneros', 'Suspense', 2013, 8.1, 'Martin Scorsese', 'La película sigue la historia de dos agentes federales, Teddy Daniels y Chuck Aule, quienes son enviados a investigar la desaparición de una paciente en un hospital psiquiátrico ubicado en una isla remota...', 'img/Suspense/Prisioneros.jpg');

-- Películas de Animación
INSERT INTO Peliculas (nombre, categoria, anio, valoracion, director, sinopsis, imagen)
VALUES 
('Toy Story', 'Animación', 1995, 8.3, 'John Lasseter', 'La película sigue la vida de los juguetes de Andy, especialmente Woody, un vaquero, y Buzz Lightyear, un astronauta del espacio...', 'img/Animacion/Toy_Strory.jpg'),
('One Piece: Red', 'Animación', 2019, 7.7, 'John Lasseter', 'La película sigue la vida de los juguetes de Andy, especialmente Woody, un vaquero, y Buzz Lightyear, un astronauta del espacio. Cuando Buzz se une a la colección de juguetes de Andy, desencadena la rivalidad con Woody, quien se siente amenazado por el nuevo juguete favorito de Andy. Sin embargo, cuando ambos juguetes se pierden accidentalmente, deben unir fuerzas y superar sus diferencias para regresar a casa y demostrar su valía como juguetes de Andy.', 'img/Animacion/One_Piece_Film_Red.jpg'),
('Shrek', 'Animación', 2001, 7.8, 'Andrew Adamson, Vicky Jenson', 'Shrek es una historia que gira en torno a un ogro solitario y gruñón llamado Shrek...', 'img/Animacion/Shrek.jpg');

-- Películas de Documental
INSERT INTO Peliculas (nombre, categoria, anio, valoracion, director, sinopsis, imagen)
VALUES 
('El Impostor', 'Documental', 2012, 7.5, 'Bart Layton', 'Relata la historia de Frederic Bourdin, un impostor francés que se hizo pasar por Nicholas Barclay, un niño estadounidense desaparecido. Bourdin, a pesar de ser mucho mayor que el niño desaparecido, logró engañar a la familia y a las autoridades durante varios meses. La película explora los eventos y las motivaciones detrás de este increíble engaño, mostrando cómo Bourdin pudo manipular la situación y los aspectos psicológicos involucrados en este misterioso caso de identidad equivocada.', 'img/Documentales/El_impostor.jpg'),
('Quieres Ser Mi Vecino?', 'Documental', 2018, 8.1, 'Bart Layton', 'La película documental ''El Impostor'' relata la historia de Frederic Bourdin, un impostor francés que se hizo pasar por Nicholas Barclay, un niño estadounidense desaparecido. Bourdin, a pesar de ser mucho mayor que el niño desaparecido, logró engañar a la familia y a las autoridades durante varios meses. La película explora los eventos y las motivaciones detrás de este increíble engaño, mostrando cómo Bourdin pudo manipular la situación y los aspectos psicológicos involucrados en este misterioso caso de identidad equivocada.', 'img/Documentales/quieres-ser-mi-vecino.jpeg'),
('Inside Job', 'Documental', 2010, 8.2, 'Charles Ferguson', 'El documental ''Inside Job'' examina la crisis financiera global de 2008 y sus repercusiones económicas en todo el mundo. Dirigido por Charles Ferguson, el filme investiga las causas y factores que llevaron al colapso económico, analizando la complejidad de la crisis financiera, desde la burbuja inmobiliaria hasta las decisiones políticas y de regulación. A través de entrevistas con académicos, políticos y financieros clave, ''Inside Job'' arroja luz sobre la corrupción, la codicia y los conflictos de interés que contribuyeron a esta crisis financiera global.', 'img/Documentales/Inside_Job.jpg');

-- Películas de Guerra
INSERT INTO Peliculas (nombre, categoria, anio, valoracion, director, sinopsis, imagen)
VALUES 
('Salvar al soldado Ryan', 'Guerra', 1998, 8.6, 'Steven Spielberg', 'La película se desarrolla durante la Segunda Guerra Mundial, siguiendo a un grupo de soldados liderados por el Capitán John Miller (interpretado por Tom Hanks), que reciben la misión de localizar al soldado James Ryan, cuyos tres hermanos han fallecido en combate. La búsqueda se convierte en una odisea peligrosa y emocional a través de territorio enemigo mientras Miller y su equipo luchan por mantenerse juntos y cumplir su misión en medio del caos y la brutalidad de la guerra.', 'img/Guerra/Salvar_al_soldado_Ryan.jpg'),
('El niño con el pijama de rayas', 'Guerra', 2008, 7.8, 'Mark Herman', 'La película está basada en la novela homónima y sigue la historia de Bruno, un niño alemán durante la Segunda Guerra Mundial, cuya familia se muda a una casa cercana a un campo de concentración nazi donde su padre trabaja. Durante su exploración, Bruno entabla amistad a través de la cerca con Shmuel, un niño judío prisionero del campo, quien usa un pijama a rayas. A pesar de las diferencias y desconocimiento de Bruno sobre la realidad del campo, la amistad entre los dos niños crece, llevando a una conmovedora y trágica conclusión.', 'img/Guerra/El_niño_con_el_pijama_de_rayas.jpg'),
('La Chaqueta Metálica', 'Guerra', 1987, 8.3, 'Stanley Kubrick', 'La película sigue a un grupo de reclutas durante su entrenamiento en la infantería de Marines de los Estados Unidos y posterior despliegue a la Guerra de Vietnam. Kubrick retrata de manera cruda y realista las experiencias de los soldados, explorando los efectos deshumanizadores y psicológicos del entrenamiento militar y el trauma de la guerra en la moral y la mente de los reclutas. La película se divide en dos partes: la primera en el campo de entrenamiento y la segunda en el contexto de la guerra en Vietnam.', 'img/Guerra/La_Chaqueta_Matalica.jpg');




CREATE TABLE CREDENCIALES (
    USUARIO VARCHAR(100) PRIMARY KEY,
    PASSWORD VARCHAR(100) NOT NULL,
    ROL_ID INT,
    HASH_CONTRASENA VARCHAR(128), 
    FOREIGN KEY (ROL_ID) REFERENCES ROLES(ID)
);

CREATE TABLE COMENTARIOS (
    ID INT GENERATED BY DEFAULT AS IDENTITY (START WITH 1 INCREMENT BY 1) PRIMARY KEY,
    VALORACION INT NOT NULL,
    MENSAJE CLOB NOT NULL,
    USUARIO VARCHAR(100),
    PELICULA_ID INT,
    FOREIGN KEY (USUARIO) REFERENCES CREDENCIALES(USUARIO),
    FOREIGN KEY (PELICULA_ID) REFERENCES PELICULAS(ID)
);

CREATE TABLE DATOSADICIONALES (
    ID INT GENERATED BY DEFAULT AS IDENTITY (START WITH 1 INCREMENT BY 1) PRIMARY KEY,
    EMAIL VARCHAR(100) NOT NULL UNIQUE,
    DIRECCION VARCHAR(255) NOT NULL,
    CODIGO_POSTAL VARCHAR(10) NOT NULL,
    TELEFONO VARCHAR(20) NOT NULL,
    USUARIO VARCHAR(100),
    FOREIGN KEY (USUARIO) REFERENCES CREDENCIALES(USUARIO)
);

CREATE TABLE PELICULAS (
    ID INT GENERATED BY DEFAULT AS IDENTITY (START WITH 1 INCREMENT BY 1) PRIMARY KEY,
    NOMBRE VARCHAR(100) NOT NULL UNIQUE,
    CATEGORIA VARCHAR(50),
    ANIO INT,
    DIRECTOR VARCHAR(100),
    VALORACION DECIMAL(10,2),
    SINOPSIS CLOB,
    IMAGEN VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE ROLES (
    ID INT GENERATED BY DEFAULT AS IDENTITY (START WITH 1 INCREMENT BY 1) PRIMARY KEY,
    ROL VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE USUARIOSCATEGORIAS (
    ID INT GENERATED BY DEFAULT AS IDENTITY (START WITH 1 INCREMENT BY 1) PRIMARY KEY,
    CATEGORIA VARCHAR(50),
    USUARIO VARCHAR(100),
    FOREIGN KEY (USUARIO) REFERENCES CREDENCIALES(USUARIO)
);

CREATE TABLE USUARIOROLES (
    ID INT GENERATED BY DEFAULT AS IDENTITY (START WITH 1 INCREMENT BY 1) PRIMARY KEY,
    USUARIO VARCHAR(100) NOT NULL UNIQUE,
    ROL_ID INT NOT NULL UNIQUE,
    FOREIGN KEY (USUARIO) REFERENCES CREDENCIALES(USUARIO),
    FOREIGN KEY (ROL_ID) REFERENCES ROLES(ID)
);

INSERT INTO ROLES (ROL) VALUES ('Usuario');
INSERT INTO ROLES (ROL) VALUES ('Administrador');


DROP TABLE comentarios;
DROP TABLE datosadicionales;
DROP TABLE peliculas;
DROP TABLE roles;
DROP TABLE usuarioscategorias;
DROP TABLE usuariosroles;
-->
