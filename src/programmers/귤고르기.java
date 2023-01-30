package programmers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class 귤고르기 {

    public static void main(String[] args) {
        System.out.println(solution(6, new int[] { 1, 3, 2, 5, 4, 5, 2, 3 }));
    }

    public static int solution(int k, int[] tangerine) {
        int answer = 0;
        Map<Integer, Integer> map = new HashMap<>();

        for (int n :
                tangerine) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }

        final List<Entry<Integer, Integer>> li = map.entrySet().stream()
                                                    .sorted((o1, o2) -> o2.getValue() - o1.getValue()).collect(Collectors.toList());

        int tmp = 0;
        for (var item : li) {
            tmp += item.getValue();
            answer++;
            if (tmp >= k) {
                break;
            }
        }

        return answer;
    }

}
