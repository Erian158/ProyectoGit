/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package main;

import javax.swing.*;

public class GimnasioApp extends JFrame {

    // 🔧 Variables globales del sistema
    public Parqueo parqueo = new Parqueo();
    public ClaseGrupal[] clases = new ClaseGrupal[6];
    public Cabina[] cabinas = new Cabina[3];
    public Auditorio auditorio = new Auditorio();
    public SalaPesas salaPesas = new SalaPesas();
    public EspacioRecreativo[] espaciosRecreativos = new EspacioRecreativo[5];
    
    public Socio[] socios = {
    new Socio("S001", "Carlos Gómez", true),
    new Socio("S002", "Ana Rodríguez", true),
    new Socio("S003", "Luis Pérez", false), // este tiene membresía inactiva
    new Socio("S004", "María Vargas", true),
    new Socio("S005", "José Salas", true)
};

    public GimnasioApp() {
        setTitle("Sistema de Gestión - Gimnasio Corporativo");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Inicia en el menú principal
        setContentPane(new PanelMenuPrincipal(this));

    }

    public void cambiarPanel(JPanel nuevoPanel) {
        setContentPane(nuevoPanel);
        revalidate();
        repaint();
    }

public Socio buscarSocioPorId(String id) {
    for (Socio s : socios) {
        if (s.getId().equalsIgnoreCase(id)) {
            return s;
        }
    }
    return null;
}

public boolean socioActivo(String id) {
    Socio s = buscarSocioPorId(id);
    return s != null && s.isActivo();
}

}
