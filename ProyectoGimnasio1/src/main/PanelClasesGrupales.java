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
public class PanelClasesGrupales extends JPanel {
      private GimnasioApp app;
    private ClaseGrupal[] clases = new ClaseGrupal[6]; // 6 clases predefinidas
    private int cantidadClases = 0;
    private JPanel panelLista;

    public PanelClasesGrupales(GimnasioApp app) {
        this.app = app;
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Clases Grupales", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        add(titulo, BorderLayout.NORTH);

        precargarClases();

        panelLista = new JPanel();
        panelLista.setLayout(new GridLayout(0, 1));
        JScrollPane scrollPane = new JScrollPane(panelLista);
        add(scrollPane, BorderLayout.CENTER);

        JButton btnAgregarClase = new JButton("Agregar Clase");
        btnAgregarClase.addActionListener(e -> agregarClase());

        JButton btnVolver = new JButton("Volver");
        btnVolver.addActionListener(e -> app.cambiarPanel(new PanelMenuPrincipal(app)));

        JPanel botones = new JPanel();
        botones.add(btnAgregarClase);
        botones.add(btnVolver);

        add(botones, BorderLayout.SOUTH);

        actualizarLista();
    }

    private void precargarClases() {
        clases[0] = new ClaseGrupal("Yoga", "8:00 AM", 10);
        clases[1] = new ClaseGrupal("Zumba", "9:00 AM", 15);
        clases[2] = new ClaseGrupal("Crossfit", "6:00 PM", 12);
        clases[3] = new ClaseGrupal("Pilates", "7:00 PM", 10);
        clases[4] = new ClaseGrupal("Funcional", "5:00 PM", 8);
        cantidadClases = 5;
    }

    private void actualizarLista() {
        panelLista.removeAll();

        for (int i = 0; i < cantidadClases; i++) {
            ClaseGrupal clase = clases[i];
            JButton btn = new JButton(clase.getNombre() + " - " + clase.getHorario() +
                    " (Cupos: " + clase.getCuposDisponibles() + ")");
            int finalI = i;

            btn.addActionListener(e -> opcionesClase(finalI));
            panelLista.add(btn);
        }

        panelLista.revalidate();
        panelLista.repaint();
    }

    private void opcionesClase(int indice) {
        ClaseGrupal clase = clases[indice];
        String[] opciones = {"Reservar cupo", "Modificar clase", "Ver inscritos"};
        int opcion = JOptionPane.showOptionDialog(this, "¿Qué desea hacer con esta clase?",
                clase.getNombre(), JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, opciones, opciones[0]);

        switch (opcion) {
            case 0 -> reservarCupo(clase);
            case 1 -> modificarClase(clase);
            case 2 -> verInscritos(clase);
        }

        actualizarLista();
    }

    private void reservarCupo(ClaseGrupal clase) {
        String id = JOptionPane.showInputDialog("Ingrese el ID del socio:");
        if (id != null && !id.isEmpty()) {
            boolean exito = clase.inscribir(id);
            if (exito) {
                JOptionPane.showMessageDialog(this, "Reserva realizada con éxito.");
            } else {
                JOptionPane.showMessageDialog(this, "Clase llena.");
            }
        }
    }

    private void modificarClase(ClaseGrupal clase) {
        String nuevoNombre = JOptionPane.showInputDialog("Nuevo nombre:", clase.getNombre());
        String nuevoHorario = JOptionPane.showInputDialog("Nuevo horario:", clase.getHorario());
        String nuevoCap = JOptionPane.showInputDialog("Nueva capacidad:", clase.getCapacidad());

        try {
            int nuevaCapacidad = Integer.parseInt(nuevoCap);
            clase.setNombre(nuevoNombre);
            clase.setHorario(nuevoHorario);
            clase.setCapacidad(nuevaCapacidad);
            JOptionPane.showMessageDialog(this, "Clase modificada.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error en los datos.");
        }
    }

    private void verInscritos(ClaseGrupal clase) {
        String[] inscritos = clase.getInscritos();
        StringBuilder sb = new StringBuilder("Inscritos:\n");
        for (String id : inscritos) {
            if (id != null) sb.append("- ").append(id).append("\n");
        }
        JOptionPane.showMessageDialog(this, sb.toString());
    }

    private void agregarClase() {
        if (cantidadClases >= clases.length) {
            JOptionPane.showMessageDialog(this, "No se pueden agregar más clases.");
            return;
        }

        String nombre = JOptionPane.showInputDialog("Nombre de la clase:");
        String horario = JOptionPane.showInputDialog("Horario:");
        String capStr = JOptionPane.showInputDialog("Capacidad:");

        try {
            int cap = Integer.parseInt(capStr);
            clases[cantidadClases++] = new ClaseGrupal(nombre, horario, cap);
            JOptionPane.showMessageDialog(this, "Clase agregada.");
            actualizarLista();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error en los datos.");
        }
    }
}
