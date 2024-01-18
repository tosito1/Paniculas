/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */



document.addEventListener('DOMContentLoaded', function() {
    const divPeliculas = document.querySelector('.div-peliculas');
    const tabla = document.getElementById('tablaPeliculas');
    const tbody = tabla.getElementsByTagName('tbody')[0];

    fetch('./js/peliculas.json')
        .then(response => response.json())
        .then(data => {
            data.peliculas.forEach(pelicula => {
                const fila = document.createElement('tr');

                const imagenFila = document.createElement('td');
                const imagen = document.createElement('img');
                imagen.src = pelicula.imagen;
                imagen.alt = "Imagen de la película";
                imagenFila.classList.add('detallesArticulo');
                fila.appendChild(imagenFila);
                imagenFila.appendChild(imagen);

                const titulo = document.createElement('td');
                titulo.textContent = pelicula.nombre;
                titulo.classList.add('nombreArticulo');
                fila.appendChild(titulo);

                const genero = document.createElement('td');
                genero.textContent = pelicula.categoria;
                genero.classList.add('categoriaArticulo');
                fila.appendChild(genero);

                const detallesFila = document.createElement('td');
                const detalles = document.createElement('a');
                detalles.textContent = "Ver detalles";
                detalles.classList.add('detallesArticulo');
                var url = 'pelicula.html?nombre=' + encodeURIComponent(pelicula.nombre);
                detalles.setAttribute('href', url);
                fila.appendChild(detallesFila);
                detallesFila.appendChild(detalles);
                
                tbody.appendChild(fila);
              });
        })
        .catch(error => console.error('Error:', error));
});




    