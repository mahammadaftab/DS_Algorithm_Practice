import java.util.HashSet;
import java.util.Set;

public class Solution {

    /**
     * Problem: N-Queens II (LeetCode)
    */

    private int count;

    public int totalNQueens(int n) {
        count = 0;
        // Use Sets to keep track of attacked columns, diagonals, and anti-diagonals for O(1) lookups
        Set<Integer> columns = new HashSet<>();
        Set<Integer> diagonals = new HashSet<>(); // row + col
        Set<Integer> antiDiagonals = new HashSet<>(); // row - col

        solveNQueens(n, 0, columns, diagonals, antiDiagonals);
        return count;
    }

    private void solveNQueens(int n, int row, Set<Integer> columns, Set<Integer> diagonals, Set<Integer> antiDiagonals) {
        // Base case: All queens have been placed successfully
        if (row == n) {
            count++;
            return;
        }

        // Try placing a queen in each column of the current row
        for (int col = 0; col < n; col++) {
            // Check if the current column is attacked
            if (columns.contains(col) || diagonals.contains(row + col) || antiDiagonals.contains(row - col)) {
                continue; // Skip to the next column
            }

            // Place the queen and update the attacked columns, diagonals, and anti-diagonals
            columns.add(col);
            diagonals.add(row + col);
            antiDiagonals.add(row - col);

            // Recursively place the next queen
            solveNQueens(n, row + 1, columns, diagonals, antiDiagonals);

            // Backtrack: Remove the queen and reset the attacked columns, diagonals, and anti-diagonals
            columns.remove(col);
            diagonals.remove(row + col);
            antiDiagonals.remove(row - col);
        }
    }

    public static void main(String[] args) {
        Solution solver = new Solution();
        int n = 4; // Example: n = 4 (4x4 chessboard)
        int solutions = solver.totalNQueens(n);
        System.out.println("Number of distinct solutions for " + n + "-Queens: " + solutions); // Output: 2
    }
}
