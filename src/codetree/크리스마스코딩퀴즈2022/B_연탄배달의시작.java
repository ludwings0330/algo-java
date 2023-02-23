package codetree.크리스마스코딩퀴즈2022;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_연탄배달의시작 {

    static int N;
    static int[] pos;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        pos = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            pos[i] = Integer.parseInt(st.nextToken());
        }

        int min = Integer.MAX_VALUE;
        int cnt = 0;

        for (int i = 0; i < N - 1; i++) {
            int dist = pos[i + 1] - pos[i];
            if (dist < min) {
                cnt = 1;
                min = pos[i + 1] - pos[i];
            } else if (dist == min) {
                cnt++;
            }
        }
        System.out.println(cnt);
    }

}
