/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */

// Asegura que todos los elementos HTML estén disponibles para interactuar
document.addEventListener('DOMContentLoaded', function() { 
    const table = document.getElementById('cuerpoTabla');

    table.addEventListener('click', function(event) {
        // Verifica si el elemento clickeado tiene la clase categoriaArticulo. 
        if (event.target.classList.contains('nombreArticulo')) {
            // Obtiene el elemento padre del elemento clickeado. (tr)
            const row = event.target.parentNode;
            
            const rojo = 'rgba(255, 0, 0, 0.8)';
            const negro = 'rgba(10, 10, 10, 0.95)';
            
            if (row.style.backgroundColor === rojo) {
                row.style.backgroundColor = negro;
                row.style.color = 'white';
            } else if (row.style.backgroundColor === negro) {
                row.style.backgroundColor = 'white';
                row.style.color = 'black';
            } else {
                row.style.backgroundColor = rojo;
                row.style.color = 'white';
            }
        }
    });
});
