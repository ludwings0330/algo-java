package codetree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 진단4 {

    static int n;
    static int k;

    static int[] nums;

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        nums = new int[k];
        perm(0);

    }

    private static void perm(int count) {
        if (count == k) {
            for (int i = 0; i < k; i++) {
                System.out.print(nums[i] + " ");
            }
            System.out.println();
            return;
        }

        for (int i = n; i >= 1; i--) {
            nums[count] = i;
            perm(count + 1);
        }
    }

}
