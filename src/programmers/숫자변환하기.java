package programmers;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class 숫자변환하기 {

    public static void main(String[] args) {
        System.out.println(solution(10, 40, 5));
        System.out.println(solution(10, 40, 30));
        System.out.println(solution(2, 5, 4));
    }

    public static int solution(int x, int y, int n) {
        int answer = -1;
        Set<Integer> visited = new HashSet<>();

        Queue<int[]> que = new LinkedList<>();
        que.add(new int[] { x, 0 });
        visited.add(x);

        while (!que.isEmpty()) {
            final int[] current = que.poll();
            if (current[0] == y) {
                answer = current[1];
                break;
            }
            if (current[0] > 1_000_000) {
                continue;

            }
            int next = current[0] + n;
            int count = current[1] + 1;
            if (!visited.contains(next)) {
                que.add(new int[] { next, count });
                visited.add(next);
            }
            next = current[0] * 2;
            if (!visited.contains(current[0] * 2)) {
                que.add(new int[] { next, count });
                visited.add(next);
            }
            next = current[0] * 3;
            if (!visited.contains(current[0] * 3)) {
                que.add(new int[] { next, count });
                visited.add(next);
            }
        }

        return answer;
    }

}
