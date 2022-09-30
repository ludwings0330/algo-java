package baekjoon;

import java.util.Arrays;
import java.util.Scanner;

public class BJ1463_1로만들기 {
    static int[] cache;

    public static void main(String[] args) {
        final Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        cache = new int[n + 1];
        Arrays.fill(cache, -1);
        System.out.println(recursiveSolve(n));
    }

    private static int recursiveSolve(int n) {
        if (n == 1) return 0;
        if (cache[n] != -1) return cache[n];

        cache[n] = Integer.MAX_VALUE;

        if (n % 3 == 0)
            cache[n] = Math.min(cache[n], recursiveSolve(n / 3) + 1);
        if (n % 2 == 0)
            cache[n] = Math.min(cache[n], recursiveSolve(n / 2) + 1);
        cache[n] = Math.min(cache[n], recursiveSolve(n - 1) + 1);

        return cache[n];
    }
}
