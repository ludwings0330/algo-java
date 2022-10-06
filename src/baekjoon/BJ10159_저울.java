package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ10159_저울 {
    static int N, M;
    static int[][] graph;

    public static void main(String[] args) throws IOException {
        init();
        floydWarshall();
        for (int i = 0; i < N; i++) {
            int cnt = 0;
            for (int j = 0; j < N; j++) {
                // i > j 혹은 j < i 둘중 하나만 알아도 아는 것이라고 생각
                cnt += graph[i][j] + graph[j][i];
            }
            System.out.println(N - cnt - 1);
        }
    }

    private static void floydWarshall() {
        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    // i > j 가 맞는 정보인지는
                    // i> k 이고 k > j 라면 i > j 라는 것을 알 수 있음
                    if (graph[i][k] == graph[k][j] && graph[k][j] == 1)
                        graph[i][j] = 1;
                }
            }
        }
    }

    private static void init() throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        graph = new int[N][N];

        for (int i = 0; i < M; i++) {
            final StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;
            // 앞의 물건이 뒤의 물건보다 무겁다.
            // from > to
            graph[from][to] = 1;
        }
    }
}
