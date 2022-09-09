package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ19235_모노미노도미노 {
    public static void main(String[] args) throws IOException {
        Monomino monomino = new Monomino();
        int N;
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            final StringTokenizer st = new StringTokenizer(br.readLine());

            int t = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            monomino.addBlock(t, x, y);
        }
    }

    static class Monomino {
        int board[][];
        int score;

        public Monomino() {
            this.board = new int[10][10];
            this.score = 0;
        }

        public void addBlock(int type, int c, int r) {
//            방금 들어온 블럭 생성
            // 1. 오른쪽으로 이동
            // 1.1 만들어진 줄 제거
            // 1.2 만들어진 줄을 통해 수정 (제거되는 줄이 없을때까지)
            // 1.3 공백공간에 아무것도없을때까지 한줄씩 제거

            // 2. 아래쪽으로 이동
            // 오른쪽으로 이동할때와 동일
        }
    }
}
