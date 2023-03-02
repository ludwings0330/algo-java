package swexpert.b형;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 이진수표현 {

    static int TC;
    static int N;
    static int M;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        TC = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < TC; tc++) {
            sb.append("#").append(tc + 1).append(" ");
            final StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            sb.append(solve() ? "ON" : "OFF").append('\n');
        }

        System.out.println(sb.toString());

    }

    private static boolean solve() {
        final StringBuilder sb = new StringBuilder();
        while (M > 0) {
            sb.append(M % 2);
            M /= 2;
        }

        String bi = sb.toString();

        if (bi.length() < N) {
            return false;
        }

        for (int i = 0; i < N; i++) {
            if (bi.charAt(i) == '0') {
                return false;
            }
        }

        return true;
    }


}
