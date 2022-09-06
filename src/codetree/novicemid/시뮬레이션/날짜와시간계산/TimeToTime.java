package codetree.novicemid.시뮬레이션.날짜와시간계산;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class TimeToTime {
    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final StringTokenizer st = new StringTokenizer(br.readLine());
        int[] time = new int[4];

        for (int i = 0; i < 4; i++) {
            time[i] = Integer.parseInt(st.nextToken());
        }
        int ans = time[2] * 60 + time[3] - time[0] * 60 - time[1];
        System.out.println(ans);
    }
}
