package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ10266_시계사진들 {
    static int N;
    static int[] clockA;
    static int[] clockB;

    public static void main(String[] args) throws IOException {
        init();

    }

    private static void init() throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        clockA = new int[N * 2];
        clockB = new int[N];
        for (int i = 0; i < N; i++) {
            clockA[i] = clockA[i + N] = Integer.parseInt(st.nextToken());
            clockB[i] = Integer.parseInt(st2.nextToken());
        }
    }
}
