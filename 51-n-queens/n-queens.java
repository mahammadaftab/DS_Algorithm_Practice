import java.util.ArrayList;
import java.util.List;

public class Solution {

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> solutions = new ArrayList<>();
        int[] queens = new int[n]; // queens[i] = column index where queen is placed in row i
        solveNQueensHelper(solutions, queens, 0, n);
        return solutions;
    }

    private void solveNQueensHelper(List<List<String>> solutions, int[] queens, int row, int n) {
        if (row == n) {
            // All queens are placed. Create and add board representation to solutions.
            solutions.add(createBoard(queens, n));
            return;
        }

        for (int col = 0; col < n; col++) {
            if (isValidPlacement(queens, row, col)) {
                queens[row] = col; // Place queen in current row and column
                solveNQueensHelper(solutions, queens, row + 1, n); // Recursively place remaining queens
            }
        }
    }

    private boolean isValidPlacement(int[] queens, int row, int col) {
        for (int i = 0; i < row; i++) {
            // Check column conflicts
            if (queens[i] == col) {
                return false;
            }
            // Check diagonal conflicts
            if (Math.abs(queens[i] - col) == row - i) {
                return false;
            }
        }
        return true;
    }

    private List<String> createBoard(int[] queens, int n) {
        List<String> board = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            StringBuilder row = new StringBuilder();
            for (int j = 0; j < n; j++) {
                if (queens[i] == j) {
                    row.append('Q');
                } else {
                    row.append('.');
                }
            }
            board.add(row.toString());
        }
        return board;
    }

    public static void main(String[] args) {
        Solution nQueens = new Solution();
        int n = 4; // Example: solve for 4x4 board
        List<List<String>> solutions = nQueens.solveNQueens(n);

        for (List<String> board : solutions) {
            System.out.println("Solution:");
            for (String row : board) {
                System.out.println(row);
            }
            System.out.println();
        }
    }
}



