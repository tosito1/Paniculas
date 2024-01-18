 /* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */

async function enviarComentario() {
    var comentario = document.getElementById("comentarioInput").value;
    var valoracion = document.getElementById("valoracionInput").value;
    var id = document.querySelector('.pelicula-imagen').querySelector('img').alt;
    var nombrePeli = document.querySelector('.nombre-peli').innerHTML;

    let parametros = new URLSearchParams();
    parametros.append('comentario', comentario);
    parametros.append('valoracion', valoracion);
    parametros.append('peliculaId', id);
    fetch("/Paniculas/html/Peliculas/Pelicula/Comentarios", { 
        method: 'POST', body: parametros }).then(response => {
            if (response.ok) {
                window.location.reload();
                comentario = "";
                valoracion = "";
                return response.text();
            }
            throw new Error('Error en la solicitud');
        })
        .then(data => {
            comentario = "";
            console.log(data); // Hacer algo con la respuesta del servidor
        })
        .catch(error => {
            console.error('Hubo un problema con la solicitud:', error);
            alert(error);
        });
}

function eliminarComentario(comentarioId) {
    if (confirm('¿Estás seguro de que quieres eliminar este comentario?')) {
        fetch(`/Paniculas/html/Peliculas/Pelicula/Comentarios?idComentario=${comentarioId}`, {
                method: 'DELETE'
            })
            .then(response => {
                if (response.ok) {
                    window.location.reload();
                } else {
                    throw new Error('Error al eliminar el comentario');
                }
            })
            .catch(error => {
                console.error('Hubo un problema al eliminar el comentario:', error);
                alert('Hubo un problema al eliminar el comentario: ' + error.message);
            });
    } else {
        console.log('Eliminación del comentario cancelada');
        alert('Eliminación del comentario cancelada');
    }
}

