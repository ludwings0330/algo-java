package programmers;

import java.util.Arrays;

public class 시소짝꿍 {

    public static void main(String[] args) {
        System.out.println(solution(new int[] { 100, 180, 360, 100, 270 }));
    }

    public static long solution(int[] weights) {
        long answer = 0;

        Arrays.sort(weights);
        int[][] cache = new int[5][4001];

        for (int weight :
                weights) {
            for (int i = 2; i <= 4; i++) {
                cache[i][weight * i] += 1;
            }
        }

        for (int weight :
                weights) {
            for (int i = 2; i <= 4; i++) {
                int target = weight * i;
                cache[i][target] = Math.max(0, cache[i][target] - 1);
                for (int j = 2; j <= 4; j++) {
                    if (cache[j][target] > 0) {
                        answer += cache[j][target];
                        for (int k = j; k <= 4; k++) {
                            cache[k][target / j * k] = Math.max(0, cache[k][target / j * k] - 1);
                        }
                    }
                }
            }
        }

        return answer;
    }

}
