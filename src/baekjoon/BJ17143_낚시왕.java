package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BJ17143_낚시왕 {
    static final int UP = 0, DOWN = 1, RIGHT = 2, LEFT = 3;
    static int R, C, M;
    static int[][] moves = new int[][]{{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    static List<Shark> sharks;
    static King king;
    static Shark[][] board;

    public static void main(String[] args) throws IOException {
        init();
        while (king.c < C - 1) {
            king.move();
            king.fishing();
            moveSharks();
        }
        System.out.println(king.score);
    }

    private static void moveSharks() {
        Shark[][] tBoard = new Shark[R][C];
        for (Shark shark :
                sharks) {
            shark.r += moves[shark.direction][0] * shark.vel;
            shark.c += moves[shark.direction][1] * shark.vel;

//            while (shark.r < 0 || shark.r >= R || shark.c < 0 || shark.c >= C) {
            if (shark.r < 0 || shark.r >= R || shark.c < 0 || shark.c >= C) {
                changeSharkDirection(shark);
            }

            if (tBoard[shark.r][shark.c] == null) {
                tBoard[shark.r][shark.c] = shark;
            } else {
                if (shark.size > tBoard[shark.r][shark.c].size) {
                    tBoard[shark.r][shark.c] = shark;
                }
            }
        }

        sharks = new ArrayList<>();
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (tBoard[r][c] != null)
                    sharks.add(tBoard[r][c]);
            }
        }
        board = tBoard;
    }

    private static void changeSharkDirection(Shark shark) {
        int a, b;
        switch (shark.direction) {
            case UP:
                a = Math.abs(shark.r) / (R - 1);
                b = Math.abs(shark.r) % (R - 1);
                if (a % 2 == 0) {
                    shark.r = b;
                    shark.direction = DOWN;
                } else {
                    shark.r = R - 1 - b;
                }
//                shark.r = Math.abs(shark.r);
//                shark.direction = DOWN;
                break;
            case DOWN:
                a = (shark.r - (R - 1)) / (R - 1);
                b = (shark.r - (R - 1)) % (R - 1);
                if (a % 2 == 0) {
                    shark.r = R - 1 - b;
                    shark.direction = UP;
                } else {
                    shark.r = b;
                }
//                shark.r = R - 1 + (R - 1 - shark.r);
//                shark.direction = UP;
                break;
            case LEFT:
                a = Math.abs(shark.c) / (C - 1);
                b = Math.abs(shark.c) % (C - 1);
                if (a % 2 == 0) {
                    shark.c = b;
                    shark.direction = RIGHT;
                } else {
                    shark.c = C - 1 - b;
                }
//                shark.c = Math.abs(shark.c);
//                shark.direction = RIGHT;
                break;
            case RIGHT:
                a = (shark.c - (C - 1)) / (C - 1);
                b = (shark.c - (C - 1)) % (C - 1);
                if (a % 2 == 0) {
                    shark.c = C - 1 - b;
                    shark.direction = LEFT;
                } else {
                    shark.c = b;
                }
//                shark.c = C - 1 + (C - 1 - shark.c);
//                shark.direction = LEFT;
        }
    }

    private static void init() throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new Shark[R][C];
        sharks = new ArrayList<>();
        king = new King();
        king.c = -1;
        king.score = 0;

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken()) - 1;
            int z = Integer.parseInt(st.nextToken());
            Shark shark = new Shark(r, c, s, z, d);
            sharks.add(shark);
            board[r][c] = shark;
        }
    }

    static class King {
        int c, score;

        public void move() {
            this.c++;
        }

        public void fishing() {
            for (int r = 0; r < R; r++) {
                if (board[r][c] != null) {
                    Shark shark = board[r][c];
                    this.score += shark.size;
                    sharks.remove(shark);
                    board[r][c] = null;
                    break;
                }
            }
        }
    }

    static class Shark {
        int r, c, vel, size, direction;

        public Shark(int r, int c, int vel, int size, int direction) {
            this.r = r;
            this.c = c;
            this.vel = vel;
            this.size = size;
            this.direction = direction;
        }
    }
}
