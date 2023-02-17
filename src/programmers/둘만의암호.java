package programmers;

import java.util.ArrayList;
import java.util.List;

public class 둘만의암호 {

    public static void main(String[] args) {

    }

    public static String solution(String s, String skip, int index) {
        String answer = "";

        List<Character> list = new ArrayList<>();

        for (char ch : "abcdefghijklnmopqrstuvwxyz".toCharArray()) {
            if (!skip.contains(String.valueOf(ch))) {
                list.add(ch);
            }
        }

        return answer;
    }

}
