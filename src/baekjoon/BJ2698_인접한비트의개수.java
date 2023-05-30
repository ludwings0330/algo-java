package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ2698_인접한비트의개수 {

    static int T;
    static int N, K;
    static int[][][] cache;

    // n<= 100 k <= 100
    // 크기가 n 인 수열에서 인접한 비트의 개수가 k 인 수열의 갯수
    public static void main(String[] args) throws IOException {

        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            cache = new int[101][101][2];
            for (int[][] intss :
                    cache) {
                for (int[] ints :
                        intss) {
                    Arrays.fill(ints, -1);
                }
            }
            final StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            int answer = recursiveSolve(0, 0, 0);
            System.out.println(answer);
        }
    }

    private static int recursiveSolve(int n, int k, int b) {
        if (n == N) {
            if (k == K) {
                return 1;
            }
            return 0;
        }
        if (cache[n][k][b] != -1) {
            return cache[n][k][b];
        }

        cache[n][k][b] = 0;

        if (b == 1) {
            cache[n][k][b] += recursiveSolve(n + 1, k + 1, 1);
            cache[n][k][b] += recursiveSolve(n + 1, k, 0);
        } else {
            cache[n][k][b] += recursiveSolve(n + 1, k, 0);
            cache[n][k][b] += recursiveSolve(n + 1, k, 1);
        }

        return cache[n][k][b];
    }

}
