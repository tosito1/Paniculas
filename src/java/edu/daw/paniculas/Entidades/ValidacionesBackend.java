/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.daw.paniculas.Entidades;

/**
 *
 * @author anton
 */
public class ValidacionesBackend {
    public static boolean validarCampoObligatorio(String valor) {
        return !valor.trim().isEmpty();
    }

    public static boolean validarNombre(String nombre) {
        return nombre.matches("[A-Za-z0-9]+");
    }

    public static boolean validarClave(String clave) {
        return clave.length() >= 6 && clave.matches(".*\\d.*");
    }

    public static boolean validarTelefono(String telefono) {
        return telefono.matches("[0-9]{9}"); // Cambia esta expresión regular según el formato de teléfono requerido
    }

    public static boolean validarCorreoElectronico(String correo) {
        return correo.matches("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$");
    }

    public static boolean validarCodigoPostal(String codigoPostal) {
        return codigoPostal.matches("[0-9]{5}"); // Cambia esta expresión regular según el formato de código postal requerido
    }
    
    public static boolean validarValoracion(String valoracion) {
        try {
            double valor = Double.parseDouble(valoracion);
            return valor >= 0 && valor <= 10;
        } catch (NumberFormatException e) {
            return false; // En caso de que el valor no sea un número válido
        }
    }
}
