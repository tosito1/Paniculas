/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */


document.getElementById("login-form").addEventListener("submit", function (e) {
    e.preventDefault(); // Evita que se recargue la página

    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;

    fetch('../js/users.json')
            .then(response => response.json())
            .then(data => {
                data.Users.forEach(user => {
        if (username === user.name && password === user.password) {
            alert("Inicio de sesión exitoso");
            url = "index.html?usuario=" + user.name;
            window.location.href = url;
        } else {
            alert("Credenciales incorrectas");
        }
        });
    })
});
