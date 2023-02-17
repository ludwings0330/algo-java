package programmers;

import java.util.ArrayDeque;
import java.util.Deque;

public class 미로탈출 {

    public int solution(String[] maps) {
        int startRow = 0;
        int startCol = 0;
        int leverRow = 0;
        int leverCol = 0;
        int endRow = 0;
        int endCol = 0;

        for (int r = 0; r < maps.length; r++) {
            for (int c = 0; c < maps[r].length(); c++) {
                switch (maps[r].charAt(c)) {
                    case 'S':
                        startRow = r;
                        startCol = c;
                        break;
                    case 'E':
                        endRow = r;
                        endCol = c;
                        break;
                    case 'L':
                        leverRow = r;
                        leverCol = c;
                        break;
                    default:
                }
            }
        }

        int distLever = bfs(startRow, startCol, leverRow, leverCol, maps);
        if (distLever == -1) {
            return -1;
        }
        int distEnd = bfs(leverRow, leverCol, endRow, endCol, maps);

        if (distEnd == -1) {
            return -1;
        }
        return distLever + distEnd;
    }

    public int bfs(int sr, int sc, int tr, int tc, String[] maps) {
        int rowSize = maps.length;
        int colSize = maps[0].length();
        boolean[][] visited = new boolean[rowSize][colSize];

        Deque<int[]> que = new ArrayDeque<>();
        visited[sr][sc] = true;
        que.add(new int[] { sr, sc, 0 });

        int[][] moves = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

        while (!que.isEmpty()) {
            int[] current = que.pollFirst();
            if (current[0] == tr && current[1] == tc) {
                return current[2];
            }
            for (int[] move :
                    moves) {
                int nr = current[0] + move[0];
                int nc = current[1] + move[1];
                if (nr < 0 || nr >= rowSize || nc < 0 || nc >= colSize || maps[nr].charAt(nc) == 'X' || visited[nr][nc]) {
                    continue;
                }
                visited[nr][nc] = true;
                que.add(new int[] { nr, nc, current[2] + 1 });
            }
        }

        return -1;
    }

}
