package codetree.novicemid.구간칠하기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 블럭쌓는명령2 {
    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] blocks = new int[N+1];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            for (int j = A; j <= B; j++) {
                blocks[j]++;
            }
        }
        int ans = 0;
        for (int i = 0; i < blocks.length; i++) {
            ans = Math.max(ans, blocks[i]);
        }
        System.out.println(ans);
    }
}
