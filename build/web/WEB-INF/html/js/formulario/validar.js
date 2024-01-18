/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */

// Valida todos los campos obligatorios
document.querySelector('.form-submit').addEventListener('click', function(event) {
    const camposRequeridos = document.querySelectorAll('.obligatorio');
    
    camposRequeridos.forEach(function(campo) {
        valor = campo.value.trim();

        if (valor === '') {
            // Si el campo está vacío, muestra el mensaje de error en rojo.
            mensajeError = campo.nextElementSibling; // Obtiene el siguiente elemento (div.error-message).
            mensajeError.textContent = 'Este campo es obligatorio.';
            mensajeError.style.color = 'red';
            mensajeError.style.margin = '5px 10%';
            
            campo.style.border = '2px solid red';

            // Prevén el envío del formulario.
            event.preventDefault();
        } else {
            // Si el campo no está vacío, borra el mensaje de error.
            mensajeError = campo.nextElementSibling;
            mensajeError.textContent = '';
            mensajeError.style.color = '';
            mensajeError.style.margin = '';
            
            campo.style.border = '2px solid green';
        }
    });
});




//Validar contenido de la contraseña
//6 caracteres y uno de ellos un número
document.getElementById('password').addEventListener('change', function() {
    const clave = this.value;
    const regexNumero = /\d/;
//  selecciona el elemento label cuyo atributo for coincide con el valor "password"
    const labelClave = document.querySelector('.password-label'); 

    if (clave.length < 6 || !regexNumero.test(clave)) {
        this.style.border = '2px solid red';
        labelClave.style.color = 'red';
        labelClave.textContent = 'Clave (Debe contener al menos 6 caracteres, incluyendo un número) *';
    } else {
        this.style.border = ''; // Cambio del borde a verde
        labelClave.style.color = ''; // Cambio del color del texto del label a verde
        labelClave.textContent = 'Clave *';
    }
});


// Validar Repetir contraseña
document.getElementById('confirm_password').addEventListener('input', function() {
    const clave = document.getElementById('password').value;
    const repetirClave = this.value;
    const labelConfirmClave = document.querySelector('.password-repit-label');

    if (clave !== repetirClave) {
        this.style.border = '2px solid red';
        labelConfirmClave.style.color = 'red';
        labelConfirmClave.textContent = 'Repetir Clave (Las contraseñas no coinciden) *';
    } else {
        this.style.border = ''; 
        labelConfirmClave.style.color = ''; 
        labelConfirmClave.textContent = 'Repetir Clave *';
    }
});


// Validar el nombre
document.getElementById('nombre').addEventListener('input', function() {
    const valor = this.value.trim();
    const labelNombre = document.querySelector('.nombre-label');

    const regxNombre = /^[A-Za-z]+$/; // Expresión regular para solo letras.

    if (!regxNombre.test(valor) && valor !==''){
        labelNombre.textContent = 'Nombre * El nombre solo debe contener letras.';
        labelNombre.style.color = 'red';
        this.style.border = '2px solid red'; // Cambia el borde del input a rojo.
    } else {
        labelNombre.textContent = 'Nombre *';
        labelNombre.style.color = '';
        this.style.border = ''; // Restaura el borde al valor predeterminado.
    }
});

// Validar número de teléfono
document.getElementById('telefono').addEventListener('input', function() {
    const telefono = this.value;
    const regxTelefono = /^[0-9]{3}[0-9]{3}[0-9]{3}$/;
    const labelTelefono = document.querySelector('.telefono-label');

    if (!regxTelefono.test(telefono) && telefono !=='') {
        // El número de teléfono no tiene el formato correcto
        labelTelefono.textContent = 'Teléfono de Contacto (999999999) * El nombre solo debe contener números.';
        labelTelefono.style.color = 'red';
        this.style.border = '2px solid red'; // Cambia el borde del input a rojo.
    } else {
        // El número de teléfono tiene el formato correcto
        labelTelefono.textContent = 'Teléfono de Contacto (999999999) *';
        labelTelefono.style.color = '';
        this.style.border = ''; // Restaura el borde al valor predeterminado.
    }
});

// Validar correo electrónico
document.getElementById('email').addEventListener('input', function() {
    const email = this.value;
    const regxEmail = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    const labelCorreo = document.querySelector('.correo_label');

    if (!regxEmail.test(email) && email !== '') {
        // El correo electrónico no tiene el formato correcto
        labelCorreo.textContent = 'Correo Electrónico (example@gmail.com) * Formato incorrecto';
        labelCorreo.style.color = 'red';
        this.style.border = '2px solid red'; // Borde verde para indicar que es válido
    } else {
        // El correo electrónico tiene el formato correcto
        labelCorreo.textContent = 'Correo Electrónico (example@gmail.com) * ';
        labelCorreo.style.color = '';
        this.style.border = ''; // Restaura el borde al valor predeterminado.
    }
});

// Validar código postal
document.getElementById('codigo_postal').addEventListener('input', function() {
    const codigo = this.value;
    const regxCodigoPostal = /^[0-9]{2}[0-9]{3}$/;
    const labelCodigoPostal = document.querySelector('.codigo_postal-label');

    if (!regxCodigoPostal.test(codigo) && codigo !== '') {
        // El código postal no tiene el formato correcto
        labelCodigoPostal.textContent = 'Código Postal (99999) * Formato incorrecto';
        labelCodigoPostal.style.color = 'red';
        this.style.border = '2px solid red'; 
    } else {
        // El código postal tiene el formato correcto
        // Comprobar que coincide con alguna provincia
        provincia = document.getElementById('provincia');
        if(provincia.value !== '') {
            labelCodigoPostal.textContent = 'Código Postal (99999) *';
            labelCodigoPostal.style.color = '';
            this.style.border = ''; // Restaura el borde al valor predeterminado.
        } else {
            labelCodigoPostal.textContent = 'Código Postal (99.999) * No encontrado';
            labelCodigoPostal.style.color = 'red';
            this.style.border = '2px solid red'; 
        }
    }
});