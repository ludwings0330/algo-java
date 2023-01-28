package programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class 무인도여행 {

    static int[][] moves = new int[][] { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

    public static void main(String[] args) {
        Arrays.stream(solution(new String[] { "X591X", "X1X5X", "X231X", "1XXX1" })).forEach(System.out::print);
    }

    public static int[] solution(String[] maps) {
        List<Integer> answer = new ArrayList<>();
        char[][] board = new char[maps.length][];

        for (int r = 0; r < maps.length; r++) {
            board[r] = maps[r].toCharArray();
        }

        boolean[][] visited = new boolean[board.length][board[0].length];

        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                if (board[r][c] == 'X' || visited[r][c]) {
                    continue;
                }

                answer.add(bfs(r, c, visited, board));

            }
        }

        if (answer.isEmpty()) {
            answer.add(-1);
        }
        return answer.stream().sorted().mapToInt(n -> n).toArray();
    }

    private static int bfs(int r, int c, boolean[][] visited, char[][] board) {
        int ret = 0;

        Stack<Pair> stack = new Stack<>();
        visited[r][c] = true;
        stack.push(new Pair(r, c));

        while (!stack.isEmpty()) {
            final Pair current = stack.pop();
            ret += board[current.r][current.c] - '0';
            for (int[] move :
                    moves) {
                int nr = current.r + move[0];
                int nc = current.c + move[1];

                if (0 > nr || nr >= visited.length || 0 > nc || nc >= visited[0].length || visited[nr][nc] || board[nr][nc] == 'X') {
                    continue;
                }

                visited[nr][nc] = true;
                stack.push(new Pair(nr, nc));
            }
        }

        return ret;
    }

    static class Pair {

        int r, c;

        public Pair(int r, int c) {
            this.r = r;
            this.c = c;
        }

    }

}
