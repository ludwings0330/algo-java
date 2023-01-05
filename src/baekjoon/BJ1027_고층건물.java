package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ1027_고층건물 {
    static int N;
    static int[] heights;
    static int answer = 0;

    public static void main(String[] args) throws Exception {
        init();

        solve();
        System.out.println(answer);
    }

    public static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        heights = new int[N];
        for (int i = 0; i < N; i++) {
            heights[i] = Integer.parseInt(st.nextToken());
        }
    }

    public static void solve() {
        for (int root = 0; root < N; root++) {
            int count = 0;
            double angle = 0;

            for (int left = root - 1; left >= 0; left--) {
                int dx = root - left;
                int dy = heights[root] - heights[left];
                double t_angle = (double) dy / (double) dx;

                // 전의 기울기가 작아야 볼 수 있음
                if (left == root - 1 || t_angle < angle) {
                    angle = t_angle;
                    count++;
                }
            }
            angle = 0;
            for (int right = root + 1; right < N; right++) {
                int dx = right - root;
                int dy = heights[right] - heights[root];
                double t_angle = (double) dy / (double) dx;
                if (right == root + 1 || t_angle > angle) {
                    angle = t_angle;
                    count++;
                }
            }

            answer = Math.max(answer, count);
        }
    }
}
