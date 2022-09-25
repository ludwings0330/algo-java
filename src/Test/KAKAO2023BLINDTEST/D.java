package Test.KAKAO2023BLINDTEST;

public class D {
    public static int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];
        int idx = 0;
        for (long number :
                numbers) {
            if (solve(number)) {
                answer[idx++] = 1;
            } else {
                answer[idx++] = 0;
            }
        }
        return answer;
    }

    private static boolean solve(long number) {
        boolean result = true;
        final StringBuilder sb = new StringBuilder();
        int left = 0;
        int right = 0;
        while (number > 0) {
            sb.append(number % 2);
            number /= 2;
            right++;
        }

        int level = 1;
        while (Math.pow(2, level) - 1 < right) {
            level += 1;
        }
        while (Math.pow(2, level) - 1 > right) {
            sb.append(0);
            right++;
        }
        int[] sum = new int[sb.length()];
        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) == '1') {
                sum[i] = 1;
            } else {
                sum[i] = 0;
            }
            if (i >= 1) {
                sum[i] += sum[i - 1];
            }
        }

        return divide(left, right - 1, sb, sum);
    }

    private static boolean divide(int left, int right, StringBuilder sb, int[] sum) {
        if (left == right) {
            return true;
        }
        int mid = (left + right) / 2;
        if (sb.charAt(mid) == '1') {
            return divide(left, mid - 1, sb, sum) && divide(mid + 1, right, sb, sum);
        } else {
            //  부모가 0 이면 자식에 1 이 있어서는 안됨
            if (sum[right] - ((left >= 1) ? sum[left - 1] : 0) == 0) return true;
                // 자식에 1 이있으면 답이될 수 없음
            else return false;
        }
    }

    public static void main(String[] args) {
        solution(new long[]{14, 1, 2, 3, 4, 7, 5, 63, 111, 95});
    }
}
