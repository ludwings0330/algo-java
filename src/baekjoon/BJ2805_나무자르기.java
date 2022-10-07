package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ2805_나무자르기 {
    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] heights = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            heights[i] = Integer.parseInt(st.nextToken());
        }
        int left = 0, right = 1000000001;
        while (left < right) {
            // left 로 정답을 판별할때에는 + 1 을 통해서 mid 의 값이 항상 left 보다 크게 해준다.
            int mid = (left + right + 1) / 2;
            long cnt = 0;
            for (int i = 0; i < N; i++) {
                cnt += Math.max(heights[i] - mid, 0);
            }

            // mid 는 정답일 가능성 없음.
            // 그리고 mid 를 더 줄여줘야함.
            if (cnt < M) {
                right = mid - 1;
            } else {
                // mid 는 정답일 가능성이 있음
                // 이중에 mid 의 최대 높이를 찾아야함
                left = mid;
            }
        }

        System.out.println(left);
    }
}
