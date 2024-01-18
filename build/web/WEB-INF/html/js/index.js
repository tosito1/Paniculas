/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */


document.addEventListener('DOMContentLoaded', function() {
    const divPeliculas = document.querySelector('.div-peliculas');

    fetch('../js/peliculas.json')
        .then(response => response.json())
        .then(data => {
            data.peliculas.forEach(pelicula => {
                const peliculaInfo = document.createElement('div');
                peliculaInfo.classList.add('pelicula');

                const peliculaImagen = document.createElement('div');
                peliculaImagen.classList.add('div-cartel');

                const imagenElement = document.createElement('img');
                imagenElement.classList.add('cartel');
                imagenElement.src = pelicula.imagen;
                imagenElement.alt = "Imagen de la película";

                peliculaImagen.appendChild(imagenElement);

                const peliculaDetalles = document.createElement('div');
                peliculaDetalles.classList.add('div-nombre');

                const tituloElement = document.createElement('h1');
                tituloElement.textContent = pelicula.nombre;
                tituloElement.classList.add('nombrePeli');
                peliculaDetalles.appendChild(tituloElement);
                
                const sinopsis = document.createElement('p');
                sinopsis.textContent = pelicula.sinopsis;
                sinopsis.classList.add('sinopsisPeli');
                peliculaDetalles.appendChild(sinopsis);

                const director = document.createElement('p');
                director.textContent = pelicula.director;
                director.classList.add('directorPeli');
                peliculaDetalles.appendChild(director);
                
                const anio = document.createElement('p');
                anio.textContent = pelicula.anio;
                anio.classList.add('anioPeli');
                peliculaDetalles.appendChild(anio);
                
                const genero = document.createElement('p');
                genero.textContent = pelicula.categoria;
                genero.classList.add('generoPeli');
                peliculaDetalles.appendChild(genero);

                const valoracion = document.createElement('p');
                valoracion.textContent = pelicula.valoracion;
                valoracion.classList.add('valoracionPeli');
                peliculaDetalles.appendChild(valoracion);

                peliculaInfo.appendChild(peliculaImagen);
                peliculaInfo.appendChild(peliculaDetalles);

                peliculaInfo.addEventListener('click', function(event) {
                    expandirPelicula(event); // Llamar a la función expandirPelicula
                });

                divPeliculas.appendChild(peliculaInfo);
            });
        })
        .catch(error => console.error('Error:', error));
});
