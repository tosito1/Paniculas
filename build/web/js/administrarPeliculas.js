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
            const datosPeliculas = fila.querySelectorAll('td');

            // Obtener los valores de cada campo del formulario
            const imagen = datosPeliculas[0].querySelector('img').getAttribute('src').replace(/^\/Paniculas\//, '');
            const nombre = datosPeliculas[1].innerText;
            const categoria = datosPeliculas[2].innerText;
            const director = datosPeliculas[3].innerText;
            const anio = datosPeliculas[4].innerText;
            const valoracion = datosPeliculas[5].innerText;
            const sinopsis = datosPeliculas[6].innerText;

            // Actualizar los valores del formulario con los datos de la fila clicada
            document.querySelector('input[name="imagen"]').value = imagen;
            document.querySelector('input[name="nombre"]').value = nombre;
            document.querySelector('input[name="categoria"]').value = categoria;
            document.querySelector('input[name="director"]').value = director;
            document.querySelector('input[name="anio"]').value = anio; 
            document.querySelector('input[name="valoracion"]').value = valoracion;
            document.querySelector('input[name="sinopsis"]').value = sinopsis;
        });
    });
});

function verTabla() {
    document.querySelector(".tabla-usuarios").classList.remove("oculto");
    document.querySelector(".btnVerDatosUsuario").classList.add("oculto");
    document.querySelector(".contenedor-datos-usuario").classList.add("oculto");
    document.querySelector(".tituloTabla").classList.remove("oculto");
}