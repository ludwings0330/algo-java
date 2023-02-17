package swexpert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA1855_영준이의진짜BFS {

    static int testCase;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static StringBuilder sb = new StringBuilder();
    static int N;
    static int[] parents;

    public static void main(String[] args) throws IOException {
        testCase = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= testCase; tc++) {
            sb.append("#").append(tc).append(' ').append(solve()).append('\n');
        }
    }

    private static long solve() throws IOException {
        init();
        return 0;
    }

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        parents = new int[N + 1];
        for (int i = 1; i < N; i++) {
            parents[i] = Integer.parseInt(st.nextToken()) - 1;
        }
    }

}
