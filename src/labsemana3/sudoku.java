package labsemana3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class sudoku {

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
            {0, 0, 0, 7 ,6, 0, 0, 0, 0},
            {8, 2, 7, 0, 0, 0, 0, 0, 0},
            {0, 9, 0, 0, 0, 7, 8, 3, 2},
            {0, 0, 0, 0, 0, 4, 1, 9, 0},
            {2, 0, 8, 9, 3, 6, 7, 4, 5},
            {0, 0, 2, 1, 4, 5, 0, 0, 0},
            {1, 0, 0, 0, 0, 3, 0, 2, 9},
            {3, 7, 0, 2, 9, 0, 5, 0, 0}
        }
            
            
            
         
    };
    
    private int currentBoardIndex = 0; 
    private int[][] board; 
    private final JTextField[][] fields = new JTextField[9][9];

    public sudoku() {
        board = boards[currentBoardIndex]; 

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
                    field.setBackground(Color.LIGHT_GRAY);
                } else {
                    field.setText("");
                    field.setEditable(true);
                    field.setBackground(Color.WHITE);
                }
            }
        }
    }

    public static void main(String[] args) {
        new sudoku();
    }
}
