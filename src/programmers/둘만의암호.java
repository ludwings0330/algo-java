package programmers;

import java.util.HashSet;
import java.util.Set;

public class 둘만의암호 {

    public static void main(String[] args) {
        System.out.println(solution("aukks", "wbqd", 5));
    }

    public static String solution(String s, String skip, int index) {
        Set<Character> skipMap = new HashSet<>();
        final StringBuilder sb = new StringBuilder();

        for (char ch :
                skip.toCharArray()) {
            skipMap.add(ch);
        }

        for (char ch :
                s.toCharArray()) {
            int count = 0;
            while (count < index) {
                ch = (char) Math.max(++ch % ('z' + 1), 'a');
                count += (skipMap.contains(ch)) ? 0 : 1;
            }
            sb.append(ch);
        }

        return sb.toString();
    }

}
