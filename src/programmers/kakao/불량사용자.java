package programmers.kakao;

import java.util.Arrays;
import java.util.HashSet;

public class 불량사용자 {
    static HashSet<String> set = new HashSet<>();

    public static void main(String[] args) {
        System.out.println(solution(new String[]{"frodo", "fradi", "crodo", "abc123", "frodoc"}, new String[]{"fr*d*", "abc1**"}));
    }

    // 순서 X, 중복 X -> combination
    public static int solution(String[] user_id, String[] banned_id) {
        int[][] candidate = match(user_id, banned_id);
        boolean[] visited = new boolean[user_id.length];

        combination(candidate, 0, 0, visited);

        return set.size();
    }

    private static int combination(int[][] candidate, int length, int process, boolean[] visited) {
        if (process == candidate.length) {
            final StringBuilder sb = new StringBuilder();

            for (int i = 0; i < visited.length; i++) {
                if (visited[i])
                    sb.append(i).append("/");
            }
            set.add(sb.toString());
            return 1;
        }

        int ret = 0;
        for (int i = 0; i < candidate[process].length; i++) {
            if (candidate[process][i] == -1)
                break;

            if (visited[candidate[process][i]])
                continue;

            visited[candidate[process][i]] = true;
            ret += combination(candidate, length, process + 1, visited);
            visited[candidate[process][i]] = false;
        }

        return ret;
    }

    private static int[][] match(String[] user_id, String[] banned_id) {
        int[][] numbers = new int[banned_id.length][user_id.length];
        for (int i = 0; i < numbers.length; i++) {
            Arrays.fill(numbers[i], -1);
        }

        for (int i = 0; i < banned_id.length; i++) {
            int idx = 0;
            for (int j = 0; j < user_id.length; j++) {
                if (isMatch(user_id[j], banned_id[i])) {
                    numbers[i][idx++] = j;
                }
            }
        }

        return numbers;
    }

    private static boolean isMatch(String uId, String bId) {
        if (uId.length() != bId.length())
            return false;

        for (int i = 0; i < uId.length(); i++)
            if (bId.charAt(i) != '*' && uId.charAt(i) != bId.charAt(i))
                return false;

        return true;
    }
}
