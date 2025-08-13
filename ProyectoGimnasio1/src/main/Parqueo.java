/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

/**
 *
 * @author Eryan
 */
public class Parqueo {
    private char[][] G1 = new char[4][5]; // 4x5
    private char[][] G2 = new char[5][5]; // 5x5
    private char[][] G3 = new char[6][5]; // 6x5

    private String[][] G1Ids = new String[4][5];
    private String[][] G2Ids = new String[5][5];
    private String[][] G3Ids = new String[6][5];

    public Parqueo() {
        inicializarMatriz(G1, G1Ids);
        inicializarMatriz(G2, G2Ids);
        inicializarMatriz(G3, G3Ids);

        // Espacios reservados para discapacitados y entrenadores
        configurarEspaciosEspeciales(G1, 3);
        configurarEspaciosEspeciales(G2, 3);
        configurarEspaciosEspeciales(G3, 3);
    }

    private void inicializarMatriz(char[][] matriz, String[][] ids) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                matriz[i][j] = 'L';
                ids[i][j] = "";
            }
        }
    }

    private void configurarEspaciosEspeciales(char[][] matriz, int discapacitados) {
        int cols = matriz[0].length;

        // Asignar 'D' (Discapacitado) al final de la última fila
        int row = matriz.length - 1;
        for (int j = 0; j < discapacitados && j < cols; j++) {
            matriz[row][j] = 'D';
        }

        // Asignar 'E' (Entrenadores) al inicio de la primera fila
        for (int j = 0; j < 2 && j < cols; j++) {
            matriz[0][j] = 'E';
        }
    }

    // ===========================
    // MÉTODOS PÚBLICOS
    // ===========================

    public char[][] getNivel(int nivel) {
        return switch (nivel) {
            case 1 -> G1;
            case 2 -> G2;
            case 3 -> G3;
            default -> null;
        };
    }

    public String[][] getNivelIds(int nivel) {
        return switch (nivel) {
            case 1 -> G1Ids;
            case 2 -> G2Ids;
            case 3 -> G3Ids;
            default -> null;
        };
    }

    public boolean reservarEspacio(int nivel, int fila, int columna, String idSocio) {
        char[][] matriz = getNivel(nivel);
        String[][] ids = getNivelIds(nivel);

        if (matriz == null || fila >= matriz.length || columna >= matriz[0].length)
            return false;

        if (matriz[fila][columna] == 'L') {
            matriz[fila][columna] = 'O';
            ids[fila][columna] = idSocio;
            return true;
        }

        return false;
    }

    
    
    public boolean liberarEspacio(int nivel, int fila, int columna) {
        char[][] matriz = getNivel(nivel);
        String[][] ids = getNivelIds(nivel);

        if (matriz == null || fila >= matriz.length || columna >= matriz[0].length)
            return false;

        if (matriz[fila][columna] == 'O') {
            matriz[fila][columna] = 'L';
            ids[fila][columna] = "";
            return true;
        }

        return false;
    }

    public String obtenerIdSocio(int nivel, int fila, int columna) {
        String[][] ids = getNivelIds(nivel);
        return ids[fila][columna];
    }
}

