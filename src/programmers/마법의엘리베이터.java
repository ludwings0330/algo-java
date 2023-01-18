package programmers;

public class 마법의엘리베이터 {

    public static void main(String[] args) {
        System.out.println(solution(16));
        System.out.println(solution(2554));
    }

    public static int solution(int storey) {
        int answer = 0;

        int tmp = storey;
        while (tmp > 0) {
            // 1. 해당 자리를 올리거나
            // 2. 해당 자리를 내리거나
            int n = tmp % 10;
            if (n > 5) {
                tmp += 10 - n;
                answer += 10 - n;
            } else if (n == 5 && (tmp / 10) % 10 >= 5) {
                tmp += 10 - n;
                answer += 10 - n;
            } else {
                // 그냥 빼기
                answer += n;
            }
            tmp /= 10;
        }

        return answer;
    }

}
