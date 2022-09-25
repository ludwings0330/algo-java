package Test.KAKAO2023BLINDTEST;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class A {
    public static int[] solution(String today, String[] terms, String[] privacies) {
        ArrayList<Integer> answer = new ArrayList<>();

        // parse
        HashMap<String, Integer> hashTerm = new HashMap<>();
        for (String term :
                terms) {
            final String[] split = term.split(" ");
            hashTerm.put(split[0], Integer.parseInt(split[1]));
        }

        Date td = new Date(today);

        for (int i = 0; i < privacies.length; i++) {
            boolean flag = true;
            final String[] s = privacies[i].split(" ");
            Date pv = new Date(s[0]);
            String p = s[1];

            pv.m += hashTerm.get(p);

            // 오늘이 더 크다는 것은 약관이 지낫다는 뜻
            if (td.y * 12 * 28 + td.m * 28 + td.d >= pv.y * 12 * 28 + pv.m * 28 + pv.d) {
                answer.add(i + 1);
            }
        }

        return answer.stream().mapToInt(n -> n).toArray();
    }

    public static void main(String[] args) {
        final int[] solution = solution("2020.01.01", new String[]{"A 6", "B 12", "C 3", "D 5"}, new String[]{"2019.08.02 D", "2021.05.02 A", "2021.07.01 B", "2022.02.19 C", "2022.02.20 C"});
        System.out.println(Arrays.stream(solution).toArray());
    }

    static class Date {
        int y, m, d;

        public Date(String date) {
            final String[] split = date.split("[.]");
            this.y = Integer.parseInt(split[0]);
            this.m = Integer.parseInt(split[1]);
            this.d = Integer.parseInt(split[2]);
        }
    }
}
