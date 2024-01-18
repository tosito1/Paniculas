/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */


document.addEventListener('DOMContentLoaded', function() {
    for(var i=0;i<document.querySelectorAll(".pelicula").length;i++) {
        contenedorImagen = document.querySelectorAll(".pelicula")[i];
        contenedorImagen.addEventListener('click', function(event) {
            expandirPelicula(event); // Llamar a la función expandirPelicula
        });
    }
});

function expandirPelicula(event) {
    var pelicula = event.currentTarget;

    pelicula.removeEventListener('click', expandirPelicula);

    // Encuentra los elementos dentro de la película expandida
    var imagen = pelicula.querySelector('.div-cartel');
    var nombre = pelicula.querySelector('.div-nombre');
    
    // Verifica si los elementos existen antes de interactuar con ellos
    if (imagen && nombre) {
        // Verifica si ya tiene la clase expandida
        var tieneExpandida = pelicula.classList.contains('pelicula-expandida');

        if (!tieneExpandida) {
            // Agregar clases solo si no tiene la clase expandida
            pelicula.classList.add('pelicula-expandida');
            imagen.classList.add('div-cartel-expandido');
            nombre.classList.add('div-nombre-expandido');
            nombre.childNodes[1].classList.add('nombre-peli-expandida');
            nombre.querySelector(".sinopsisPeli").classList.add('sinopsis-peli-expandida');
            nombre.querySelector(".directorPeli").classList.add('director-peli-expandida');
            nombre.querySelector(".anioPeli").classList.add('anio-peli-expandida');
            nombre.querySelector(".generoPeli").classList.add('genero-peli-expandida');
            nombre.querySelector(".valoracionPeli").classList.add('valoracion-peli-expandida');
            

            var enlace = document.createElement('a');
            enlace.classList.add('enlace-expandida');
            var url = "Pelicula/" + encodeURIComponent(nombre.querySelector(".nombrePeli").textContent);
            console.log(url);
            enlace.setAttribute('href', url);
            enlace.textContent = 'Comentar Pelicula';
            nombre.appendChild(enlace);

            // Agregar un manejador de eventos al botón de cerrar
            var cerrarBoton = document.createElement('button');
            cerrarBoton.classList.add('cerrar-expandida');
            cerrarBoton.innerHTML = 'X';

            cerrarBoton.addEventListener('click', function(event) {
                // Evita la propagación del evento al contenedor principal
                event.stopPropagation();

                // Eliminar clases agregadas y elementos creados dinámicamente
                pelicula.classList.remove('pelicula-expandida');
                imagen.classList.remove('div-cartel-expandido');
                nombre.classList.remove('div-nombre-expandido');
                cerrarBoton.remove();
                enlace.remove();

                // Volver a agregar el listener para expandir la película
                pelicula.addEventListener('click', expandirPelicula);
            });
            pelicula.appendChild(cerrarBoton);
        }
    }
}

