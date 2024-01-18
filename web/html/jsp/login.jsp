<%-- 
    Document   : login
    Created on : 22-nov-2023, 16:54:08
    Author     : anton
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Iniciar Sesión</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="${pageContext.request.contextPath}/html/css/estiloLogin.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="container">
            <form onsubmit="return validar(this);" method="post" id="formulario-login" class="login-container">
                <h2>Iniciar Sesión</h2>
                <div class="input-container">
                    <label for="username" class="nombre-label">Usuario</label>
                    <input type="text" id="username" name="username" placeholder="Nombre de usuario" required>
                </div>
                <div class="input-container">
                    <label for="password" class="password-label">Contraseña</label>
                    <input type="password" id="password" name="password" placeholder="Contraseña" required>
                    <span class="show-password" onclick="mostrarPassword()">Mostrar</span>
                </div>
                <div>
                    <button type="submit">Iniciar sesión</button>
                </div>
                <div class="registrarse">
                    <a href="/Paniculas/html/formulario.html">Registrarse</a>
                </div>
                <div class="forgot-password">
                    <a href="/Paniculas/html/recuperarCuenta.html">¿Olvidaste tu contraseña?</a>
                </div>
            </form>
        </div>
        <script src="${pageContext.request.contextPath}/html/js/login.js" type="text/javascript"></script>
        
    <footer>
        <p>&copy; 2023 Antonio José Muriel Gálvez</p>
    </footer>
    </body>

</html>
