package swexpert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SE3307_최장증가부분수열 {
    static int TC;
    static int N;
    static int[] arr;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        TC = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= TC; tc++) {
            init();
            sb.append("#").append(tc).append(" ").append(lis()).append("\n");
        }
        System.out.println(sb.toString());
    }

    // O(n^2)
    private static int lis() {
        int[] cache = new int[N];

        for (int i = 0; i < N; i++) {
            cache[i] = 1;
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i])
                    cache[i] = Math.max(cache[i], cache[j] + 1);
            }
        }

        return Arrays.stream(cache).max().getAsInt();
    }

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        final StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
    }
}
