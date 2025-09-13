import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        if (matrix == null || matrix.length == 0) {
            return result;
        }

        int rows = matrix.length;
        int cols = matrix[0].length;
        int top = 0;
        int bottom = rows - 1;
        int left = 0;
        int right = cols - 1;
        int dir = 0; // 0: right, 1: down, 2: left, 3: up

        while (top <= bottom && left <= right) {
            if (dir == 0) { // Traverse right
                for (int i = left; i <= right; i++) {
                    result.add(matrix[top][i]);
                }
                top++;
            } else if (dir == 1) { // Traverse down
                for (int i = top; i <= bottom; i++) {
                    result.add(matrix[i][right]);
                }
                right--;
            } else if (dir == 2) { // Traverse left
                for (int i = right; i >= left; i--) {
                    result.add(matrix[bottom][i]);
                }
                bottom--;
            } else if (dir == 3) { // Traverse up
                for (int i = bottom; i >= top; i--) {
                    result.add(matrix[i][left]);
                }
                left++;
            }
            dir = (dir + 1) % 4; // Change direction
        }

        return result;
    }

    // Example usage (for testing):
    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] matrix = {{
                1, 2, 3
            }, {
                4, 5, 6
            }, {
                7, 8, 9
            }
        };

        List<Integer> spiral = sol.spiralOrder(matrix);
        System.out.println(spiral); // Output: [1, 2, 3, 6, 9, 8, 7, 4, 5]

        int[][] matrix2 = {{
                1, 2, 3, 4
            }, {
                5, 6, 7, 8
            }, {
                9, 10, 11, 12
            }
        };
        spiral = sol.spiralOrder(matrix2);
        System.out.println(spiral); // Output: [1, 2, 3, 4, 8, 12, 11, 10, 9, 5, 6, 7]

        int[][] matrix3 = {{7}, {9}, {6}};
        spiral = sol.spiralOrder(matrix3);
        System.out.println(spiral); //Output: [7, 9, 6]
    }
}

