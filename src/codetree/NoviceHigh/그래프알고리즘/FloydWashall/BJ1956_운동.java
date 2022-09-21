package codetree.NoviceHigh.그래프알고리즘.FloydWashall;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BJ1956_운동 {
    static int V, E;
    static LinkedList<Integer[]>[] graph;

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        graph = new LinkedList[V + 1];
        for (int i = 0; i < V + 1; i++) {
            graph[i] = new LinkedList<>();
        }
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            graph[from].add(new Integer[]{to, dist});
        }

        long result = floydWarshall();
        System.out.println(result);
    }

    public static long floydWarshall() {
        long[][] dists = new long[V + 1][V + 1];
        for (int r = 1; r < V + 1; r++) {
            for (int c = 1; c < V + 1; c++) {
                dists[r][c] = 999999999;
            }
        }

        for (int r = 0; r < V + 1; r++) {
            for (var current :
                    graph[r]) {
                dists[r][current[0]] = current[1];
            }
        }

        // floyd warshall 핵심 j -> k > j -> i -> k 라면 업데이트
        for (int i = 1; i <= V; i++) {
            for (int j = 1; j <= V; j++) {
                for (int k = 1; k <= V; k++) {
                    // 그냥 가는 것보다 거쳐서 가는게 더 짧으면 업데이트
                    if (dists[j][k] > dists[j][i] + dists[i][k]) {
                        dists[j][k] = dists[j][i] + dists[i][k];
                    }
                }
            }
        }

        long min = 999999999;

        for (int r = 1; r <= V; r++) {
            min = Math.min(min, dists[r][r]);
            for (int c = 1; c <= V; c++) {
                min = Math.min(min, dists[r][c] + dists[c][r]);
            }
        }

        return (min < 999999999) ? min : -1;
    }
}
