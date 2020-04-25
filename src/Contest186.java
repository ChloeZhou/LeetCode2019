/**
 * Created by keyingzhou on 4/25/20.
 */
public class Contest186 {

    public int largestSquareSurroundedByOne(int[][] matrix) {
        // Write your solution here
        //pre process: get the longest 1 from right to left
        // get the longest 1 from bottom to top
        int n = matrix.length; int m = matrix[0].length;
        int[][] rightLeft = new int[n][m];
        int[][] bottomTop = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = m - 1; j >= 0; j--) {
                if (j == m - 1) {
                    rightLeft[i][j] = matrix[i][j];
                } else {
                    rightLeft[i][j] = matrix[i][j] == 0 ? 0 : matrix[i][j + 1] + 1;
                }
            }
        }

        for (int j = 0; j < m; j++) {
            for (int i = n - 1; i >= 0; i--) {
                if (i == n - 1) {
                    bottomTop[i][j] = matrix[i][j];
                } else {
                    bottomTop[i][j] = matrix[i][j] == 0 ? 0 : matrix[i + 1][j] + 1;
                }
            }
        }

        //
        int res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == 1) {
                    res = Math.max(res, getLargestSquare(i, j, matrix, rightLeft, bottomTop));
                }
            }
        }

        return res;


    }

    private int getLargestSquare(int i, int j, int[][] matrix, int[][] rightLeft, int[][] bottomTop) {
        //
        int possibleMax = Math.max(rightLeft[i][j], bottomTop[i][j]);
        for (int k = possibleMax; k >= 1; k--) {
            if (rightLeft[i + k - 1][j] >= k && bottomTop[i][j + k - 1] >= k) {
                return k;
            }
        }
        return 0;
    }
}
