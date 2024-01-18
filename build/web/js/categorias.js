/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */

document.addEventListener('DOMContentLoaded', function() {
    contenedorPeliculas = document.querySelectorAll(".div-pelicula");
    for (var i=0; i<contenedorPeliculas.length;i++) {
        contenedorImagen = contenedorPeliculas[i].querySelector(".div-cartel");
        nombrePeli = contenedorPeliculas[i].querySelector(".nombrePelicula").textContent;
        contenedorImagen.addEventListener('click', function(event) {
            var urlPelicula = "Pelicula/" + encodeURIComponent(nombrePeli);
            window.location.href = urlPelicula;
        });
    }
    ocultarPeliculas();
});
    

function ocultarPeliculas() {
    const ANCHO_PELICULA = 200; // Ancho fijo de cada película en píxeles

    const categorias = document.querySelectorAll('.categoria');

    categorias.forEach(categoria => {
        const contenedorPeliculas = categoria.querySelector('.contenedorPeliculas');
        const contenedorWidth = contenedorPeliculas.offsetWidth;
        const peliculas = contenedorPeliculas.querySelectorAll('.div-pelicula');

        const cantidadPeliculasCaben = Math.floor(contenedorWidth / ANCHO_PELICULA);

        peliculas.forEach((pelicula, index) => {
            if (index >= cantidadPeliculasCaben) {
                pelicula.classList.add('peliculaNoVisible');
            } else {
                pelicula.classList.remove('peliculaNoVisible');
            }
        });
    });
}


// Función para manejar el desplazamiento de las películas al hacer clic en las flechas
function moverPeliculas(contenedor, direccion) {
  const contenedorPeliculas = contenedor.querySelector('.contenedorPeliculas');
  const peliculas = contenedorPeliculas.querySelectorAll('.div-pelicula');

  // Determinar la película visible actualmente
  let primeraPeliculaVisible;
  for (let i = 0; i < peliculas.length; i++) {
    if (!peliculas[i].classList.contains('peliculaNoVisible')) {
      primeraPeliculaVisible = i;
      break;
    }
  }

    // Determinar la película visible actualmente
  let ultimaPeliculaVisible;
  for (let i = primeraPeliculaVisible; i < peliculas.length; i++) {
    if (peliculas[i].classList.contains('peliculaNoVisible')) {
        ultimaPeliculaVisible = i - 1;
      break;
    } else {
        ultimaPeliculaVisible = i;
    }
  }

  // Mostrar siguiente película hacia la derecha
  if (direccion === 'derecha') {
    const siguientePelicula = ultimaPeliculaVisible + 1;
    if (siguientePelicula < peliculas.length) {
      peliculas[primeraPeliculaVisible].classList.add('peliculaNoVisible');
      peliculas[siguientePelicula].classList.remove('peliculaNoVisible');
    }
  }
  // Mostrar película anterior hacia la izquierda
  else if (direccion === 'izquierda') {
    const anteriorPelicula = primeraPeliculaVisible - 1;
    if (primeraPeliculaVisible > 0) {
      peliculas[ultimaPeliculaVisible].classList.add('peliculaNoVisible');
      peliculas[anteriorPelicula].classList.remove('peliculaNoVisible');
    }
  }
}
