/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

/**
 *
 * @author Eryan
 */
public class SalaPesas {
    private final int capacidadMaxima = 50;
    private final String[] sociosDentro = new String[capacidadMaxima];
    private int contador = 0;

    public boolean ingresar(String idSocio) {
        if (contador >= capacidadMaxima) return false;

        for (int i = 0; i < capacidadMaxima; i++) {
            if (sociosDentro[i] == null) {
                sociosDentro[i] = idSocio;
                contador++;
                return true;
            }
        }
        return false;
    }

    public boolean salir(String idSocio) {
        for (int i = 0; i < capacidadMaxima; i++) {
            if (idSociosIguales(sociosDentro[i], idSocio)) {
                sociosDentro[i] = null;
                contador--;
                return true;
            }
        }
        return false;
    }

    public int getOcupacionActual() {
        return contador;
    }

    public String[] getSociosDentro() {
        return sociosDentro;
    }

    private boolean idSociosIguales(String a, String b) {
        return a != null && a.equals(b);
    }
}
