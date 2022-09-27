package programmers.동적계획법;

import java.util.Arrays;

public class 정수삼각형 {
    static int[][] cache;

    public static void main(String[] args) {
        System.out.println(solution(new int[][]{{7}, {3, 8}, {8, 1, 0}, {2, 7, 4, 4}, {4, 5, 2, 6, 5}}));
        System.out.println(solution(new int[][]{{1}, {50, 1}, {50, 1, 1}, {1, 1, 1000, 4}, {4, 5, 2, 6, 5}}));
        System.out.println(solution(new int[][]{{10}}));
    }

    public static int solution(int[][] triangle) {
        cache = new int[501][501];
        for (int[] ints : cache) {
            Arrays.fill(ints, -1);
        }

        int answer = recursiveSolve(0, 0, triangle);

        return answer;
    }

    // r, c 에서 출발할때 최대 합 반환
    private static int recursiveSolve(int r, int c, int[][] triangle) {
        if (c < 0 || c > r)
            return -1;

        // 마지막줄이면 더이상 아래로 가지 않음
        if (r == triangle.length - 1) {
            return cache[r][c] = triangle[r][c];
        }

        if (cache[r][c] != -1)
            return cache[r][c];

        cache[r][c] = triangle[r][c];
        // 1. 왼쪽 아래
        // 2. 오른쪽 아래
        int[][] moves = {{1, 0}, {1, 1}};

        for (var move :
                moves) {
            int nr = r + move[0];
            int nc = c + move[1];

            // 0번 줄에는 0 번, 1번줄에는 0, 1
            if (nc > nr || nc < 0)
                continue;

            cache[r][c] = Math.max(cache[r][c], triangle[r][c] + recursiveSolve(nr, nc, triangle));
        }

        return cache[r][c];
    }
}
