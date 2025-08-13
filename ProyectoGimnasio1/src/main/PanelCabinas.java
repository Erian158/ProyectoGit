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
public class PanelCabinas extends JPanel {
     private GimnasioApp app;
    private Cabina[] cabinas = new Cabina[3]; // 3 cabinas disponibles
    private JPanel panelCabinas;

    public PanelCabinas(GimnasioApp app) {
        this.app = app;
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Reserva de Cabinas Insonorizadas", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        add(titulo, BorderLayout.NORTH);

        for (int i = 0; i < cabinas.length; i++) {
            cabinas[i] = new Cabina();
        }

        panelCabinas = new JPanel(new GridLayout(1, cabinas.length));
        actualizarPanel();
        add(panelCabinas, BorderLayout.CENTER);

        JButton volver = new JButton("Volver al Menú");
        volver.addActionListener(e -> app.cambiarPanel(new PanelMenuPrincipal(app)));
        add(volver, BorderLayout.SOUTH);
    }

    private void actualizarPanel() {
        panelCabinas.removeAll();

        for (int i = 0; i < cabinas.length; i++) {
            Cabina cabina = cabinas[i];
            JPanel panel = new JPanel(new GridLayout(cabina.getHorarios().length + 1, 1));
            panel.setBorder(BorderFactory.createTitledBorder("Cabina " + (i + 1)));

            String[] horarios = cabina.getHorarios();
            String[] reservas = cabina.getReservas();

            for (int j = 0; j < horarios.length; j++) {
                JButton btn = new JButton(horarios[j]);
                int cabinaIndex = i, horaIndex = j;

                if (cabina.estaReservado(j)) {
                    btn.setBackground(Color.RED);
                    btn.setText(horarios[j] + " - Ocupado");
                } else {
                    btn.setBackground(Color.GREEN.darker());
                    btn.setText(horarios[j] + " - Libre");
                }

                btn.setForeground(Color.WHITE);
                btn.setOpaque(true);

                btn.addActionListener(e -> {
                    if (cabina.estaReservado(horaIndex)) {
                        int opcion = JOptionPane.showConfirmDialog(this,
                                "¿Liberar esta hora?\nSocio: " + cabina.getSocio(horaIndex),
                                "Liberar turno", JOptionPane.YES_NO_OPTION);
                        if (opcion == JOptionPane.YES_OPTION) {
                            cabina.liberarHora(horaIndex);
                        }
                    } else {
                        String id = JOptionPane.showInputDialog("Ingrese ID del socio:");
                        if (id != null && !id.isEmpty()) {
                            if (!cabina.reservarHora(horaIndex, id)) {
                                JOptionPane.showMessageDialog(this, "Turno ya reservado.");
                            }
                        }
                    }
                    actualizarPanel();
                });

                panel.add(btn);
            }

            panelCabinas.add(panel);
        }

        panelCabinas.revalidate();
        panelCabinas.repaint();
    }
}
