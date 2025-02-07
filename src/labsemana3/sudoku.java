/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package labsemana3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 *
 * @author river
 */
public class sudoku {

    private final int[][] board = {
        {5, 3, 0, 0, 7, 0, 0, 0, 0},
        {6, 0, 0, 1, 9, 5, 0, 0, 0},
        {0, 9, 8, 0, 0, 0, 0, 6, 0},
        {8, 0, 0, 0, 6, 0, 0, 0, 3},
        {4, 0, 0, 8, 0, 3, 0, 0, 1},
        {7, 0, 0, 0, 2, 0, 0, 0, 6},
        {0, 6, 0, 0, 0, 0, 2, 8, 0},
        {0, 0, 0, 4, 1, 9, 0, 0, 5},
        {0, 0, 0, 0, 8, 0, 0, 7, 9}
    };
    private final JTextField[][] fields = new JTextField[9][9];

    public sudoku() {
        JFrame frame = new JFrame("Sudoku");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(9, 9));
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                JTextField field = new JTextField();
                field.setHorizontalAlignment(JTextField.CENTER);
                if (board[row][col] != 0) {
                    field.setText(String.valueOf(board[row][col]));
                    field.setEditable(false);
                    field.setBackground(Color.LIGHT_GRAY);
                }
                fields[row][col] = field;
                panel.add(field);
            }
        }
        frame.add(panel, BorderLayout.CENTER);

        JButton checkButton = new JButton("Check Solution");
        checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isValidSolution()) {
                    JOptionPane.showMessageDialog(frame, "¡Sudoku completado correctamente!");
                } else {
                    JOptionPane.showMessageDialog(frame, "Hay errores en la solución.");
                }
            }
        });
        frame.add(checkButton, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
    
    private boolean isValidSolution() {
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
}
