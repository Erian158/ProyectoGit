/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

/**
 *
 * @author Eryan
 */
public class Auditorio {
    private final String[] sesiones = {"10:00 AM", "3:00 PM"};
    private final String[][] inscritos = new String[2][30]; // 2 sesiones, 30 personas máx
    private final int[] contadores = {0, 0};

    public String[] getSesiones() {
        return sesiones;
    }

    public boolean inscribir(int sesion, String idSocio) {
        if (contadores[sesion] < 30) {
            inscritos[sesion][contadores[sesion]++] = idSocio;
            return true;
        }
        return false;
    }

    public String[] getInscritos(int sesion) {
        return inscritos[sesion];
    }

    public int getDisponibles(int sesion) {
        return 30 - contadores[sesion];
    }
}
