/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */

document.addEventListener('DOMContentLoaded', function() {
    const queryString = window.location.search;
    const params = new URLSearchParams(queryString);
    const nombrePelicula = params.get('nombre');
    const nombreDecodificado = decodeURIComponent(nombrePelicula);
    console.log(nombreDecodificado);

    document.querySelector('.pelicula-detalles h1').textContent = nombreDecodificado;
    
    fetch("../js/peliculas.json")
        .then(response => response.json())
        .then(data => {
          const peliculaEncontrada = data.peliculas.find(pelicula => pelicula.nombre === nombreDecodificado);

    if (peliculaEncontrada) {
      // Si se encuentra la película, accede a sus datos
      const nombrePelicula = peliculaEncontrada.nombre;
      const sinopsisPelicula = peliculaEncontrada.sinopsis;
      const directorPelicula = peliculaEncontrada.director;
      const generoPelicula = peliculaEncontrada.categoria;
      const anioPelicula = peliculaEncontrada.anio;
      const valoracionPelicula = peliculaEncontrada.valoracion;
      const imagenPelicula = peliculaEncontrada.imagen;

      document.querySelector('.pelicula-imagen img').setAttribute('src',imagenPelicula);
      document.querySelector('.sinopsis-peli').textContent = sinopsisPelicula;
      document.querySelector('.director-peli').textContent = "Director: " + directorPelicula;
      document.querySelector('.anio-peli').textContent = "Año: " + anioPelicula;
      document.querySelector('.genero-peli').textContent = "Género: " + generoPelicula;
      document.querySelector('.valoracion-peli').textContent = "Valoración: " + valoracionPelicula;
      
     
      console.log(nombrePelicula, generoPelicula, anioPelicula, valoracionPelicula, imagenPelicula);
    } else {
      console.log('Película no encontrada');
    }
  })
  .catch(error => console.error('Error:', error));
});
