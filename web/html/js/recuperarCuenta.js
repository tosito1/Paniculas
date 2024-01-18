/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


document.addEventListener('DOMContentLoaded', function() {
    const formulario = document.getElementById('recuperarForm');

    formulario.addEventListener('submit', function(event) {
        event.preventDefault(); // Evita que se envíe el formulario por defecto
        enviarDatos(event);
    });

    function enviarDatos(event) {
        const usuario = document.getElementById('usuario').value;

        let parametros = new URLSearchParams();
        parametros.append('usuario', usuario);

        fetch("/Paniculas/html/RecuperarCuenta", {
            method: 'POST',
            body: parametros
        }).then(response => {
            if (response.ok) {
                alert("Su solicitud de restablecimiento de contraseña ha sido enviada.");
                window.location.href = "..";
                return response.text();
            }
            throw new Error('Error en la solicitud');
        }).then(data => {
            document.getElementById('mensaje').textContent = data;
            alert(data);
        }).catch(error => {
            console.error('Error:', error);
            document.getElementById('mensaje').textContent = 'Ha ocurrido un error al enviar los datos';
            alert(error);
        });
    }
});
