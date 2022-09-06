package codetree.novicemid.시뮬레이션.날짜와시간계산;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 흐른날짜계산 {
    public static void main(String[] args) throws IOException {
        int[] num_of_days = new int[]{0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final StringTokenizer st = new StringTokenizer(br.readLine());
        int[] date = new int[4];
        for (int i = 0; i < 4; i++) {
            date[i] = Integer.parseInt(st.nextToken());
        }
        int ans = 0;
        for (int m = date[0]; m < date[2] ; m++) {
            ans += num_of_days[m];
        }
        ans -= date[1] - date[3] - 1;
        System.out.println(ans);
    }
}
