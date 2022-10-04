package programmers.dev매칭;

import java.util.Arrays;
import java.util.HashSet;

public class A {
    // 길이 3 이상 6 이하 길이
    // 숫자 0이상 6이하 길이
    // 숫자맨앞자리는 0 x

    public static String solution(String[] registered_list, String new_id) {
        String answer = "";

        HashSet<String> hashSet = new HashSet<>(Arrays.asList(registered_list));

        while (hashSet.contains(new_id)) {
            StringBuilder sb = new StringBuilder();
            int num = 0;
            for (int i = 0; i < new_id.length(); i++) {
                if ('0' <= new_id.charAt(i) && new_id.charAt(i) <= '9') {
                    num = Integer.parseInt(new_id.substring(i));
                    break;
                }
                sb.append(new_id.charAt(i));
            }
            num++;
            sb.append(num);
            new_id = sb.toString();
        }

        return new_id;
    }

    public static void main(String[] args) {

    }
}
