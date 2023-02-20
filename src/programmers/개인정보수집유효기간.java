package programmers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class 개인정보수집유효기간 {

    public static void main(String[] args) {

    }

    public static int[] solution(String today, String[] terms, String[] privacies) {
        List<Integer> answer = new ArrayList<>();
        Map<Character, Integer> myTerms = new HashMap<>();

        for (String term :
                terms) {
            Character key = term.charAt(0);
            Integer value = Integer.parseInt(term.substring(2));
            myTerms.put(key, value);
        }

        int iToday = calculateDay(today);
        for (int i = 0; i < privacies.length; i++) {
            String privacy = privacies[i];
            if (iToday > calculateDay(privacy.substring(0, 10)) + myTerms.get(privacy.charAt(11)) * 28) {
                answer.add(i + 1);
            }
        }
        return answer.stream().mapToInt(i -> i).toArray();
    }

    public static int calculateDay(String date) {
        int year = Integer.parseInt(date.substring(0, 4));
        int month = Integer.parseInt(date.substring(5, 7));
        int day = Integer.parseInt(date.substring(8));

        return (year * 12 + month) * 28 + day;
    }

}
