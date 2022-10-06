package swexpert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SE1263_사람네트워크2 {
    static int N;
    static int[][] graph;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int TC = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= TC; tc++) {
            sb.append("#").append(tc).append(" ").append(solve()).append("\n");
        }
        System.out.println(sb.toString());
    }

    private static int solve() throws IOException {
        init();
        return floydWarshall();
    }

    private static int floydWarshall() {
        int ret = Integer.MAX_VALUE;

        for (int k = 0; k < N; k++)
            for (int i = 0; i < N; i++)
                for (int j = 0; j < N; j++)
                    graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);

        for (int r = 0; r < N; r++)
            ret = Math.min(ret, Arrays.stream(graph[r]).sum());

        return ret;
    }

    private static void init() throws IOException {
        final StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        graph = new int[N][N];

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                graph[r][c] = Integer.parseInt(st.nextToken());
                if (r != c && graph[r][c] == 0)
                    graph[r][c] = 999999;
            }
        }
    }
}