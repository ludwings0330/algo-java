package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BJ2660_회장뽑기 {
    static int N;
    static int[][] graph;

    public static void main(String[] args) throws IOException {
        init();
        floydWarsahll();
        print();
    }

    private static void print() {
        int min = Integer.MAX_VALUE;
        List<Integer> candidate = new ArrayList<>();
        for (int r = 1; r <= N; r++) {
            boolean flag = false;
            int max = 0;
            for (int c = 1; c <= N; c++) {
                max = Math.max(max, graph[r][c]);
            }
            if (min > max) {
                min = max;
                candidate.clear();
                candidate.add(r);
            } else if (min == max) {
                candidate.add(r);
            }
        }
        System.out.println(min + " " + candidate.size());
        candidate.stream().forEach(n -> System.out.print(n + " "));
    }

    private static void floydWarsahll() {
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    // i, j 가 친구의 친구라면
                    graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
                }
            }
        }
    }

    private static void init() throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int a = 0, b = 0;
        graph = new int[N + 1][N + 1];
        for (int r = 0; r < N + 1; r++) {
            Arrays.fill(graph[r], Integer.MAX_VALUE / 2);
            graph[r][r] = 0;
        }

        while (true) {
            final StringTokenizer st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            if (a == -1 || b == -1)
                break;
            graph[a][b] = 1;
            graph[b][a] = 1;
        }
    }
}
