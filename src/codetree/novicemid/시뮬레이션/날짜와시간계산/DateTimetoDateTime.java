package codetree.novicemid.시뮬레이션.날짜와시간계산;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DateTimetoDateTime {
    public static void main(String[] args) throws IOException {
        int[] num_of_days = new int[]{0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final StringTokenizer st = new StringTokenizer(br.readLine());

        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int m = c - 11;
        m += b*60 - 11*60;
        m += a*24*60 - 11*24*60;
        System.out.println((m>=0)?m:-1);
    }
}
