package programmers.summerwintercoding2018;

public class 기지국설치 {
    public static int solution(int n, int[] stations, int w) {
        int answer = 0;
        int base = 1;
        int size = 2 * w + 1;
        for (int station :
                stations) {
            int left = station - w;
            int right = station + w;
            int length = Math.max(0, left - base);
            answer += Math.ceil(length / (double) size);
            base = right + 1;
        }

        if (base <= n) {
            int length = Math.max(0, n - base + 1);
            answer += Math.ceil(length / (double) size);
        }

        return answer;
    }

    public static void main(String[] args) {
//        System.out.println(solution(11, new int[]{4, 11}, 1));
//        System.out.println(solution(16, new int[]{9}, 2));
        System.out.println(solution(10, new int[]{9}, 100));
    }
}
