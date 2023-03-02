package swexpert.b형;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class 동아리실관리하기 {

    static final int MOD = 1_000_000_007;
    static int TC;
    static String managers;
    static StringBuilder sb = new StringBuilder();
    static Set<Integer> potentials = new HashSet<>();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static long[][] cache;

    public static void main(String[] args) throws IOException {
        for (int i = 1; i < 16; i++) {
            potentials.add(i);
        }

        TC = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < TC; tc++) {
            sb.append("#").append(tc + 1).append(" ");
            managers = br.readLine();

            sb.append(solve()).append('\n');
        }

        System.out.println(sb.toString());
    }

    private static long solve() {
        cache = new long[managers.length()][16];

        for (int day = 0; day < managers.length(); day++) {
            int manager = 1 << (managers.charAt(day) - 'A');

            for (int todayMembers :
                    potentials) {
                if (day == 0 && (todayMembers & 1) > 0 && (todayMembers & manager) > 0) {
                    cache[day][todayMembers] = 1;
                    continue;
                }

                if (day == 0) {
                    continue;
                }

                for (int tomorrowMembers :
                        potentials) {
                    if ((tomorrowMembers & manager) > 0 && (todayMembers & tomorrowMembers) > 0) {
                        cache[day][tomorrowMembers] += cache[day - 1][todayMembers];
                        cache[day][tomorrowMembers] %= MOD;
                    }
                }
            }

        }

        return Arrays.stream(cache[cache.length - 1]).sum() % MOD;
    }

}
