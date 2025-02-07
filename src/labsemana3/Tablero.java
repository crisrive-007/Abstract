package labsemana3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Tablero {

    private final int[][][] boards = {
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
        }
        
    };
    
    private int currentBoardIndex = 0; 
    private int[][] board; 
    private final JTextField[][] fields = new JTextField[9][9];

    public Tablero() {
        board = boards[currentBoardIndex]; 

        JFrame frame = new JFrame("Sudoku");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(9, 9));
        for (int fila = 0; fila < 9; fila++) {
            for (int columna = 0; columna < 9; columna++) {
                JTextField field = new JTextField();
                field.setHorizontalAlignment(JTextField.CENTER);
                field.setFont(new Font("Segoe UI", Font.PLAIN, 18));

                boolean colorAlterno = ((fila / 3) + (columna / 3)) % 2 == 0;
                field.setBackground(colorAlterno ? Color.decode("#c5e7e9") : Color.WHITE);

                if (board[fila][columna] != 0) {
                    field.setText(String.valueOf(board[fila][columna]));
                    field.setFont(new Font("Segoe UI", Font.BOLD, 18));
                    field.setEditable(false);
                }

                fields[fila][columna] = field;
                panel.add(field);
            }
        }
        frame.add(panel, BorderLayout.CENTER);

        JButton checkButton = new JButton("Check Solution");
        checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (solucionValida()) {
                    JOptionPane.showMessageDialog(frame, "¡Sudoku completado correctamente!");
                    loadNextBoard(); 
                } else {
                    JOptionPane.showMessageDialog(frame, "Hay errores en la solución.");
                }
            }
        });
        frame.add(checkButton, BorderLayout.SOUTH);

        JButton newGameButton = new JButton("Nueva Partida");
        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadNextBoard(); 
            }
        });
        frame.add(newGameButton, BorderLayout.NORTH);

        frame.setVisible(true);
    }

    private boolean solucionValida() {
        int[][] tempBoard = new int[9][9];
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                String text = fields[row][col].getText();
                if (text.isEmpty() || !text.matches("[1-9]")) {
                    return false;
                }
                tempBoard[row][col] = Integer.parseInt(text);
            }
        }
        return isValidSudoku(tempBoard);
    }

    private boolean isValidSudoku(int[][] board) {
        for (int i = 0; i < 9; i++) {
            if (!isValidGroup(board, i, true) || !isValidGroup(board, i, false) || !isValidBox(board, i)) {
                return false;
            }
        }
        return true;
    }

    private boolean isValidGroup(int[][] board, int index, boolean isRow) {
        boolean[] seen = new boolean[9];
        for (int i = 0; i < 9; i++) {
            int num = isRow ? board[index][i] : board[i][index];
            if (num < 1 || num > 9 || seen[num - 1]) {
                return false;
            }
            seen[num - 1] = true;
        }
        return true;
    }

    private boolean isValidBox(int[][] board, int box) {
        boolean[] seen = new boolean[9];
        int rowStart = (box / 3) * 3;
        int colStart = (box % 3) * 3;
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                int num = board[rowStart + r][colStart + c];
                if (num < 1 || num > 9 || seen[num - 1]) {
                    return false;
                }
                seen[num - 1] = true;
            }
        }
        return true;
    }

    
    private void loadNextBoard() {
        currentBoardIndex = (currentBoardIndex + 1) % boards.length; // Cambiar el índice del tablero
        board = boards[currentBoardIndex]; // Cargar el nuevo tablero

        //actualizar jtexts
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                JTextField field = fields[row][col];
                if (board[row][col] != 0) {
                    field.setText(String.valueOf(board[row][col]));
                    field.setEditable(false);
                } else {
                    field.setText("");
                    field.setEditable(true);
                }
            }
        }
    }
}
