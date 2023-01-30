package programmers;

import java.util.HashMap;
import java.util.Map;

public class 시소짝꿍 {

    public static void main(String[] args) {
        System.out.println(solution(new int[] { 100, 100, 100, 100 }));
    }

    public static long solution(int[] weights) {
        long answer = 0;

        Map<Integer, Long> counter = new HashMap<>();

        for (int weight :
                weights) {
            counter.put(weight, counter.getOrDefault(weight, 0L) + 1);
        }

        for (var left :
                counter.entrySet()) {
            for (var right :
                    counter.entrySet()) {
                if (left.getKey() == right.getKey()) {
                    answer += ((left.getValue()) * (right.getValue() - 1));
                    continue;
                }
                boolean isPair = false;
                for (int i = 2; i <= 4; i++) {
                    for (int j = 2; j <= 4; j++) {
                        if (left.getKey() * i == right.getKey() * j) {
                            isPair = true;
                        }
                    }
                }

                if (isPair) {
                    answer += left.getValue() * right.getValue();
                }
            }
        }

        return answer / 2;
    }

}
