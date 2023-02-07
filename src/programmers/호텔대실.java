package programmers;

import java.util.Arrays;

public class 호텔대실 {

    public static void main(String[] args) {
        System.out.println(solution(
                new String[][] { { "00:00", "12:00" }, { "12:10", "23:59" } }));
    }

    public static int solution(String[][] book_time) {
        int answer = 0;
        int[] cnt = new int[24 * 60 + 30];
        for (var time :
                book_time) {
            for (int i = getMinute(time[0]); i <= getMinute(time[1]) + 9; i++) {
                cnt[i]++;
            }
        }
        return Arrays.stream(cnt).max().getAsInt();
    }

    public static int getMinute(String query) {
        return Integer.parseInt(query.substring(0, 2)) * 60 + Integer.parseInt(query.substring(3, 5));
    }

}
