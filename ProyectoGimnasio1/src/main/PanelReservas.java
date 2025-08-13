/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

/**
 *
 * @author Eryan
 */
import javax.swing.*;
import java.awt.*;

public class PanelReservas extends JPanel {

    private final GimnasioApp app;

    public PanelReservas(GimnasioApp app) {
        this.app = app;
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Resumen de Reservas", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        add(titulo, BorderLayout.NORTH);

        JTextArea resumen = new JTextArea();
        resumen.setEditable(false);
        resumen.setFont(new Font("Monospaced", Font.PLAIN, 14));
        resumen.setText(generarResumenFicticio()); // Reemplaza luego con datos reales

        JScrollPane scroll = new JScrollPane(resumen);
        add(scroll, BorderLayout.CENTER);

        JButton volver = new JButton("Volver al Menú");
        volver.addActionListener(e -> app.cambiarPanel(new PanelMenuPrincipal(app)));
        add(volver, BorderLayout.SOUTH);
    }

    private String generarResumenFicticio() {
        // 🔧 Aquí debes conectar con los objetos reales y mostrar info real
        StringBuilder sb = new StringBuilder();

        sb.append("🏎️ Parqueo:\n");
        sb.append("Nivel G1: 3 ocupados\n");
        sb.append("Nivel G2: 5 ocupados\n\n");

        sb.append("🧘 Clases Grupales:\n");
        sb.append("Zumba: 12 inscritos\n");
        sb.append("Yoga: 10 inscritos\n\n");

        sb.append("🔇 Cabinas:\n");
        sb.append("Cabina 1: 3 turnos ocupados\n\n");

        sb.append("🏛️ Auditorio:\n");
        sb.append("10 AM: 15 inscritos\n");
        sb.append("3 PM: 20 inscritos\n\n");

        sb.append("🏋️ Sala de pesas:\n");
        sb.append("Actualmente: 25 personas\n\n");

        sb.append("🏀 Recreación:\n");
        sb.append("Fútbol cancha 1: 10 jugadores\n");
        sb.append("Ping-Pong mesa 2: 2 jugadores\n");

        return sb.toString();
    }
   
  
}