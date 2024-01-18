<%-- 
    Document   : cambiarDatosPerfil
    Created on : 22-nov-2023, 10:29:23
    Author     : anton
--%>

<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Paniculas</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="${pageContext.request.contextPath}/html/css/estiloTemplate.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/html/css/estiloPerfilActualizar.css" rel="stylesheet" type="text/css"/>
        <!--<script src="${pageContext.request.contextPath}/js/perfilDatos.js" type="text/javascript"></script>-->
        
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
            <div class="contenedor-datos-usuario">
                <h2>Datos Personales</h2>
                <form onsubmit="return validar(this);" class="registro-form">
                    <div class="campo">
                        <label for="nombreactual" class="form-label nombre-label">Usuario</label>
                        <input type="text" id="nombreactual" name="nombreactual" value="<% out.print(request.getAttribute("usuario")); %>" class="form-input" required readOnly>
                        <div class="error-message"></div>
                    </div>
                        <input name="rol" id="rol" value="<% out.print(session.getAttribute("rol")); %>" style="display: none;">
                    <div class="campo">
                        <label for="nombrenuevo" class="form-label nombre-label">Usuario</label>
                        <input type="text" id="nombrenuevo" name="nombrenuevo" value="<% out.print(request.getAttribute("usuario")); %>" class="form-input" required>
                        <div class="error-message"></div>
                    </div>
                    <div class="campo">
                        <label for="email" class="form-label correo_label">Correo Electrónico (example@gmail.com)<span>*</span></label>
                        <input type="email" id="email" name="email" value="<% out.print(request.getAttribute("email")); %>" class="form-input obligatorio" required>
                        <div class="error-message"></div>
                    </div>

                    <div class="campo">
                        <label for="password" class="form-label password-label">Clave <span>*</span></label>
                        <input type="password" id="password" name="password" value="<% out.print(request.getAttribute("password")); %>" class="form-input obligatorio" required>
                        <div class="error-message"></div>
                    </div>

                    <div class="campo">
                        <label for="confirm_password" class="form-label password-repit-label">Repetir Clave <span>*</span></label>
                        <input type="password" id="confirm_password" name="confirm_password" value="<% out.print(request.getAttribute("password")); %>" class="form-input obligatorio" required>
                        <div class="error-message"></div>
                    </div>

                    <div class="campo">
                        <label for="direccion" class="form-label">Dirección</label>
                        <input type="text" id="direccion" name="direccion" value="<% out.print(request.getAttribute("direccion")); %>" class="form-input">
                    </div>    
                    <div class="campo">
                        <label for="codigo_postal" class="form-label codigo_postal-label">Código Postal (12345) <span>*</span></label>
                        <input type="text" id="codigo_postal" name="codigo_postal" value="<% out.print(request.getAttribute("codigo_postal")); %>" class="form-input obligatorio" pattern="[0-9]{2}[0-9]{3}" required>
                        <div class="error-message"></div>
                    </div>
                    <div class="campo">
                        <label for="provincia" class="form-label">Provincia</label>
                            <select id="provincia" name="provincia" class="form-input">
                            <option value="">Elige Provincia</option>
                            <option value="01">Álava</option>
                            <option value="02">Albacete</option>
                            <option value="03">Alicante</option>
                            <option value="04">Almería</option>
                            <option value="33">Asturias</option>
                            <option value="05">Ávila</option>
                            <option value="06">Badajoz</option>
                            <option value="07">Baleares</option>
                            <option value="08">Barcelona</option>
                            <option value="09">Burgos</option>
                            <option value="10">Cáceres</option>
                            <option value="11">Cádiz</option>
                            <option value="39">Cantabria</option>
                            <option value="12">Castellón</option>
                            <option value="51">Ceuta</option>
                            <option value="13">Ciudad Real</option>
                            <option value="14">Córdoba</option>
                            <option value="15">La Coruña</option>
                            <option value="16">Cuenca</option>
                            <option value="17">Gerona</option>
                            <option value="18">Granada</option>
                            <option value="19">Guadalajara</option>
                            <option value="20">Guipúzcoa</option>
                            <option value="21">Huelva</option>
                            <option value="22">Huesca</option>
                            <option value="23">Jaén</option>
                            <option value="32">La Rioja</option>
                            <option value="24">León</option>
                            <option value="25">Lérida</option>
                            <option value="27">Lugo</option>
                            <option value="28">Madrid</option>
                            <option value="29">Málaga</option>
                            <option value="52">Melilla</option>
                            <option value="30">Murcia</option>
                            <option value="31">Navarra</option>
                            <option value="32">Orense</option>
                            <option value="34">Palencia</option>
                            <option value="36">Pontevedra</option>
                            <option value="37">Salamanca</option>
                            <option value="40">Segovia</option>
                            <option value="41">Sevilla</option>
                            <option value="42">Soria</option>
                            <option value="43">Tarragona</option>
                            <option value="38">Tenerife</option>
                            <option value="44">Teruel</option>
                            <option value="45">Toledo</option>
                            <option value="46">Valencia</option>
                            <option value="47">Valladolid</option>
                            <option value="48">Vizcaya</option>
                            <option value="49">Zamora</option>
                            <option value="50">Zaragoza</option>
                        </select>
                    </div>
                    <div class="campo">
                        <label for="telefono" class="form-label telefono-label">Teléfono de Contacto (999999999) <span>*</span></label>
                        <input type="tel" id="telefono" name="telefono" value="<% out.print(request.getAttribute("telefono")); %>" class="form-input obligatorio" pattern="[0-9]{3}[0-9]{3}[0-9]{3}" required>
                        <div class="error-message"></div>
                    </div>

                    <div class="campo">
                        <label class="form-label">Categorías de Interés</label>
                        <div class="conjuntoCheckbox">
                            <input type="checkbox" class="form-checkbox" id="electronica" name="categorias[]" value="Acción">
                            <label for="electronica" class="form-checkbox-label">Acción</label>

                            <input type="checkbox" class="form-checkbox" id="libros" name="categorias[]" value="Aventura">
                            <label for="libros" class="form-checkbox-label">Aventura</label>

                            <input type="checkbox" class="form-checkbox" id="juegos" name="categorias[]" value="Comedia">
                            <label for="juegos" class="form-checkbox-label">Comedia</label>

                            <input type="checkbox" class="form-checkbox" id="otras" name="categorias[]" value="Drama">
                            <label for="otras" class="form-checkbox-label">Drama</label>

                            <input type="checkbox" class="form-checkbox" id="terror" name="categorias[]" value="Terror">
                            <label for="terror" class="form-checkbox-label">Terror</label>

                            <input type="checkbox" class="form-checkbox" id="romance" name="categorias[]" value="Romance">
                            <label for="romance" class="form-checkbox-label">Romance</label>

                            <input type="checkbox" class="form-checkbox" id="ciencia_ficcion" name="categorias[]" value="Ciencia ficción">
                            <label for="ciencia_ficcion" class="form-checkbox-label">Ciencia ficción</label>

                            <input type="checkbox" class="form-checkbox" id="fantasia" name="categorias[]" value="Fantasía">
                            <label for="fantasia" class="form-checkbox-label">Fantasía</label>

                            <input type="checkbox" class="form-checkbox" id="suspense" name="categorias[]" value="Suspense">
                            <label for="suspense" class="form-checkbox-label">Suspense</label>

                            <input type="checkbox" class="form-checkbox" id="animacion" name="categorias[]" value="Animación">
                            <label for="animacion" class="form-checkbox-label">Animación</label>

                            <input type="checkbox" class="form-checkbox" id="documental" name="categorias[]" value="Documental">
                            <label for="documental" class="form-checkbox-label">Documental</label>

                            <input type="checkbox" class="form-checkbox" id="guerra" name="categorias[]" value="Guerra">
                            <label for="guerra" class="form-checkbox-label">Guerra</label>
                        </div>
                    </div>
                    <button type="submit" id="btnCambiarDatosUsuario" class="form-submit">Actualizar Datos</button>
                </form>
            </div>
        </main>
        <footer>
            <p>&copy; 2023 Antonio José Muriel Gálvez</p>
        </footer>
            <script src="${pageContext.request.contextPath}/html/js/actualizarPerfil.js" type="text/javascript"></script>
    </body>
</html>
