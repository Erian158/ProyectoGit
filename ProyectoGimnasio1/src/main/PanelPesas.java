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
public class PanelPesas extends JPanel {

    private final GimnasioApp app;
    private final JLabel lblOcupacion;

    public PanelPesas(GimnasioApp app) {
        this.app = app;
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Sala de Pesas", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        add(titulo, BorderLayout.NORTH);

        lblOcupacion = new JLabel("Personas dentro: 0", SwingConstants.CENTER);
        lblOcupacion.setFont(new Font("Arial", Font.PLAIN, 16));
        add(lblOcupacion, BorderLayout.CENTER);

        JPanel botones = new JPanel();

        JButton btnEntrada = new JButton("Registrar Entrada");
        JButton btnSalida = new JButton("Registrar Salida");
        JButton btnVerSocios = new JButton("Ver Socios dentro");
        JButton btnVolver = new JButton("Volver");

        btnEntrada.addActionListener(e -> registrarEntrada());
        btnSalida.addActionListener(e -> registrarSalida());
        btnVerSocios.addActionListener(e -> mostrarSocios());
        btnVolver.addActionListener(e -> app.cambiarPanel(new PanelMenuPrincipal(app)));

        botones.add(btnEntrada);
        botones.add(btnSalida);
        botones.add(btnVerSocios);
        botones.add(btnVolver);

        add(botones, BorderLayout.SOUTH);

        actualizarOcupacion();
    }

    private void registrarEntrada() {
        String id = JOptionPane.showInputDialog("Ingrese ID del socio:");
        if (id == null || id.isEmpty()) return;

        Socio socio = app.buscarSocioPorId(id);

        if (socio == null) {
            JOptionPane.showMessageDialog(this, "❌ Socio no encontrado.");
            return;
        }

        if (!socio.isActivo()) {
            JOptionPane.showMessageDialog(this, "⚠️ Membresía inactiva. No puede ingresar.");
            return;
        }

        boolean exito = app.salaPesas.ingresar(id);
        if (exito) {
            JOptionPane.showMessageDialog(this, "✔️ Ingreso registrado para " + socio.getNombre());
        } else {
            JOptionPane.showMessageDialog(this, "⚠️ Capacidad máxima alcanzada.");
        }

        actualizarOcupacion();
    }

    private void registrarSalida() {
        String id = JOptionPane.showInputDialog("Ingrese ID del socio:");
        if (id == null || id.isEmpty()) return;

        boolean exito = app.salaPesas.salir(id);
        if (exito) {
            JOptionPane.showMessageDialog(this, "✔️ Salida registrada.");
        } else {
            JOptionPane.showMessageDialog(this, "❌ El socio no se encuentra dentro.");
        }

        actualizarOcupacion();
    }

    private void mostrarSocios() {
        String[] ocupantes = app.salaPesas.getOcupantes();
        StringBuilder sb = new StringBuilder("👥 Socios actualmente en la sala:\n\n");

        for (String idSocio : ocupantes) {
            if (idSocio != null) {
                Socio socio = app.buscarSocioPorId(idSocio);
                if (socio != null) {
                    sb.append("- ").append(socio.getNombre()).append(" (").append(idSocio).append(")\n");
                } else {
                    sb.append("- ").append(idSocio).append(" (no registrado)\n");
                }
            }
        }

        JOptionPane.showMessageDialog(this, sb.toString());
    }

    private void actualizarOcupacion() {
        int cantidad = app.salaPesas.getOcupacionActual();
        lblOcupacion.setText("Personas dentro: " + cantidad);
    }
}