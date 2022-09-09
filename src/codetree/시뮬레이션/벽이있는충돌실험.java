package codetree.시뮬레이션;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 벽이있는충돌실험 {
    static int T, N, M;
    static int[][] count, nextCount;
    static List<Pair> pairs;
    static Map<String, int[]> moves = new HashMap<>();

    public static void main(String[] args) throws IOException {
        moves.put("U", new int[]{-1, 0});
        moves.put("D", new int[]{1, 0});
        moves.put("R", new int[]{0, 1});
        moves.put("L", new int[]{0, -1});

        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        while (T > 0) {
            T--;
            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            pairs = new ArrayList<>();
            count = new int[N][N];
            nextCount = new int[N][N];

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int r = Integer.parseInt(st.nextToken()) - 1;
                int c = Integer.parseInt(st.nextToken()) - 1;
                String d = st.nextToken();

                pairs.add(new Pair(r, c, d));
            }

            // 아주 오랜시간이 흐른 후에도 여전히 격자 안에 남아있는 구슬의 개수
            // 그.. 순회하면서 이미 방문했던 적 있는 모습이라면 끝낸다.
            simulate();

            System.out.println(pairs.size());
        }
    }

    private static void simulate() {
        int k = 0;
        while (k <= 2 * N) {
            k++;
            moveAll();
        }
    }

    private static void moveAll() {
        List<Pair> nextPairs = new ArrayList<>();

        for (Pair pair :
                pairs) {
            int nr = pair.r + moves.get(pair.d)[0];
            int nc = pair.c + moves.get(pair.d)[1];
            if (!inRange(nr, nc)) {
                String nd;
                switch (pair.d) {
                    case "U":
                        pair.d = "D";
                        break;
                    case "D":
                        pair.d = "U";
                        break;
                    case "L":
                        pair.d = "R";
                        break;
                    case "R":
                        pair.d = "L";
                }
                nextPairs.add(pair);
                nextCount[pair.r][pair.c]++;
            } else {
                nextPairs.add(new Pair(nr, nc, pair.d));
                nextCount[nr][nc]++;
            }
        }

        pairs.clear();
        for (Pair pair :
                nextPairs) {
            if (nextCount[pair.r][pair.c] >= 2)
                continue;
            count[pair.r][pair.c] = 1;
            pairs.add(pair);
        }
        for (Pair pair :
                nextPairs) {
            nextCount[pair.r][pair.c] = 0;
        }
    }

    private static boolean inRange(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c < N;
    }

    private static class Pair {
        int r, c;
        String d;

        public Pair(int r, int c, String d) {
            this.r = r;
            this.c = c;
            this.d = d;
        }

        @Override
        public String toString() {
            return "Pair{" +
                    "r=" + r +
                    ", c=" + c +
                    ", d='" + d + '\'' +
                    '}';
        }
    }
}
