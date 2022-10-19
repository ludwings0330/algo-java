package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ18442_우체국 {
    static int V, P;
    static long L;
    static long[] pos;
    static int[] choices;
    static long[] arrAnswer;
    static long answer;

    public static void main(String[] args) throws IOException {
        init();
        combination(0, 0);
        System.out.println(answer);
        for (int i = 0; i < P; i++) {
            System.out.print(arrAnswer[i] + " ");
        }
    }

    private static void combination(int start, int count) {
        if (count == P) {
            long dist = getDist();
            if (dist < answer) {
                for (int i = 0; i < P; i++) {
                    arrAnswer[i] = pos[choices[i]];
                    answer = dist;
                }
            }
            return;
        }

        for (int i = start; i < V; i++) {
            choices[count] = i;
            combination(i + 1, count + 1);
        }
    }

    private static long getDist() {
        long dist = 0;

        for (int i = 0; i < V; i++) {
            long tmp = Long.MAX_VALUE;
            long x = pos[i];
            for (int j = 0; j < P; j++) {
                long y = pos[choices[j]];
                tmp = Math.min(tmp, Math.min(Math.abs(x - y), L - Math.abs(x - y)));
            }
            dist += tmp;
        }

        return dist;
    }

    private static void init() throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        L = Long.parseLong(st.nextToken());

        pos = new long[V];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < V; i++) {
            pos[i] = Long.parseLong(st.nextToken());
        }

        choices = new int[P];
        answer = Long.MAX_VALUE;
        arrAnswer = new long[P];
    }
}
