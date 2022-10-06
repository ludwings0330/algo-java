package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ9205_맥주마시면서걸어가기 {
    static int N;
    static Pos start;
    static List<Pos> store;
    static Pos end;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int TC = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= TC; tc++) {
            if (solve())
                sb.append("happy\n");
            else
                sb.append("sad\n");
        }
        System.out.println(sb.toString());
    }

    private static boolean solve() throws IOException {
        init();
        return bfs();
    }

    private static boolean bfs() {
        Deque<Pos> dq = new LinkedList<>();
        boolean[] visited = new boolean[N + 2];
        dq.offerLast(start);
        visited[start.id] = true;
        while (!dq.isEmpty()) {
            Pos current = dq.pollFirst();
            if (Math.abs(current.r - end.r) + Math.abs(current.c - end.c) <= 1000)
                return true;

            for (var next :
                    store) {
                if (!visited[next.id] &&
                        Math.abs(current.r - next.r) + Math.abs(current.c - next.c) <= 1000) {
                    dq.offerLast(next);
                    visited[next.id] = true;
                }
            }
        }

        return false;
    }

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());
        int id = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        start = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), id++);
        store = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            store.add(new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), id++));
        }
        st = new StringTokenizer(br.readLine());
        end = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), id++);
    }

    static class Pos {
        int r, c;
        int id;

        public Pos(int r, int c, int id) {
            this.r = r;
            this.c = c;
            this.id = id;
        }
    }
}
