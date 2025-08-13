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

public class PanelRecreacion extends JPanel {

    private final GimnasioApp app;
    private final EspacioRecreativo[] espacios = new EspacioRecreativo[5];

    public PanelRecreacion(GimnasioApp app) {
        this.app = app;
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Espacios Recreativos", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        add(titulo, BorderLayout.NORTH);

        precargarEspacios();

        JPanel panelLista = new JPanel(new GridLayout(espacios.length, 1, 10, 10));

        for (int i = 0; i < espacios.length; i++) {
            int index = i;
            JButton btn = new JButton(espacios[i].getNombre());
            btn.setFont(new Font("Arial", Font.PLAIN, 16));
            btn.addActionListener(e -> gestionarEspacio(index));
            panelLista.add(btn);
        }

        add(panelLista, BorderLayout.CENTER);

        JButton volver = new JButton("Volver al Menú");
        volver.addActionListener(e -> app.cambiarPanel(new PanelMenuPrincipal(app)));
        add(volver, BorderLayout.SOUTH);
    }

    private void precargarEspacios() {
        espacios[0] = new EspacioRecreativo("Ping-Pong", 2, 2);
        espacios[1] = new EspacioRecreativo("Billar", 2, 2);
        espacios[2] = new EspacioRecreativo("Fútbol", 2, 12);
        espacios[3] = new EspacioRecreativo("Baloncesto", 1, 10);
        espacios[4] = new EspacioRecreativo("Tenis", 2, 2);
    }

    private void gestionarEspacio(int index) {
        EspacioRecreativo espacio = espacios[index];
        String[] opciones = new String[espacio.getCantidad()];
        for (int i = 0; i < espacio.getCantidad(); i++) {
            opciones[i] = espacio.getNombre() + " #" + (i + 1);
        }

        int unidad = JOptionPane.showOptionDialog(this, "Seleccione espacio:",
                "Espacios de " + espacio.getNombre(),
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, opciones, opciones[0]);

        if (unidad < 0) return;

        String[] acciones = {"Reservar", "Liberar", "Ver Reservas"};
        int accion = JOptionPane.showOptionDialog(this, "¿Qué desea hacer?",
                "Espacio " + opciones[unidad],
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, acciones, acciones[0]);

        switch (accion) {
            case 0 -> reservar(espacio, unidad);
            case 1 -> liberar(espacio, unidad);
            case 2 -> verReservas(espacio, unidad);
        }
    }

    private void reservar(EspacioRecreativo espacio, int unidad) {
        String id = JOptionPane.showInputDialog("ID del socio:");
        if (id != null && !id.isEmpty()) {
            boolean exito = espacio.reservar(unidad, id);
            if (exito) {
                JOptionPane.showMessageDialog(this, "Reserva exitosa.");
            } else {
                JOptionPane.showMessageDialog(this, "Espacio lleno.");
            }
        }
    }

    private void liberar(EspacioRecreativo espacio, int unidad) {
        String id = JOptionPane.showInputDialog("ID del socio a liberar:");
        if (id != null && !id.isEmpty()) {
            boolean exito = espacio.liberar(unidad, id);
            if (exito) {
                JOptionPane.showMessageDialog(this, "Liberado correctamente.");
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró ese socio.");
            }
        }
    }

    private void verReservas(EspacioRecreativo espacio, int unidad) {
        StringBuilder sb = new StringBuilder("Socios en " + espacio.getNombre() + " #" + (unidad + 1) + ":\n");
        String[] reservas = espacio.getReservasDeUnidad(unidad);
        for (String s : reservas) {
            if (s != null) sb.append("- ").append(s).append("\n");
        }
        JOptionPane.showMessageDialog(this, sb.toString());
    }
}