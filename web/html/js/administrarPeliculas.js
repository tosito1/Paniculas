/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

document.addEventListener('DOMContentLoaded', function() {
const filas = document.querySelectorAll('.tabla-peliculas tbody tr');
document.querySelector(".contenedor-datos-peliculas").classList.add("oculto");
document.querySelector('.btnVerDatosPelicula').classList.add("oculto");

    // Agregar un evento click a cada fila
    filas.forEach((fila, indice) => {
        fila.addEventListener('click', () => {
            document.querySelector(".tituloTabla").classList.add("oculto");
            document.querySelector(".tabla-peliculas").classList.add("oculto");
            document.getElementById('btnNuevaPelicula').classList.add('oculto');
            document.querySelector(".btnVerDatosPelicula").classList.remove("oculto");
            document.querySelector(".contenedor-datos-peliculas").classList.remove("oculto");
            // Obtener los datos de la fila clicada
            const datosPeliculas = fila.querySelectorAll('td');

            // Obtener los valores de cada campo del formulario
            const nombre = datosPeliculas[1].innerText;
            const categoria = datosPeliculas[2].innerText;
            const director = datosPeliculas[3].innerText;
            const anio = datosPeliculas[4].innerText;
            const valoracion = datosPeliculas[5].innerText;
            const sinopsis = datosPeliculas[6].innerText;

            document.querySelector('input[name="nombreactual"]').readOnly = true;
            document.querySelector('input[name="nombreactual"]').value = nombre;
            document.querySelector('input[name="nombrenuevo"]').value = nombre;
            document.querySelector('input[name="categoria"]').value = categoria;
            document.querySelector('input[name="director"]').value = director;
            document.querySelector('input[name="anio"]').value = anio; 
            document.querySelector('input[name="valoracion"]').value = valoracion;
            document.querySelector('input[name="sinopsis"]').value = sinopsis;
        });
    });
    const input = document.querySelector('.upload');
    const fileName = document.querySelector('.file-name');

    input.addEventListener('change', function() {
      fileName.textContent = this.files[0].name;
    });
});

function verTabla() {
    document.querySelector(".tabla-peliculas").classList.remove("oculto");
    document.querySelector(".btnVerDatosPelicula").classList.add("oculto");
    document.getElementById('btnNuevaPelicula').classList.remove('oculto');
    document.querySelector(".contenedor-datos-peliculas").classList.add("oculto");
    document.querySelector(".tituloTabla").classList.remove("oculto");
}

function nuevaPelicula() {
    document.querySelector(".tabla-peliculas").classList.add("oculto");
    document.querySelector(".btnVerDatosPelicula").classList.remove("oculto");
    document.querySelector(".btnNuevaPelicula").classList.add("oculto");
    document.querySelector(".contenedor-datos-peliculas").classList.remove("oculto");
    document.querySelector(".tituloTabla").classList.add("oculto");
    
    document.querySelector('input[name="nombreactual"]').readOnly = true;
    document.querySelector('input[name="imagen"]').readOnly = false;
    document.querySelector('input[name="nombrenuevo"]').readOnly = false;
    document.querySelector('input[name="categoria"]').readOnly = false;
    document.querySelector('input[name="director"]').readOnly = false;
    document.querySelector('input[name="anio"]').readOnly = false;
    document.querySelector('input[name="valoracion"]').readOnly = false;
    document.querySelector('input[name="sinopsis"]').readOnly = false;
    
    document.querySelector('input[name="nombreactual"]').value = "";
    document.querySelector('input[name="imagen"]').value = "";
    document.querySelector('input[name="nombrenuevo"]').value = "";
    document.querySelector('input[name="categoria"]').value = "";
    document.querySelector('input[name="director"]').value = "";
    document.querySelector('input[name="anio"]').value = ""; 
    document.querySelector('input[name="valoracion"]').value = "";
    document.querySelector('input[name="sinopsis"]').value = "";
    
    document.querySelector('.registro-form').action = 'InsertarPeliculas';
    boton = document.getElementById('btnCambiarDatosPelicula');
    boton.innerHTML = 'Guardar Pelicula';
}

function eliminarPelicula() {
    document.querySelector('.registro-form').action = 'EliminarPeliculas';
    boton = document.getElementById('btnCambiarDatosPelicula');
    boton.innerHTML = 'Eliminar Pelicula';

    document.querySelector('input[name="nombreactual"]').readOnly = true;
    document.querySelector('input[name="imagen"]').readOnly = true;
    document.querySelector('input[name="nombrenuevo"]').readOnly = true;
    document.querySelector('input[name="categoria"]').readOnly = true;
    document.querySelector('input[name="director"]').readOnly = true;
    document.querySelector('input[name="anio"]').readOnly = true;
    document.querySelector('input[name="valoracion"]').readOnly = true;
    document.querySelector('input[name="sinopsis"]').readOnly = true;
}

function actualizarPelicula() {
    document.querySelector('input[name="nombreactual"]').readOnly = true;
    document.querySelector('input[name="imagen"]').readOnly = false;
    document.querySelector('input[name="nombrenuevo"]').readOnly = false;
    document.querySelector('input[name="categoria"]').readOnly = false;
    document.querySelector('input[name="director"]').readOnly = false;
    document.querySelector('input[name="anio"]').readOnly = false;
    document.querySelector('input[name="valoracion"]').readOnly = false;
    document.querySelector('input[name="sinopsis"]').readOnly = false;
    
    document.querySelector('.registro-form').action = 'ActualizarPelicula';
    boton = document.getElementById('btnCambiarDatosPelicula');
    boton.innerHTML = 'Actualizar Pelicula';
}