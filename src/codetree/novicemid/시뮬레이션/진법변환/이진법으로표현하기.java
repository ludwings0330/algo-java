package codetree.novicemid.시뮬레이션.진법변환;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 이진법으로표현하기 {
    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        StringBuilder sb = new StringBuilder();
        while (N > 0) {
            sb.append(N%2);
            N /= 2;
        }
        String ans = sb.reverse().toString();
        System.out.println((ans.length()==0)?0:ans);
    }
}
