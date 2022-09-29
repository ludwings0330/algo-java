package programmers.동적계획법;

import java.util.Arrays;

public class 등굣길 {
    public static int solution(int c, int r, int[][] puddles) {
        int MOD = 1_000_000_007;
        int[][] cache = new int[r][c];

        for (int i = 0; i < r; i++)
            Arrays.fill(cache[i], -1);
        int[][] board = new int[r][c];

        for (int i = 0; i < puddles.length; i++) {
            board[puddles[i][1] - 1][puddles[i][0] - 1] = 1;
        }

        int answer = recursiveSole(0, 0, board, cache);

        return answer;
    }

    private static int recursiveSole(int r, int c, int[][] puddles, int[][] cache) {
        if (r == puddles.length - 1 && c == puddles[0].length - 1)
            return 1;

        if (r < 0 || r >= puddles.length || c < 0 || c >= puddles[0].length || puddles[r][c] == 1)
            return 0;
        if (cache[r][c] != -1) return cache[r][c];

        cache[r][c] = 0;
        cache[r][c] += recursiveSole(r + 1, c, puddles, cache) + recursiveSole(r, c + 1, puddles, cache);
        cache[r][c] %= 1_000_000_007;

        return cache[r][c];
    }

    public static void main(String[] args) {
        System.out.println(solution(4, 3, new int[][]{{2, 2}}));
    }
}
