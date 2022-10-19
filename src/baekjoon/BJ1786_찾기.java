package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BJ1786_찾기 {
    static String origin;
    static String search;
    static int[] table;

    public static void main(String[] args) throws IOException {
        init();
        table = initKmpTable(search);

        List<Integer> idx = kmp(origin, search);
        final StringBuilder sb = new StringBuilder();
        for (int i :
                idx) {
            sb.append(i + 1).append(' ');
        }
        System.out.println(idx.size());
        System.out.println(sb.toString());
    }

    private static List<Integer> kmp(String origin, String search) {
        int n = origin.length();
        int m = search.length();

        int begin = 0;
        int matched = 0;
        List<Integer> ret = new ArrayList<>();
        while (begin <= n - m) {
            if (matched < m && origin.charAt(begin + matched) == search.charAt(matched)) {
                matched++;
                if (matched == m)
                    ret.add(begin);
            } else {
                if (matched == 0)
                    begin++;
                else {
                    begin += matched - table[matched - 1];
                    matched = table[matched - 1];
                }
            }
        }

        return ret;
    }

    private static int[] initKmpTable(String search) {
        int n = search.length();
        int[] table = new int[n];
        int begin = 1;
        int matched = 0;

        while (begin + matched < n) {
            if (search.charAt(matched) == search.charAt(begin + matched)) {
                matched++;
                table[begin + matched - 1] = matched;
            } else {
                if (matched == 0) {
                    begin++;
                } else {
                    begin += matched - table[matched - 1];
                    matched = table[matched - 1];
                }
            }
        }

        return table;
    }

    private static void init() throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        origin = br.readLine();
        search = br.readLine();
    }
}
