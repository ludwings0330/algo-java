package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ1068_트리 {

    static int N;

    static int[][] graph = new int[50][50];
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int answer;
    static int root;
    static int delete;
    static int[] nodes;

    public static void main(String[] args) throws Exception {
        init();
        solve(root);
        System.out.println(answer);
    }

    public static void solve(int current) {
        if (current == delete) {
            return;
        }

        int isLeaf = 0;
        for (int i = 0; i < 50; i++) {
            if (graph[current][i] == 1) {
                solve(i);
                isLeaf++;
                if (i == delete) {
                    isLeaf--;
                }
            }
        }

        if (isLeaf == 0) {
            answer++;
        }
    }

    public static void init() throws Exception {
        N = Integer.parseInt(br.readLine());
        nodes = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nodes[i] = Integer.parseInt(st.nextToken());
            if (nodes[i] == -1) {
                root = i;
                continue;
            }
            graph[nodes[i]][i] = 1;
        }
        answer = 0;
        delete = Integer.parseInt(br.readLine());
    }

}
