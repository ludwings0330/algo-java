package codetree.novicemid.시뮬레이션.날짜와시간계산;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 요일맞추기 {
    public static void main(String[] args) throws IOException {
        int[] num_of_days = new int[]{0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        String[] days = new String[]{"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final StringTokenizer st = new StringTokenizer(br.readLine());

        int m1 = Integer.parseInt(st.nextToken());
        int d1 = Integer.parseInt(st.nextToken());
        int m2 = Integer.parseInt(st.nextToken());
        int d2 = Integer.parseInt(st.nextToken());
        int day1 = d1;
        int day2 = d2;
        for (int i = 0; i < m1; i++) {
            day1 += num_of_days[i];
        }
        for (int i = 0; i < m2; i++) {
            day2 += num_of_days[i];
        }

        int gap = ((day2 - day1)%7+7)%7;
        System.out.println(days[gap]);
    }
}
