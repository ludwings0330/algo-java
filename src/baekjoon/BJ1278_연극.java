package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ1278_연극 {
    static int N;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static boolean[] visited = new boolean[1 << 20];

    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());
        visited[0] = true;
        solve(0, 0);
        System.out.println((1 << N) - 1);
        System.out.println(sb.toString());
    }

    public static boolean solve(int actors, int count) {
        // 각 무대에서 배우들의 구성은 장면마다 달라야한다.
        // 각 무대에서 반드시 1명 이상 올라와 있어야한다.
        // 다음 무대로 전환시, 2가지 경우가 있음
        // 1. 한명이 올라옴
        // 2. 한명이 내려감
        if (count == (1 << N) - 1) {
            // 마지막 남은 인원 빼내기
            int i = 0;
            while (actors > 0) {
                i++;
                if (actors % 2 == 1) {
                    sb.append(i).append('\n');
                }
                actors /= 2;
            }
            return true;
        }

        for (int i = 0; i < N; i++) {
            int k = 1 << i;
            int tActors = actors ^ k;
            if (visited[tActors]) {
                continue;
            }
            visited[tActors] = true;
            if (solve(tActors, count + 1)) {
                sb.append(i + 1).append('\n');
                return true;
            }
            visited[tActors] = false;
        }

        return false;
    }
}
