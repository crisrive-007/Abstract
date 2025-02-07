package labsemana3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Tablero {

    public Sudoku_Logica sudoku = new Sudoku_Logica();

    public int[][][] casillas = {
        {
            {5, 3, 0, 0, 7, 0, 0, 0, 0},
            {6, 0, 0, 1, 9, 5, 0, 0, 0},
            {0, 9, 8, 0, 0, 0, 0, 6, 0},
            {8, 0, 0, 0, 6, 0, 0, 0, 3},
            {4, 0, 0, 8, 0, 3, 0, 0, 1},
            {7, 0, 0, 0, 2, 0, 0, 0, 6},
            {0, 6, 0, 0, 0, 0, 2, 8, 0},
            {0, 0, 0, 4, 1, 9, 0, 0, 5},
            {0, 0, 0, 0, 8, 0, 0, 7, 9}
        },
        {
            {0, 0, 5, 3, 0, 0, 0, 0, 0},
            {8, 0, 0, 0, 0, 0, 0, 2, 0},
            {0, 7, 0, 0, 1, 0, 5, 0, 0},
            {4, 0, 0, 0, 0, 5, 3, 0, 0},
            {0, 1, 0, 0, 7, 0, 0, 0, 6},
            {0, 0, 3, 2, 0, 0, 0, 8, 0},
            {0, 6, 0, 5, 0, 0, 0, 0, 9},
            {0, 0, 4, 0, 0, 0, 0, 3, 0},
            {0, 0, 0, 0, 0, 9, 7, 0, 0}
        },
        {
            {8, 0, 0, 4, 7, 3, 0, 0, 0},
            {0, 2, 6, 8, 5, 1, 0, 9, 0},
            {0, 0, 5, 0, 0, 0, 8, 0, 0},
            {0, 1, 3, 0, 0, 8, 4, 0, 0},
            {6, 0, 7, 3, 0, 2, 9, 0, 0},
            {0, 5, 0, 0, 9, 7, 6, 0, 8},
            {0, 6, 2, 7, 3, 0, 5, 0, 0},
            {0, 3, 0, 2, 0, 0, 7, 0, 6},
            {4, 0, 0, 6, 0, 0, 0, 2, 0}
        },
        {
            {0, 0, 7, 9, 0, 3, 0, 6, 8},
            {1, 0, 5, 0, 0, 0, 0, 4, 2},
            {8, 6, 0, 2, 0, 0, 1, 9, 7},
            {0, 0, 0, 0, 0, 5, 0, 2, 0},
            {0, 0, 0, 7, 0, 6, 0, 0, 1},
            {0, 2, 0, 8, 0, 1, 4, 0, 0},
            {9, 0, 0, 0, 0, 2, 0, 3, 0},
            {0, 7, 4, 0, 0, 8, 0, 0, 9},
            {3, 1, 2, 4, 7, 9, 0, 0, 5}
        },
        {
            {0, 0, 9, 3, 8, 0, 0, 0, 7},
            {0, 0, 0, 7, 6, 0, 0, 0, 0},
            {8, 2, 7, 0, 0, 0, 0, 0, 0},
            {0, 9, 0, 0, 0, 7, 8, 3, 2},
            {0, 0, 0, 0, 0, 4, 1, 9, 0},
            {2, 0, 8, 9, 3, 6, 7, 4, 5},
            {0, 0, 2, 1, 4, 5, 0, 0, 0},
            {1, 0, 0, 0, 0, 3, 0, 2, 9},
            {3, 7, 0, 2, 9, 0, 5, 0, 0}
        }

    };

    public int indexTablero = 0;
    public int[][] tablero;
    public JTextField[][] espacios = new JTextField[9][9];

    public Tablero() {
        tablero = casillas[indexTablero];

        JFrame pantalla = new JFrame("Sudoku");
        pantalla.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pantalla.setSize(500, 500);
        pantalla.setLayout(new BorderLayout());
        pantalla.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(9, 9));
        for (int fila = 0; fila < 9; fila++) {
            for (int columna = 0; columna < 9; columna++) {
                JTextField numero = new JTextField();
                numero.setHorizontalAlignment(JTextField.CENTER);
                numero.setFont(new Font("Segoe UI", Font.PLAIN, 18));

                boolean colorAlterno = ((fila / 3) + (columna / 3)) % 2 == 0;
                numero.setBackground(colorAlterno ? Color.decode("#c5e7e9") : Color.WHITE);

                if (tablero[fila][columna] != 0) {
                    numero.setText(String.valueOf(tablero[fila][columna]));
                    numero.setFont(new Font("Segoe UI", Font.BOLD, 18));
                    numero.setEditable(false);
                }

                numero.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

                espacios[fila][columna] = numero;
                panel.add(numero);
            }
        }
        pantalla.add(panel, BorderLayout.CENTER);

        JButton verificar = new JButton("Verificar solucion");
        verificar.addActionListener((ActionEvent e) -> {
            if (sudoku.solucionValida(espacios)) {
                JOptionPane.showMessageDialog(pantalla, "¡Sudoku completado correctamente!");
                sudoku.nuevoTablero(tablero, indexTablero, casillas, espacios);
            } else {
                JOptionPane.showMessageDialog(pantalla, "Hay errores en la solución.");
            }
        });
        pantalla.add(verificar, BorderLayout.SOUTH);

        JButton nueva_partida = new JButton("Nueva Partida");
        nueva_partida.addActionListener((ActionEvent e) -> {
            sudoku.nuevoTablero(tablero, indexTablero, casillas, espacios);
        });
        pantalla.add(nueva_partida, BorderLayout.NORTH);

        pantalla.setVisible(true);
    }

}
