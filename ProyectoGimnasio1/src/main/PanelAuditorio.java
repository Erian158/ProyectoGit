/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;
import javax.swing.*;
import java.awt.*;
/**
 *
 * @author Eryan
 */
public class PanelAuditorio extends JPanel {

    private final Auditorio auditorio = new Auditorio();
    private final GimnasioApp app;

    public PanelAuditorio(GimnasioApp app) {
        this.app = app;
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Auditorio Fitness - Charlas", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        add(titulo, BorderLayout.NORTH);

        JPanel panelSesiones = new JPanel(new GridLayout(2, 1, 10, 10));
        String[] horarios = auditorio.getSesiones();

        for (int i = 0; i < horarios.length; i++) {
            int sesion = i;
            JButton btn = new JButton(horarios[i] + " (Disponibles: " + auditorio.getDisponibles(i) + ")");
            btn.setFont(new Font("Arial", Font.PLAIN, 16));
            btn.setBackground(Color.CYAN.darker());
            btn.setOpaque(true);
            btn.setForeground(Color.BLACK);

            btn.addActionListener(e -> mostrarOpciones(sesion));

            panelSesiones.add(btn);
        }

        add(panelSesiones, BorderLayout.CENTER);

        JButton volver = new JButton("Volver al Menú");
        volver.addActionListener(e -> app.cambiarPanel(new PanelMenuPrincipal(app)));
        add(volver, BorderLayout.SOUTH);
    }

    private void mostrarOpciones(int sesion) {
        String[] opciones = {"Inscribir socio", "Ver inscritos"};
        int opcion = JOptionPane.showOptionDialog(this, "Sesión: " + (sesion == 0 ? "10:00 AM" : "3:00 PM"),
                "Opciones", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, opciones, opciones[0]);

        switch (opcion) {
            case 0 -> inscribirSocio(sesion);
            case 1 -> verInscritos(sesion);
        }

        // Recarga panel
        removeAll();
        add(new PanelAuditorio(app));
        revalidate();
        repaint();
    }

    private void inscribirSocio(int sesion) {
        String id = JOptionPane.showInputDialog("Ingrese ID del socio:");
        if (id != null && !id.isEmpty()) {
            if (auditorio.inscribir(sesion, id)) {
                JOptionPane.showMessageDialog(this, "Inscripción exitosa.");
            } else {
                JOptionPane.showMessageDialog(this, "Sesión llena.");
            }
        }
    }

    private void verInscritos(int sesion) {
        String[] inscritos = auditorio.getInscritos(sesion);
        StringBuilder sb = new StringBuilder("Inscritos en la sesión:\n");
        for (String id : inscritos) {
            if (id != null) sb.append("- ").append(id).append("\n");
        }
        JOptionPane.showMessageDialog(this, sb.toString());
    }
}