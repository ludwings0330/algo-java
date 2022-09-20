package codetree.novicemid.DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 뿌요뿌요 {
    static int N;
    static int[][] board;
    static boolean[][] visited;
    static int[][] moves = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    static int totalCount = 0;
    static int maxSize = 0;

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        board = new int[N][N];
        visited = new boolean[N][N];

        for (int r = 0; r < N; r++) {
            final StringTokenizer st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                board[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (!visited[r][c]) {
                    visited[r][c] = true;
                    int size = dfs(r, c);
                    maxSize = Math.max(maxSize, size);

                    if (size >= 4) {
                        totalCount++;
                    }
                }
            }
        }
        System.out.println(totalCount + " " + maxSize);
    }

    private static int dfs(int r, int c) {
        int ret = 1;

        for (int i = 0; i < moves.length; i++) {
            int nr = r + moves[i][0];
            int nc = c + moves[i][1];

            if (0 <= nr && nr < N && 0 <= nc && nc < N && !visited[nr][nc] && board[nr][nc] == board[r][c]) {
                visited[nr][nc] = true;
                ret += dfs(nr, nc);
            }
        }

        return ret;
    }
}
