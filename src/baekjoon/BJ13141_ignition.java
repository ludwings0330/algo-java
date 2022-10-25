package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ13141_ignition {
    static int[][] dists = new int[201][201];
    static int N, M;
    static int[] S = new int[20001], E = new int[20001], L = new int[20001];

    static {
        for (int r = 0; r < dists.length; r++) {
            Arrays.fill(dists[r], Integer.MAX_VALUE / 2 - 1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            S[i] = Integer.parseInt(st.nextToken());
            E[i] = Integer.parseInt(st.nextToken());
            L[i] = Integer.parseInt(st.nextToken());

            dists[S[i]][E[i]] = dists[E[i]][S[i]] = Math.min(dists[S[i]][E[i]], L[i]);
        }

        // floyd-Warshall
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (i == j)
                        dists[i][j] = 0;
                    dists[i][j] = Math.min(dists[i][j], dists[i][k] + dists[k][j]);
                }
            }
        }

        // find longest length
        int answer = Integer.MAX_VALUE;

        // 출발 정점을 n 으로 선택했을 때,
        for (int n = 1; n <= N; n++) {
            // n 에대해서 모든 간선을 확인하여 최대 길이를 얻음
            int tmp = 0;
            for (int m = 1; m <= M; m++) {
                tmp = Math.max(tmp, dists[n][S[m]] + dists[n][E[m]] + L[m]);
            }
            answer = Math.min(answer, tmp);
        }

        System.out.println(answer / 2.0);
    }
}
