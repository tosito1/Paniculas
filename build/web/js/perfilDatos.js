/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

document.addEventListener('DOMContentLoaded', function() {
    const btnAdminUsuarios = document.getElementById('btnAdminUsuarios');
    const btnAdminPeliculas = document.getElementById('btnAdminPeliculas');
    const btnCambiarDatosUsuario = document.getElementById('btnCambiarDatosUsuario');
    const btnCerrarSesion = document.getElementById('btnCerrarSesion');

    // Agrega un evento "click" a cada botón
    btnCerrarSesion.addEventListener('click', redirectToUr);
    btnCambiarDatosUsuario.addEventListener('click', redirectToUr);
    btnAdminUsuarios.addEventListener('click', redirectToUr);
    btnAdminPeliculas.addEventListener('click', redirectToUr);
});
// Función para redireccionar a la URL especificada en el atributo "data-url"
function redirectToUr(event) {
  const url = event.target.getAttribute('data-url');
  if (url) {
    window.location.href = url;
  }
}