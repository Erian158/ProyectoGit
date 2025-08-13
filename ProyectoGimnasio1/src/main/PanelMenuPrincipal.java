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

public class PanelMenuPrincipal extends JPanel {

    public PanelMenuPrincipal(GimnasioApp app) {
        setLayout(new GridLayout(4, 2, 10, 10));
        setBorder(BorderFactory.createTitledBorder("Menú Principal"));

        JButton btnParqueo = new JButton("Ver Parqueo");
        JButton btnClases = new JButton("Clases Grupales");
        JButton btnCabinas = new JButton("Cabinas Insonorizadas");
        JButton btnAuditorio = new JButton("Auditorio Fitness");
        JButton btnPesas = new JButton("Sala de Pesas");
        JButton btnRecreacion = new JButton("Espacios Recreativos");
        JButton btnReservas = new JButton("Ver Todas las Reservas");

        // Navegación entre paneles
        btnParqueo.addActionListener(e -> app.cambiarPanel(new PanelParqueo(app)));
        btnClases.addActionListener(e -> app.cambiarPanel(new PanelClasesGrupales(app)));
        btnCabinas.addActionListener(e -> app.cambiarPanel(new PanelCabinas(app)));
        btnAuditorio.addActionListener(e -> app.cambiarPanel(new PanelAuditorio(app)));
        btnPesas.addActionListener(e -> app.cambiarPanel(new PanelPesas(app)));
        btnRecreacion.addActionListener(e -> app.cambiarPanel(new PanelRecreacion(app)));
        btnReservas.addActionListener(e -> app.cambiarPanel(new PanelReservas(app))); // este lo agregamos al final

        add(btnParqueo);
        add(btnClases);
        add(btnCabinas);
        add(btnAuditorio);
        add(btnPesas);
        add(btnRecreacion);
        add(btnReservas);
    }
}