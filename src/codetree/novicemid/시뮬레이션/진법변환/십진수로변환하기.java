package codetree.novicemid.시뮬레이션.진법변환;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 십진수로변환하기 {
    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] nums = br.readLine().toCharArray();

        int k = 0;
        int ans = 0;
        for (int i = nums.length-1; i >= 0; i--) {
            ans += Math.pow(2, k) * (nums[i]-'0');
            k++;
        }
        System.out.println(ans);
    }
}
