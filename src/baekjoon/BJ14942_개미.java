package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ14942_개미 {
    static int n;

    static List<CaveInfo>[] graph;
    static int logN;
    static int[] energies;
    static CaveInfo[][] sparseTable;

    public static void main(String[] args) throws IOException {
        init();
        final StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            sb.append(solve(i, energies[i])).append('\n');
        }
        System.out.println(sb.toString());
    }

    private static int solve(int n, int energy) {
        for (int k = logN - 1; k >= 0; k--) {
            if (sparseTable[n][k].edge <= energy) {
                energy -= sparseTable[n][k].edge;
                n = sparseTable[n][k].vertex;
            }
        }

        return n;
    }

    private static void init() throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        energies = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            energies[i] = Integer.parseInt(br.readLine());
        }

        graph = new List[energies.length];
        for (int i = 0; i < energies.length; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < n - 1; i++) {
            final StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            CaveInfo cave = new CaveInfo(e, d);
            graph[s].add(cave);

            cave = new CaveInfo(s, d);
            graph[e].add(cave);
        }

        bfs();

        initSparseTable();
    }

    private static void initSparseTable() {
        int tmp = 1;
        logN = 1;
        while (tmp <= n) {
            tmp <<= 1;
            logN++;
        }
        sparseTable = new CaveInfo[n + 1][logN];

        sparseTable[1][0] = new CaveInfo(1, 0);
        for (int i = 2; i < sparseTable.length; i++) {
            // i 번 노드에서 2^0 앞에 있는 node
            sparseTable[i][0] = graph[i].get(0);
        }

        for (int k = 1; k < logN; k++) {
            for (int n = 1; n < sparseTable.length; n++) {
                int next = sparseTable[n][k - 1].vertex;
                int dist = sparseTable[n][k - 1].edge;
                sparseTable[n][k] = new CaveInfo(sparseTable[next][k - 1].vertex, sparseTable[next][k - 1].edge + dist);
            }
        }

    }

    private static void bfs() {
        Deque<Integer> deque = new LinkedList<>();
        boolean[] visited = new boolean[n + 1];

        int s = 1;
        visited[1] = true;

        List<CaveInfo>[] directedGraph = new List[n + 1];
        for (int i = 0; i < directedGraph.length; i++) {
            directedGraph[i] = new ArrayList<>();
        }

        for (CaveInfo cave :
                graph[1]) {
            // parent, current
            deque.add(cave.vertex);
            directedGraph[cave.vertex].add(new CaveInfo(1, cave.edge));
            visited[cave.vertex] = true;
        }

        while (!deque.isEmpty()) {
            int current = deque.pollFirst();

            for (var next :
                    graph[current]) {
                if (visited[next.vertex])
                    continue;

                visited[next.vertex] = true;
                directedGraph[next.vertex].add(new CaveInfo(current, next.edge));
                deque.offerLast(next.vertex);
            }
        }

        graph = directedGraph;
    }

    static class CaveInfo {
        int vertex, edge;

        public CaveInfo() {
        }

        public CaveInfo(int vertex, int edge) {
            this.vertex = vertex;
            this.edge = edge;
        }
    }
}
