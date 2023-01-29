package programmers;

import java.util.Arrays;

public class 가장가까운글자 {

    public static void main(String[] args) {
        Arrays.stream(solution("banana")).forEach(System.out::println);
        Arrays.stream(solution("foobar")).forEach(System.out::println);
    }

    public static int[] solution(String s) {
        int[] answer = new int[s.length()];
        int[] check = new int['z' + 1];
        Arrays.fill(check, -1);

        int idx = 0;
        for (char ch :
                s.toCharArray()) {
            answer[idx] = (check[ch] == -1) ? -1 : idx - check[ch];
            check[ch] = idx;
            idx++;
        }

        return answer;
    }

}
