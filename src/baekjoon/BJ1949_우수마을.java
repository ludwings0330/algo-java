package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BJ1949_우수마을 {
    static int N;
    static int[] populations;
    static List<Integer>[] graph;
    static int[][] cache;

    public static void main(String[] args) throws IOException {
        init();
        System.out.println(solve(0, 0, 0));
    }

    /**
     * current 가 선택
     * -> 자식들은 모두 선택안함
     * current 선택 안함
     * -> 자식은 자식이 있는 경우 ( 길이가 1 이상)
     * -> 선택함
     * -> 선택안함
     * -> 자식이 없는 경우 ( 길이가 1)
     * -> 선택함
     */
    private static int solve(int parent, int current, int isSelected) {
        if (current == 0)
            return Math.max(solve(0, 1, 1), solve(0, 1, 0));

        if (cache[current][isSelected] != -1)
            return cache[current][isSelected];

        cache[current][isSelected] = (isSelected == 1) ? populations[current] : 0;
//        cache[current][isSelected] = 0;

        // 부모가 선택되었다면
        if (isSelected == 1) {
            // 부모가 선택 -> 자식들은 모두 선택 안한 결과를 더함
            for (var next :
                    graph[current]) {
                if (next == parent)
                    continue;
                cache[current][isSelected] += solve(current, next, 0);
            }
        } else {
            // 부모가 선택되지 않았으면 -> 자식이 있으면 선택하거나 안하거나
            // -> 자식이 없으면 무조건 선택

            for (var next :
                    graph[current]) {
                // 자식이 없을때는 자동으로 걸러짐
                if (next == parent)
                    continue;
                // 자식의 자식이 존재한다면 해당 자식은 선택하지 않거나 선택할 수 있음
                cache[current][isSelected] += Math.max(solve(current, next, 0), solve(current, next, 1));
            }
        }

        return cache[current][isSelected];
    }

    private static void init() throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        populations = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            populations[i + 1] = Integer.parseInt(st.nextToken());
        }

        graph = new List[N + 1];
        for (int i = 0; i < N + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());

            int fr = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            graph[fr].add(to);
            graph[to].add(fr);
        }

        cache = new int[N + 1][];
        for (int i = 0; i < N + 1; i++) {
            cache[i] = new int[]{-1, -1};
        }
    }
}
