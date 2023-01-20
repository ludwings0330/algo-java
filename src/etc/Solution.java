package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

    static long[][] comb = new long[20][20];

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // comb init
        comb[0][0] = 1;
        for (int n = 1; n < 20; n++) {
            comb[n][0] = 1;
            for (int r = 1; r < 20; r++) {
                comb[n][r] = comb[n - 1][r] + comb[n - 1][r - 1];
            }
        }

        long n = 0;
        do {
            n = Long.parseLong(br.readLine());
            System.out.println(solve(n));
        } while (n != -1);
    }

    public static long solve(long num) {

        final String strNum = String.valueOf(num);

        long answer = num - recursiveSolve(strNum);

        return answer;

//        return answer;
    }

    private static long recursiveSolve(String strNum) {
        if (strNum.length() == 2) {
            return (Integer.parseInt(strNum) >= 13) ? 1 : 0;
        }
        int k = strNum.length() - 1;
        int n = 2;
        int r = k - 2;
        int flag = 1;
        long ret = 0L;
        long firstNumber = strNum.charAt(0) - '0';

        while (r >= 0) {
            ret += (long) (comb[n + r - 1][r] * Math.pow(10, r) * flag);
            n += 2;
            r -= 2;
            flag *= -1;
        }

        ret *= firstNumber;
        ret += recursiveSolve(strNum.substring(1));
        return ret;
    }

}
