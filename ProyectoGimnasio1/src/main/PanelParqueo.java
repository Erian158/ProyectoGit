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
public class PanelParqueo extends JPanel {
     private final Parqueo parqueo = new Parqueo();
    private final GimnasioApp app;

    public PanelParqueo(GimnasioApp app) {
        this.app = app;
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Visualización del Parqueo", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        add(titulo, BorderLayout.NORTH);

        JPanel contenedorNiveles = new JPanel(new GridLayout(1, 3, 10, 10));
        contenedorNiveles.add(crearPanelNivel(1));
        contenedorNiveles.add(crearPanelNivel(2));
        contenedorNiveles.add(crearPanelNivel(3));

        add(contenedorNiveles, BorderLayout.CENTER);

        JButton volver = new JButton("Volver al Menú");
        volver.addActionListener(e -> app.cambiarPanel(new PanelMenuPrincipal(app)));
        add(volver, BorderLayout.SOUTH);
    }

    private JPanel crearPanelNivel(int nivel) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Nivel G" + nivel));

        char[][] matriz = parqueo.getNivel(nivel);
        String[][] ids = parqueo.getNivelIds(nivel);

        JPanel grid = new JPanel(new GridLayout(matriz.length, matriz[0].length));

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                JButton btn = new JButton(String.valueOf(matriz[i][j]));
                int fila = i, columna = j;

                btn.setBackground(colorPorTipo(matriz[i][j]));
                btn.setOpaque(true);
                btn.setForeground(Color.WHITE);

                btn.addActionListener(e -> {
                    String idSocio = JOptionPane.showInputDialog("ID del socio:");
                    if (idSocio == null || idSocio.isEmpty()) return;

                    if (matriz[fila][columna] == 'L') {
                        parqueo.reservarEspacio(nivel, fila, columna, idSocio);
                    } else if (matriz[fila][columna] == 'O') {
                        int confirmar = JOptionPane.showConfirmDialog(this, "Liberar este espacio?");
                        if (confirmar == JOptionPane.YES_OPTION) {
                            parqueo.liberarEspacio(nivel, fila, columna);
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Espacio especial o ya reservado.");
                    }

                    // Actualiza botón
                    btn.setText(String.valueOf(matriz[fila][columna]));
                    btn.setBackground(colorPorTipo(matriz[fila][columna]));
                });

                grid.add(btn);
            }
        }

        panel.add(grid, BorderLayout.CENTER);
        return panel;
    }

    private Color colorPorTipo(char tipo) {
        return switch (tipo) {
            case 'L' -> Color.GREEN.darker();
            case 'O' -> Color.RED.darker();
            case 'E' -> Color.BLUE.darker();
            case 'D' -> Color.MAGENTA.darker();
            default -> Color.GRAY;
        };
    }
}
