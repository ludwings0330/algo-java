package programmers.kakao;

import java.util.Comparator;
import java.util.TreeSet;

public class 징검다리건너기 {
    public static int solution(int[] stones, int k) {
        int answer = Integer.MAX_VALUE;
        Integer[] arr = new Integer[stones.length];
        TreeSet<Integer> ts = new TreeSet<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                // 내림 차순 정렬
                return stones[o2] - stones[o1];
            }
        });
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
        for (int i = 0; i < k; i++) {
            ts.add(arr[i]);
        }
        int left = 0;
        for (int i = k; i < stones.length; i++) {
            answer = Math.min(answer, stones[ts.first()]);
            ts.remove(arr[i - k]);
            ts.add(arr[i]);
        }

        return answer;
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[]{2, 4, 5, 3, 2, 1, 4, 2, 5, 1}, 3));
        System.out.println(solution(new int[]{1, 2, 5, 1, 2, 5, 1, 2, 5, 1}, 3));
    }
}
