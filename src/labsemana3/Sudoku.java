/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package labsemana3;

import javax.swing.JTextField;

/**
 *
 * @author river
 */
public abstract class Sudoku {

    protected abstract boolean solucionValida(JTextField[][] espacios);

    protected abstract boolean esValidSudoku(int[][] board);

    protected abstract boolean esValidGrupo(int[][] board, int index, boolean isRow);

    protected abstract boolean cajaValida(int[][] board, int box);

    protected abstract void nuevoTablero(int[][] tab, int indexTablero, int[][][] casillas, JTextField[][] espacios);
}
