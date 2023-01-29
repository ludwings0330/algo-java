package programmers;

public class 유사칸토어비트열 {

    public static void main(String[] args) {
        System.out.println(solution(2, 4, 17));
    }

    public static int solution(int n, long l, long r) {
        int answer = 0;

        recursiveSolve(n, l, r, 1, 1, 1, 0);

        return answer;
    }

    public static long recursiveSolve(int N, long queryLeft, long queryRight,
                                      int n, long currentLeft, long currentRight,
                                      int head) {
        long ret = 0;

        return ret;
    }

}
