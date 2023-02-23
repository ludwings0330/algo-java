package codetree.크리스마스코딩퀴즈2022;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class E_산타의텔레포트 {

    static int R, C;
    static int[][] board;
    static Map<Integer, ArrayList<int[]>> teleport = new HashMap<>();
    static int[] gift;

    public static void main(String[] args) throws IOException {
        init();
        int distGift = getMinEnergy(new int[] { 0, 0 }, gift);
        if (distGift == -1) {
            System.out.println(-1);
            return;
        }
        int distGoal = getMinEnergy(gift, new int[] { R - 1, C - 1 });
        if (distGoal == -1) {
            System.out.println(-1);
            return;
        }
        System.out.println(distGift + distGoal);
    }

    private static int getMinEnergy(int[] from, int[] to) {
        int ret = -1;
        int[][] visited = new int[R][C];

        for (int r = 0; r < R; r++) {
            Arrays.fill(visited[r], Integer.MAX_VALUE);
        }

        int[][] moves = new int[][] { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

        Deque<int[]> que = new ArrayDeque<>();
        visited[from[0]][from[1]] = 0;
        que.add(from);

        while (!que.isEmpty()) {
            final int[] current = que.pollFirst();
            if (current[0] == to[0] && current[1] == to[1]) {
                continue;
            }
            int currentNum = board[current[0]][current[1]];

            // 순간이동 하지 않고 그냥 이동
            for (int[] move :
                    moves) {
                int nr = current[0] + move[0];
                int nc = current[1] + move[1];
                if (nr < 0 || nr >= R || nc < 0 || nc >= C) {
                    continue;
                }
                // 벽은 지날 수 없음
                if (board[nr][nc] == -1) {
                    continue;
                }
                if (visited[nr][nc] <= visited[current[0]][current[1]]) {
                    continue;
                }
                visited[nr][nc] = visited[current[0]][current[1]];
                que.add(new int[] { nr, nc });
            }

            // 순간이동할 수 있는 위치라면 순간이동 수행
            if (teleport.containsKey(currentNum)) {
                ArrayList<int[]> list = teleport.get(currentNum);
                for (int[] pos :
                        list) {
                    // 순간이동해도 기존보다 적은 에너지가 필요하면 수행
                    if (visited[pos[0]][pos[1]] <= visited[current[0]][current[1]]) {
                        continue;
                    }
                    // 순간이동을 했으므로 에너지 + 1
                    visited[pos[0]][pos[1]] = visited[current[0]][current[1]] + 1;
                    que.add(pos);
                }
            }
        }

        ret = visited[to[0]][to[1]];
        return (ret == Integer.MAX_VALUE) ? -1 : ret;
    }

    private static void init() throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        board = new int[R][C];

        for (int r = 0; r < R; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < C; c++) {
                board[r][c] = Integer.parseInt(st.nextToken());
                if (board[r][c] >= 10) {
                    final ArrayList<int[]> value = teleport.getOrDefault(board[r][c], new ArrayList<>());
                    value.add(new int[] { r, c });
                    teleport.put(board[r][c], value);
                }
                if (board[r][c] == -2) {
                    gift = new int[] { r, c };
                }
            }
        }
    }

}
