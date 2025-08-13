/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

/**
 *
 * @author Eryan
 */
public class Socio {
    private String id;
    private String nombre;
    private boolean activo;

    public Socio(String id, String nombre, boolean activo) {
        this.id = id;
        this.nombre = nombre;
        this.activo = activo;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}

