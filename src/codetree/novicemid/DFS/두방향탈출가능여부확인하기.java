package codetree.novicemid.DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Objects;
import java.util.Stack;
import java.util.StringTokenizer;

public class 두방향탈출가능여부확인하기 {
    static int R, C;
    static int[][] board;

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        board = new int[R][];
        for (int r = 0; r < R; r++) {
            board[r] = new int[C];
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < C; c++) {
                board[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        HashSet<Pair> visited = new HashSet<Pair>();
        Stack<Pair> stack = new Stack<>();
        stack.push(new Pair(0, 0));
        visited.add(stack.peek());
        int result = 0;
        int[][] moves = {{0, 1}, {1, 0}};
        while (!stack.isEmpty()) {
            Pair current = stack.pop();

            if (current.r == (R - 1) && current.c == (C - 1)) {
                result = 1;
                break;
            }

            for (int i = 0; i < moves.length; i++) {
                int nr = current.r + moves[i][0];
                int nc = current.c + moves[i][1];

                if (0 <= nr && nr < R && 0 <= nc && nc < C && board[nr][nc] == 1) {
                    final Pair pair = new Pair(nr, nc);
                    if (visited.contains(pair)) continue;
                    visited.add(pair);
                    stack.push(pair);
                }
            }
        }

        System.out.println(result);
    }

    private static class Pair {
        int r, c;

        public Pair(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return r == pair.r && c == pair.c;
        }

        @Override
        public int hashCode() {
            return Objects.hash(r, c);
        }
    }
}
