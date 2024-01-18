/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

document.addEventListener('DOMContentLoaded', function() {
const filas = document.querySelectorAll('.tabla-usuarios tbody tr');
document.querySelector(".contenedor-datos-usuario").classList.add("oculto");
document.querySelector('.btnVerDatosUsuario').classList.add("oculto");

    // Agregar un evento click a cada fila
    filas.forEach((fila, indice) => {
        fila.addEventListener('click', () => {
            document.querySelector(".tituloTabla").classList.add("oculto");
            document.querySelector(".tabla-usuarios").classList.add("oculto");
            document.querySelector(".btnVerDatosUsuario").classList.remove("oculto");
            document.querySelector(".contenedor-datos-usuario").classList.remove("oculto");
            // Obtener los datos de la fila clicada
            const datosUsuario = fila.querySelectorAll('td');

            // Obtener los valores de cada campo del formulario
            const nombreUsuario = datosUsuario[0].innerText;
            const password = datosUsuario[1].innerText;
            const rol = datosUsuario[2].innerText;
            const email = datosUsuario[3].innerText;
            const direccion = datosUsuario[4].innerText;
            const codigoPostal = datosUsuario[5].innerText;
            const telefono = datosUsuario[6].innerText;

            // Actualizar los valores del formulario con los datos de la fila clicada
            document.querySelector('input[name="nombre"]').value = nombreUsuario;
            document.querySelector('input[name="rol"]').value = rol;
            document.querySelector('input[name="email"]').value = email;
            document.querySelector('input[name="password"]').value = password;
            document.querySelector('input[name="confirm_password"]').value = password; // Asumiendo que confirm_password tiene el mismo valor que password
            document.querySelector('input[name="direccion"]').value = direccion;
            document.querySelector('input[name="codigo_postal"]').value = codigoPostal;
            document.querySelector('input[name="telefono"]').value = telefono;
        });
    });
});

function verTabla() {
    document.querySelector(".tabla-usuarios").classList.remove("oculto");
    document.querySelector(".btnVerDatosUsuario").classList.add("oculto");
    document.querySelector(".contenedor-datos-usuario").classList.add("oculto");
    document.querySelector(".tituloTabla").classList.remove("oculto");
}