/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package labsemana3;

import java.awt.Font;
import javax.swing.JTextField;

/**
 *
 * @author river
 */
public class Sudoku_Logica extends Sudoku{

    private int[][] tablero;

    public Sudoku_Logica() {
    }

    @Override
    public boolean solucionValida(JTextField[][] espacios) {
        int[][] tempBoard = new int[9][9];
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                String text = espacios[row][col].getText();
                if (text.isEmpty() || !text.matches("[1-9]")) {
                    return false;
                }
                tempBoard[row][col] = Integer.parseInt(text);
            }
        }
        return esValidSudoku(tempBoard);
    }

    @Override
    public boolean esValidSudoku(int[][] board) {
        for (int i = 0; i < 9; i++) {
            if (!esValidGrupo(board, i, true) || !esValidGrupo(board, i, false) || !cajaValida(board, i)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean esValidGrupo(int[][] board, int index, boolean isRow) {
        boolean[] visto = new boolean[9];
        for (int i = 0; i < 9; i++) {
            int num = isRow ? board[index][i] : board[i][index];
            if (num < 1 || num > 9 || visto[num - 1]) {
                return false;
            }
            visto[num - 1] = true;
        }
        return true;
    }

    @Override
    public boolean cajaValida(int[][] board, int box) {
        boolean[] visto = new boolean[9];
        int rowStart = (box / 3) * 3;
        int colStart = (box % 3) * 3;
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                int num = board[rowStart + r][colStart + c];
                if (num < 1 || num > 9 || visto[num - 1]) {
                    return false;
                }
                visto[num - 1] = true;
            }
        }
        return true;
    }

    @Override
    public void nuevoTablero(int[][] tab, int indexTablero, int[][][] casillas, JTextField[][] espacios) {
        indexTablero = (indexTablero + 1) % casillas.length;
        tablero = casillas[indexTablero];

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                JTextField field = espacios[row][col];
                field.setFont(new Font("Segoe UI", Font.PLAIN, 18));

                if (tablero[row][col] != 0) {
                    field.setText(String.valueOf(tablero[row][col]));
                    field.setFont(new Font("Segoe UI", Font.BOLD, 18));
                    field.setEditable(false);
                } else {
                    field.setText("");
                    field.setEditable(true);
                }
            }
        }
    }
}
