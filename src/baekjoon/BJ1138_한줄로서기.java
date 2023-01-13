package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ1138_한줄로서기 {

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        final StringTokenizer st = new StringTokenizer(br.readLine());
        int[] result = new int[N];
        int[] input = new int[N];
        for (int i = 0; i < N; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 0; i < N; i++) {
            int idx = 0;
            while (idx < N && result[idx] != 0) {
                idx++;
            }
            int count = 0;
            for (int j = 0; j < N; j++) {
                if (count == input[i] && result[j] == 0) {
                    result[j] = i + 1;
                    break;
                }
                if (result[j] == 0) {
                    count++;
                }
            }
        }

        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(result[i]).append(' ');
        }
        System.out.println(sb.toString());
    }

}
