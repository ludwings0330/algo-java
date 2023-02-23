package codetree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class 소삼형제 {

    static int N;

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        Map<Set<String>, Integer> counter = new HashMap<>();

        for (int i = 0; i < N; i++) {
            final String[] cows = br.readLine().split(" ");
            final Set<String> cowsSet = Arrays.stream(cows).sorted().collect(Collectors.toSet());
            counter.merge(cowsSet, 1, (v1, v2) -> v1 + 1);
        }

        int max = 0;
        for (int v :
                counter.values()) {
            max = Math.max(max, v);
        }

        System.out.println(max);
    }

}
