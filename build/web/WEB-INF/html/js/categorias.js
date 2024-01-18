/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */

document.addEventListener('DOMContentLoaded', function() {

    fetch('../js/peliculas.json')
        .then(response => response.json())
        .then(data => {
            data.peliculas.forEach(pelicula => {
                claseGenero = `.div-genero-${pelicula.categoria}`;
                claseDivGenero = document.querySelector(claseGenero).childNodes[3].childNodes[3];
                
                contenedorPelicula = document.createElement('div');
                contenedorPelicula.classList.add('div-pelicula');
                contenedorImagen = document.createElement('div');
                contenedorImagen.classList.add('div-cartel');

                imagenElement = document.createElement('img');
                imagenElement.classList.add('cartel');
                imagenElement.src = pelicula.imagen;
                imagenElement.alt = "Imagen de la película";

                contenedorImagen.appendChild(imagenElement);

                divContenido = document.createElement('div');
                titulo = document.createElement('p');
                titulo.textContent = pelicula.nombre;
                titulo.classList.add('nombrePelicula');
                divContenido.appendChild(titulo);  
                
                contenedorPelicula.appendChild(contenedorImagen);
                contenedorPelicula.appendChild(divContenido);
                claseDivGenero.appendChild(contenedorPelicula);
              });
        })
        .catch(error => console.error('Error:', error));
});
    

function moverPeliculas(lista, direccion) {
    lista = document.querySelector(lista);
    listaItems = lista.querySelectorAll('.div-pelicula');

    let scrollPos = lista.scrollLeft;
    const containerWidth = lista.offsetWidth;

    let i = 0;
    while (i < listaItems.length && (scrollPos + listaItems[i].getBoundingClientRect().width) <= containerWidth) {
        scrollPos += listaItems[i].getBoundingClientRect().width;
        i++;
    }

    if (direccion === 'izquierda') {
        if (lista.scrollLeft > 0) {
            lista.scrollLeft -= listaItems[i - 1].getBoundingClientRect().width;
        }
    } else {
        if (i < listaItems.length) {
            lista.scrollLeft += listaItems[i].getBoundingClientRect().width;
        }
    }
}
