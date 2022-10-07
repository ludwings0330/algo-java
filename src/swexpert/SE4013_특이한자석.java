package swexpert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SE4013_특이한자석 {
    static final int N = 0, S = 1, CW = 1, CCW = -1;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static int K;

    static Info[] rotateInfo;
    static int[][] magnets;
    static int[] arrow;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        int TC = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= TC; tc++) {
            sb.append("#").append(tc).append(" ").append(solve()).append("\n");
        }

        System.out.println(sb.toString());
    }

    private static int solve() throws IOException {
        init();

        for (int i = 0; i < K; i++) {
            visited = new boolean[4];
            rotate(rotateInfo[i]);
        }

        return calculate();
    }

    private static int calculate() {
        int score = 0;

        for (int i = 0; i < 4; i++) {
            if (magnets[i][arrow[i]] == S) {
                score += Math.pow(2, i);
            }
        }

        return score;
    }

    private static void rotate(Info current) {
        visited[current.no] = true;

        int leftMagnet = current.no - 1;
        int rightMagnet = current.no + 1;

        int currentLeftType = magnets[current.no][(arrow[current.no] + 8 - 2) % 8];
        int currentRightType = magnets[current.no][(arrow[current.no] + 8 + 2) % 8];

        if (leftMagnet >= 0 && !visited[leftMagnet]) {
            visited[leftMagnet] = true;
            int leftType = magnets[leftMagnet][(arrow[leftMagnet] + 8 + 2) % 8];
            if (leftType != currentLeftType)
                rotate(new Info(leftMagnet, current.direction * -1));
        }

        if (rightMagnet <= 3 && !visited[rightMagnet]) {
            visited[rightMagnet] = true;
            int rightType = magnets[rightMagnet][(arrow[rightMagnet] + 8 - 2) % 8];
            if (rightType != currentRightType)
                rotate(new Info(rightMagnet, current.direction * -1));
        }

        arrow[current.no] = (arrow[current.no] + (current.direction * -1) + 8) % 8;
    }

    private static void init() throws IOException {
        K = Integer.parseInt(br.readLine());
        magnets = new int[4][8];
        for (int i = 0; i < 4; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 8; j++) {
                magnets[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        rotateInfo = new Info[K];
        for (int i = 0; i < K; i++) {
            final StringTokenizer st = new StringTokenizer(br.readLine());
            int no = Integer.parseInt(st.nextToken()) - 1;
            int direction = Integer.parseInt(st.nextToken());

            rotateInfo[i] = new Info(no, direction);
        }

        arrow = new int[4];
    }

    private static class Info {
        int no;
        int direction;

        public Info(int no, int direction) {
            this.no = no;
            this.direction = direction;
        }
    }
}
