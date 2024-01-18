/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

function buscarPeliculas() {
    var input = document.getElementById('inputBusqueda');
    var filter = input.value.trim().toUpperCase();
    var divPeliculas = document.getElementsByClassName('div-peliculas')[0];
    var peliculas = divPeliculas.getElementsByClassName('pelicula');

    for (var i = 0; i < peliculas.length; i++) {
        var nombrePeli = peliculas[i].getElementsByClassName('nombrePeli')[0];
        var textoPeli = nombrePeli.textContent || nombrePeli.innerText;

        if (textoPeli.toUpperCase().includes(filter)) {
            peliculas[i].style.display = 'block';
        } else {
            peliculas[i].style.display = 'none';
        }
    }
}


