package programmers;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class 뒤에있는큰수찾기 {

    public static void main(String[] args) {
        Arrays.stream(solution(new int[] { 2, 3, 3, 5 })).forEach(System.out::print);
        System.out.println();
        Arrays.stream(solution(new int[] { 9, 1, 5, 3, 6, 2 })).forEach(System.out::print);

    }

    public static int[] solution(int[] numbers) {
        int[] answer = new int[numbers.length];
        Deque<Integer> dq = new ArrayDeque<>();

        int i = numbers.length - 1;
        while (i >= 0) {
            while (!dq.isEmpty() && dq.peekFirst() <= numbers[i]) {
                dq.pollFirst();
            }

            answer[i] = (dq.isEmpty()) ? -1 : dq.peekFirst();
            dq.addFirst(numbers[i]);
            i--;
        }

        return answer;
    }

}
