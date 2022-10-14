package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ1700_멀티탭스케줄링 {
    static int N, K;
    static int[] next;
    static int[] tabInfo;
    static int[] op;

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        // 현재 번호가 다시 등장하기까지 거리
        // 멀티탭에 있는 것중 가장 나중에 등장하는 코드를 뽑아줄 것
        next = new int[K + 1];
        // tab 사용중인 정보 저장
        // 처음에는 모두 0
        tabInfo = new int[N];
        Arrays.fill(tabInfo, -1);
        st = new StringTokenizer(br.readLine());
        op = new int[K + 1];

        // 순서저장
        for (int i = 0; i < K; i++) {
            int n = Integer.parseInt(st.nextToken());
            op[i] = n;
        }

        // i 번째의 code 가 다음에 들어오기까지의 거리
        for (int i = 0; i < K; i++) {
            for (int j = i + 1; j < K; j++) {
                if (op[i] == op[j]) {
                    next[i] = j;
                    break;
                }
            }
            if (next[i] == 0) next[i] = Integer.MAX_VALUE;
        }

        int answer = 0;
        // 탭에 꽂혀있는 애들 중 가장 멀리있는애로 바꿈
        for (int i = 0; i < K; i++) {
            // op[i] code 를 꽂아야함

            // tabInfo 에서 꽂을 자리 확인
            int maxDist = 0;
            int idx = 0;
            for (int j = 0; j < N; j++) {
                if (tabInfo[j] == -1) {
                    // 아무것도 꽂혀있지 않다면
                    idx = j;
                    break;
                }
                if (op[tabInfo[j]] == op[i]) {
                    // 이미 해당 코드가 꽂혀있다면
                    // 아무것도 하지 않음
                    idx = j;
                    break;
                }
                if (maxDist < next[tabInfo[j]] - i) {
                    maxDist = next[tabInfo[j]] - i;
                    idx = j;
                }
            }
            // 빈자리가 아니면서 이미 꽂힌값이 아니면 뽑아야한다.
            if (tabInfo[idx] != -1 && op[tabInfo[idx]] != op[i])
                answer++;

            tabInfo[idx] = i;
        }

        System.out.println(answer);
    }
}
