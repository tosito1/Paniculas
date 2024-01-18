/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */


function validar(form) {
    event.preventDefault();
    var username = form.elements["username"].value.trim();
    var password = form.elements["password"].value;

    const regexNumero = /\d/;
    const regxNombre = /^[A-Za-z0-9]+$/;;

    const labelNombre = document.querySelector('.nombre-label');
    const labelClave = document.querySelector('.password-label');

    if (!regxNombre.test(username) && username !== '') {
        labelNombre.textContent = 'Usuario * El usuario solo debe contener letras.';
        labelNombre.style.color = 'red';
        form.elements["username"].style.border = '2px solid red';
        return false;
    } else {
        labelNombre.textContent = 'Usuario ';
        labelNombre.style.color = '';
        form.elements["username"].style.border = '';
    }

    if (password.length < 6 || !regexNumero.test(password)) {
        labelClave.textContent = 'Contraseña * (Debe contener al menos 6 caracteres, incluyendo un número)';
        labelClave.style.color = 'red';
        form.elements["password"].style.border = '2px solid red';
        return false;
    } else {
        labelClave.textContent = 'Contraseña ';
        labelClave.style.color = '';
        form.elements["password"].style.border = '';
    }

    let parametros = new URLSearchParams();
    parametros.append('password', password);
    parametros.append('username', username);
    fetch("/Paniculas/html/VerificarCredenciales", { 
        method: 'POST', 
        body: parametros 
    })
    .then(response => {
        if (response.ok) {
            window.location.href = window.location.href;
            return response.text();
        } else {
            throw new Error('El usuario no existe');
        }
    })
    .then(data => {
        // Hacer algo con la respuesta del servidor
        console.log("Respuesta del servidor:", data);
        alert("Respuesta del servidor: " + data); // Mostrar la respuesta en una alerta
    })
    .catch(error => {
        console.error('Hubo un problema con la solicitud:', error);
        alert('Hubo un problema con la solicitud: ' + error.message); // Mostrar error en una alerta
    });
    return false; // Evitar el envío del formulario

    }
    
function mostrarPassword() {
    var passwordField = document.getElementById("password");
    if (passwordField.type === "password") {
        passwordField.type = "text";
    } else {
        passwordField.type = "password";
    }
}
