package swexpert.b형;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class 새로운불면증치료법 {

    static int T;
    static int N;
    static StringBuilder sb = new StringBuilder();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        init();
        for (int tc = 0; tc < T; tc++) {
            solve(tc + 1);
        }

        System.out.println(sb.toString());
    }

    private static void solve(int tc) throws IOException {
        sb.append("#").append(tc).append(' ');

        N = Integer.parseInt(br.readLine());
        HashSet<Integer> set = new HashSet<>();

        int x = 0;
        while (set.size() < 10) {
            x++;
            int num = N * x;

            while (num > 0) {
                set.add(num % 10);
                num /= 10;
            }
        }

        sb.append(N * x).append('\n');
    }

    private static void init() throws IOException {
        T = Integer.parseInt(br.readLine());
    }

}
