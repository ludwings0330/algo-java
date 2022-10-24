package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ4354_문자열제곱 {
    static int[] pi;
    static int ans;

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String pattern = br.readLine();
            int n = pattern.length();

            if (pattern.equals("."))
                break;
            pi = initKmpTable(pattern);
            if (ans == 0)
                System.out.println(1);
            else
                System.out.println(n / (n - ans));

            System.out.println();
        }
    }

    private static int[] initKmpTable(String pattern) {
        int n = pattern.length();
        int[] pi = new int[n];

        int begin = 1;
        int matched = 0;
        while (begin + matched < n) {
            if (pattern.charAt(begin + matched) == pattern.charAt(matched)) {
                matched++;
                pi[begin + matched - 1] = matched;
            } else {
                if (matched == 0)
                    begin++;
                else {
                    begin += matched - pi[matched - 1];
                    matched = pi[matched - 1];
                }
            }
        }

        return pi;
    }
}
