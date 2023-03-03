package codetree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class gcd {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        System.out.println(gcd(n, m));

    }

    public static int gcd(int a, int b) {
        if (a == 0) {
            return b;
        }
        if (a < b) {
            return gcd(b, a);
        }
        return gcd(a % b, b);
    }

}
