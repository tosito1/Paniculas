/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


document.addEventListener('DOMContentLoaded', function() {
    var peliculas = document.querySelectorAll(".nombreArticulo");
    var peliculasPorPagina = 10;
    var numPaginas = Math.ceil(peliculas.length / peliculasPorPagina);
    var paginaActual = 1;

    function mostrarPagina(numeroPagina) {
        var inicio = (numeroPagina - 1) * peliculasPorPagina;
        var fin = inicio + peliculasPorPagina;
        
        peliculas.forEach(function(pelicula, index) {
            if (index >= inicio && index < fin) {
                pelicula.parentElement.style.display = 'table-row';
            } else {
                pelicula.parentElement.style.display = 'none';
            }
        });
    }

    mostrarPagina(paginaActual);

    var botonesPaginas = document.getElementById('botonesPaginas');

    var botonAnterior = document.createElement('button');
    botonAnterior.textContent = 'Anterior';
    botonAnterior.addEventListener('click', function() {
        if (paginaActual > 1) {
            paginaActual--;
            mostrarPagina(paginaActual);
        }
    });
    botonesPaginas.appendChild(botonAnterior);

    var botonSiguiente = document.createElement('button');
    botonSiguiente.textContent = 'Siguiente';
    botonSiguiente.addEventListener('click', function() {
        if (paginaActual < numPaginas) {
            paginaActual++;
            mostrarPagina(paginaActual);
        }
    });
    botonesPaginas.appendChild(botonSiguiente);
});
