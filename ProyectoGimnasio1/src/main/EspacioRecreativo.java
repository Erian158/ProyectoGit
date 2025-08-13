/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

/**
 *
 * @author Eryan
 */
public class EspacioRecreativo {
    private String nombre;
    private int cantidad;         // número de espacios disponibles (ej: 2 canchas)
    private int capacidadPorUnidad;
    private String[][] reservas; // [espacio][cupos]

    public EspacioRecreativo(String nombre, int cantidad, int capacidadPorUnidad) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.capacidadPorUnidad = capacidadPorUnidad;
        this.reservas = new String[cantidad][capacidadPorUnidad];
    }

    public String getNombre() {
        return nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public int getCapacidadPorUnidad() {
        return capacidadPorUnidad;
    }

    public boolean reservar(int unidad, String idSocio) {
        for (int i = 0; i < capacidadPorUnidad; i++) {
            if (reservas[unidad][i] == null) {
                reservas[unidad][i] = idSocio;
                return true;
            }
        }
        return false;
    }

    public boolean liberar(int unidad, String idSocio) {
        for (int i = 0; i < capacidadPorUnidad; i++) {
            if (idSociosIguales(reservas[unidad][i], idSocio)) {
                reservas[unidad][i] = null;
                return true;
            }
        }
        return false;
    }

    public String[] getReservasDeUnidad(int unidad) {
        return reservas[unidad];
    }

    private boolean idSociosIguales(String a, String b) {
        return a != null && a.equals(b);
    }
}
