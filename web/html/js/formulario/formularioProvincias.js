/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */


document.getElementById('codigo_postal').addEventListener('change', function() {
    const codigoPostal = this.value.substring(0, 2);
    provinciaSelect = document.getElementById('provincia');

    for (let i = 0; i < provinciaSelect.options.length; i++) {
        if (provinciaSelect.options[i].value.substring(0, 2) === codigoPostal) {
            provinciaSelect.selectedIndex = i;
            break;
        }
    }
});
