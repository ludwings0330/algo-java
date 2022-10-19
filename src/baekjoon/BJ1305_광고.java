package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ1305_광고 {
    static int L;
    static String origin;
    static int[] table;

    public static void main(String[] args) throws IOException {
        init();
        table = makeKmpTable();
        int n = origin.length();
        System.out.println(n - table[n - 1]);
    }

    private static int[] makeKmpTable() {
        int n = origin.length();
        int[] table = new int[n];
        int begin = 1;
        int matched = 0;

        while (begin + matched < n) {
            if (origin.charAt(matched) == origin.charAt(begin + matched)) {
                matched++;
                table[begin + matched - 1] = matched;
            } else {
                if (matched == 0)
                    begin++;
                else {
                    begin += matched - table[matched - 1];
                    matched = table[matched - 1];
                }
            }
        }

        return table;
    }

    private static void init() throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        L = Integer.parseInt(br.readLine());
        origin = br.readLine();
    }
}
