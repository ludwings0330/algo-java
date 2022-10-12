package swexpert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SE5643_키순서 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, M;
    static int[][] graph;
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

        floydWarshall();

        return count();
    }

    private static int count() {
        int ret = 0;
        for (int i = 1; i <= N; i++) {
            int cnt = 0;
            for (int j = 1; j <= N; j++) {
                cnt += graph[i][j];
                cnt += graph[j][i];
            }
            if (cnt == N - 1)
                ret++;
        }
        return ret;
    }

    private static void floydWarshall() {
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    // i < j 를 알기 위해서는 i < k && k < j
                    if (graph[i][k] == 1 && graph[k][j] == 1)
                        graph[i][j] = 1;
                }
            }
        }
    }

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        graph = new int[N + 1][N + 1];

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            // a < b
            graph[a][b] = 1;
        }
    }
}
