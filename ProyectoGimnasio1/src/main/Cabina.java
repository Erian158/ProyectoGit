/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

/**
 *
 * @author Eryan
 */
public class Cabina {
    
 private String[] horarios = {
        "9:00 AM", "10:00 AM", "11:00 AM", "12:00 PM",
        "1:00 PM", "2:00 PM", "3:00 PM", "4:00 PM",
        "5:00 PM"
    };
    private String[] reservas; // ID del socio o null si libre

    public Cabina() {
        reservas = new String[horarios.length];
    }

    public String[] getHorarios() {
        return horarios;
    }

    public String[] getReservas() {
        return reservas;
    }

    public boolean reservarHora(int indice, String idSocio) {
        if (reservas[indice] == null) {
            reservas[indice] = idSocio;
            return true;
        }
        return false;
    }

    public boolean liberarHora(int indice) {
        if (reservas[indice] != null) {
            reservas[indice] = null;
            return true;
        }
        return false;
    }

    public String getSocio(int indice) {
        return reservas[indice];
    }

    public boolean estaReservado(int indice) {
        return reservas[indice] != null;
    }
}
