package codetree.novicemid.구간칠하기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 최대로겹치는구간 {
    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] blocks = new int[300];
        for (int i = 0; i < N; i++) {
            final StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) + 100;
            int b = Integer.parseInt(st.nextToken()) + 100;
            for (int j = a; j < b; j++) {
                blocks[j] ++;
            }
        }
        int ans = 0;
        for (int i = 0; i < 300; i++) {
            System.out.print(blocks[i]);
            ans = Math.max(ans, blocks[i]);
        }
        System.out.println(ans);
    }
}
