/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

/**
 *
 * @author Eryan
 */
public class ClaseGrupal {
    private String nombre;
    private String horario; // Ejemplo: "8:00 AM", "6:00 PM"
    private int capacidad;
    private String[] inscritos;
    private int contadorInscritos;

    public ClaseGrupal(String nombre, String horario, int capacidad) {
        this.nombre = nombre;
        this.horario = horario;
        this.capacidad = capacidad;
        this.inscritos = new String[capacidad];
        this.contadorInscritos = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public String getHorario() {
        return horario;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public int getCuposDisponibles() {
        return capacidad - contadorInscritos;
    }

    public String[] getInscritos() {
        return inscritos;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public void setCapacidad(int nuevaCapacidad) {
        String[] nuevosInscritos = new String[nuevaCapacidad];
        for (int i = 0; i < Math.min(nuevaCapacidad, contadorInscritos); i++) {
            nuevosInscritos[i] = inscritos[i];
        }
        inscritos = nuevosInscritos;
        capacidad = nuevaCapacidad;
        if (contadorInscritos > nuevaCapacidad) {
            contadorInscritos = nuevaCapacidad;
        }
    }

    public boolean inscribir(String idSocio) {
        if (contadorInscritos < capacidad) {
            inscritos[contadorInscritos++] = idSocio;
            return true;
        }
        return false;
    }
}
