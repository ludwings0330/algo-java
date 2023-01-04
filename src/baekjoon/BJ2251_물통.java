package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class BJ2251_물통 {
    static int[][][] cache = new int[201][201][201];
    static int A, B, C;
    static Set<Integer> answer = new HashSet<>();

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        init();
        solve(0, 0, C);
        answer.stream().sorted().forEach(n -> sb.append(n).append(' '));
        System.out.println(sb.toString());
    }

    public static void init() throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        for (int i = 0; i < 200; i++) {
            for (int j = 0; j < 200; j++) {
                for (int k = 0; k < 200; k++) {
                    cache[i][j][k] = -1;
                }
            }
        }
    }

    static void solve(int a, int b, int c) {
        if (cache[a][b][c] != -1) {
            return;
        }
        cache[a][b][c] = 1;

        // a -> b
        solve(Math.max(0, a - (Math.min(B, b + a) - b)), Math.min(B, b + a), c);
        // a -> c
        solve(Math.max(0, a - (Math.min(C, c + a) - c)), b, Math.min(C, c + a));
        // b -> a
        solve(Math.min(A, a + b), Math.max(0, b - (Math.min(A, a + b) - a)), c);
        // b -> c
        solve(a, Math.max(0, b - (Math.min(C, c + b) - c)), Math.min(C, c + b));
        // c -> a
        solve(Math.min(A, a + c), b, Math.max(0, c - (Math.min(A, a + c) - a)));
        // c -> b
        solve(a, Math.min(B, b + c), Math.max(0, c - (Math.min(B, b + c) - b)));
        if (a == 0)
            answer.add(c);

    }
}
