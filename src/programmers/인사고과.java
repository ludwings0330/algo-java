package programmers;

import java.util.Arrays;

public class 인사고과 {

    public static void main(String[] args) {
        solution(new int[][] { { 2, 2 }, { 1, 4 }, { 3, 2 }, { 3, 2 }, { 2, 1 } });
    }

    public static int solution(int[][] scores) {
        int answer = 1;
        // 근무 태도 내림차순, 동료평가 오름차순
        int wanhoAttitude = scores[0][0];
        int wanhoReview = scores[0][1];
        int wanhoTotal = wanhoAttitude + wanhoReview;
        Arrays.sort(scores, (s1, s2) -> (s1[0] == s2[0]) ? s1[1] - s2[1] : s2[0] - s1[0]);

        int maxReview = 0;
        // 근무 태도는 항상 더 작거나 같음
        for (int[] score :
                scores) {
            if (score[1] < maxReview) {
                // 제외 대상
                if (score[0] == wanhoAttitude && score[1] == wanhoReview) {
                    return -1;
                }
            } else {
                // 인센티브 대상
                if (score[0] + score[1] > wanhoTotal) {
                    answer++;
                }
                maxReview = Math.max(maxReview, score[1]);
            }
        }

        return answer;
    }

}
