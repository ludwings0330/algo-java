package swexpert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SE14510_나무높이 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static int N;
    static int[] heights;
    static int[][] cache;
    static int MAX;
    static int remains;

    public static void main(String[] args) throws IOException {
        int TC = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= TC; tc++) {
            sb.append("#").append(tc).append(' ').append(solve()).append('\n');
        }

        System.out.println(sb.toString());
    }

    private static int solve() throws IOException {
        init();
        return recursiveSolve(remains, 0);
    }

    // 남은 높이가 k 이고 현재 일수가 day 일때 모두 채우기위한 최소 일수
    private static int recursiveSolve(int k, int day) {
        if (k < 0) return Integer.MAX_VALUE;
        if (day > MAX) return Integer.MAX_VALUE;
        if (k == 0) return day;

        if (cache[k][day] != -1)
            return cache[k][day];

        cache[k][day] = Integer.MAX_VALUE;
        if (day % 2 == 1) {
            cache[k][day] = Math.min(cache[k][day], recursiveSolve(k - 1, day + 1));
        } else if (day % 2 == 0 && day != 0) {
            cache[k][day] = Math.min(cache[k][day], recursiveSolve(k - 2, day + 1));
        }
        cache[k][day] = Math.min(cache[k][day], recursiveSolve(k, day + 1));
        return cache[k][day];
    }

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());
        heights = new int[N];
        MAX = 0;
        remains = 0;

        final StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            heights[i] = Integer.parseInt(st.nextToken());
            MAX = Math.max(MAX, heights[i]);
        }

        for (int i = 0; i < N; i++) {
            if (heights[i] < MAX)
                remains += MAX - heights[i];
        }

        cache = new int[remains + 1][MAX + 1];
        for (int i = 0; i < remains + 1; i++) {
            Arrays.fill(cache[i], -1);
        }
    }
}
