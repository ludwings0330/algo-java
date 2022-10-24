package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BJ1701_Cubeditor {
    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int ret = 0;
        for (int i = 0; i < s.length() - 1; i++) {
            int[] table = initKmpTable(s.substring(i));
            ret = Math.max(ret, Arrays.stream(table).max().getAsInt());
        }

        System.out.println(ret);
    }

    private static int[] initKmpTable(String origin) {
        int N = origin.length();
        int[] table = new int[N];

        int begin = 1;
        int matched = 0;
        while (begin + matched < N) {
            if (origin.charAt(begin + matched) == origin.charAt(matched)) {
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
}
