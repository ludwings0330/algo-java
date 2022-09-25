package Test.KAKAO2023BLINDTEST;

public class B {
    public static long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        int dIdx = n - 1;
        int pIdx = n - 1;

        // critical 제거
        while (dIdx >= 0 && deliveries[dIdx] == 0) dIdx--;
        while (pIdx >= 0 && pickups[pIdx] == 0) pIdx--;

        // 모두 배달할때 혹은 수거 할 때 까지 반복
        while (dIdx >= 0 || pIdx >= 0) {
            // 배달해야하던, 수거해야하던 최대한 멀리 가야한다.
            answer += (Math.max(dIdx, pIdx) + 1) * 2;
            int tmp = cap;
            if (dIdx >= 0) {
                while (tmp > 0) {
                    // 물건이 tmp 개 만큼 있음 deliveries[dIdx] 로 배달을 간다
                    if (tmp >= deliveries[dIdx]) {
                        // 만약 차에 더 많이 있으면 다 줘야지
                        tmp -= deliveries[dIdx];
                        deliveries[dIdx] = 0;
                    } else {
                        // 배달할 게 더 남 았으면 다음에 올게요~
                        deliveries[dIdx] -= tmp;
                        tmp = 0;
                    }
                    // 해당 주소에 모두 배달했으면 아래로 옮겨옴
                    while (dIdx >= 0 && deliveries[dIdx] == 0) dIdx--;
                    if (dIdx < 0) break;
                }
            }
            tmp = cap;
            if (pIdx >= 0) {
                while (tmp > 0) {
                    // 물건이 tmp 개 만큼 있음 deliveries[dIdx] 로 배달을 간다
                    if (tmp >= pickups[pIdx]) {
                        // 만약 차에 더 많이 있으면 다 줘야지
                        tmp -= pickups[pIdx];
                        pickups[pIdx] = 0;
                    } else {
                        // 배달할 게 더 남 았으면 다음에 올게요~
                        pickups[pIdx] -= tmp;
                        tmp = 0;
                    }

                    // 해당 주소에 모두 옮겻으면 아래 주소로 옮겨옴
                    while (pIdx >= 0 && pickups[pIdx] == 0) pIdx--;
                    if (pIdx < 0) break;
                }
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        System.out.println(solution(4, 5, new int[]{1, 0, 3, 1, 2}, new int[]{0, 3, 0, 4, 0}));
        System.out.println(solution(2, 7, new int[]{1, 0, 2, 0, 1, 0, 2}, new int[]{0, 2, 0, 1, 0, 2, 0}));
        System.out.println(solution(1, 1, new int[]{0}, new int[]{0}));
    }
}
