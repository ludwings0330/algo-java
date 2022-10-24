package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ17435_합성함수와쿼리 {
    static int m, Q;
    static int[] f;
    static StringBuilder sb = new StringBuilder();
    static int[][] sparseTable;
    static int CNT_LOG_M;

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        m = Integer.parseInt(br.readLine());
        f = new int[m + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= m; i++) {
            f[i] = Integer.parseInt(st.nextToken());
        }

        initSparseTable();

        Q = Integer.parseInt(br.readLine());
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            sb.append(solve(x, n)).append('\n');
        }

        System.out.println(sb.toString());
    }

    // 전처리 n log n
    private static void initSparseTable() {
        CNT_LOG_M = 19;
        // log(m) 구해서 테이블 만들기
        int LOG_M = 1;
        while (LOG_M <= m) {
            LOG_M <<= 1;
//            CNT_LOG_M++;
        }

        sparseTable = new int[m + 1][CNT_LOG_M];

        for (int i = 0; i <= m; i++) {
            sparseTable[i][0] = f[i];
        }

        for (int k = 1; k < CNT_LOG_M; k++) {
            for (int n = 0; n <= m; n++) {
                // d[n][k] -> n 이 2^k 만큼 앞으로 이동했을때 위치
                // -> n 이 2^(k-1) 만큼 이동한 위치에서 2^(k-1) 만큼 이동한 위치
                sparseTable[n][k] = sparseTable[sparseTable[n][k - 1]][k - 1];
            }
        }
    }

    // n 에서 시작하여 x 번째 앞에있는 결과
    private static int solve(int n, int x) {
        for (int i = 0; i < CNT_LOG_M; i++) {
            if ((x & (1 << i)) > 0)
                n = sparseTable[n][i];
        }
        return n;
    }
}
