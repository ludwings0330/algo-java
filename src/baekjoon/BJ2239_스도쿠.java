package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ2239_스도쿠 {
    static boolean[][] visitedRow;
    static boolean[][] visitedCol;
    static boolean[][] visitedArea;
    static int[][] board;
    static int R, C;

    public static void main(String[] args) throws IOException {
        init();
        solve(0);

        StringBuilder sb = new StringBuilder();
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                sb.append(board[r][c]);
            }
            sb.append('\n');
        }

        System.out.println(sb.toString());
    }

    private static boolean solve(int idx) {
        int r = idx / 9;
        int c = idx % 9;

        if (r == 9)
            return true;

        if (board[r][c] != 0) {
            if (solve(idx + 1))
                return true;
        } else {
            for (int num = 1; num <= 9; num++) {
                if (visitedRow[r][num] || visitedCol[c][num] || visitedArea[getArea(r, c)][num])
                    continue;
                board[r][c] = num;
                visitedRow[r][num] = visitedCol[c][num] = visitedArea[getArea(r, c)][num] = true;

                if (solve(idx + 1))
                    return true;

                visitedRow[r][num] = visitedCol[c][num] = visitedArea[getArea(r, c)][num] = false;
                board[r][c] = 0;
            }
        }

        return false;
    }

    private static void init() throws IOException {
        R = 9;
        C = 9;

        visitedRow = new boolean[R][10];
        visitedCol = new boolean[R][10];
        visitedArea = new boolean[R][10];
        board = new int[R][C];
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int r = 0; r < R; r++) {
            char[] row = br.readLine().toCharArray();

            for (int c = 0; c < C; c++) {
                board[r][c] = row[c] - '0';
                visitedRow[r][board[r][c]] = true;
                visitedCol[c][board[r][c]] = true;
                visitedArea[getArea(r, c)][board[r][c]] = true;
            }
        }

    }

    private static int getArea(int r, int c) {
        return (r / 3) * 3 + c / 3;
    }
}
