package programmers;

public class 크기가작은부분문자열 {

    public static void main(String[] args) {
//        System.out.println(solution("3141592", "271"));
        System.out.println(solution("10203", "15"));
    }

    public static int solution(String t, String p) {
        int answer = 0;

        int len = p.length();
        Long pp = Long.valueOf(p);
        for (int i = 0; i < t.length() - p.length() + 1; i++) {
            if (Long.valueOf(t.substring(i, i + len)) <= pp) {
                answer++;
            }
        }

        return answer;
    }

}
