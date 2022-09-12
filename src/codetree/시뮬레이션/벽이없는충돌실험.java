package codetree.시뮬레이션;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class 벽이없는충돌실험 {
    static int T, N, lastTime;
    static HashMap<String, Integer[]> direction = new HashMap<>();
    static BufferedReader br;
    static BufferedWriter bw;

    static List<Bead> beads = new LinkedList<>();
    static int maxDist;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        direction.put("R", new Integer[]{0, 1});
        direction.put("L", new Integer[]{0, -1});
        direction.put("U", new Integer[]{1, 0});
        direction.put("D", new Integer[]{-1, 0});

        T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            solve();
        }

        bw.flush();
        br.close();
        bw.close();
    }

    private static void solve() throws IOException {
        N = Integer.parseInt(br.readLine());
        maxDist = 0;
        lastTime = 0;
        beads = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) * 2;
            int y = Integer.parseInt(st.nextToken()) * 2;
            int w = Integer.parseInt(st.nextToken());
            String d = st.nextToken();
            beads.add(new Bead(y, x, w, d, i));
        }

        int t = 1;
        while (!beads.isEmpty()) {
            // 1초 움직임
            moveAll(t++);
            // 2초 움직임
            moveAll(t++);
        }

        bw.write(String.valueOf((lastTime > 0) ? lastTime : -1));
        bw.write('\n');
    }

    private static void moveAll(int time) {
        // case 1. 이동하고나서 최종 위치에서 만났을때.
        // case 2. 이동중에 중간에서 만났을 때.
        HashMap<String, Bead> store = new HashMap<>();
        for (Bead bead :
                beads) {
            int nr = bead.r + direction.get(bead.d)[0];
            int nc = bead.c + direction.get(bead.d)[1];
            if (nr < -2000 || nr > 2000 || nc < -2000 || nc > 2000)
                continue;
            String key = nr + "," + nc;

            bead.r = nr;
            bead.c = nc;
            // (nr, nc, weight)
            if (store.containsKey(key)) {
                // 충돌이 발생하면 충돌시간을 업데이트
                lastTime = time;
                Bead current = store.get(key);
                if (current.w < bead.w || (current.w == bead.w && current.no < bead.no)) {
                    store.replace(key, bead);
                }
            } else {
                store.put(key, bead);
            }
        }

        beads = new LinkedList<>(store.values());
    }

    static class Bead {
        int r, c, w, no;
        String d;

        public Bead(int r, int c, int w, String d, int no) {
            this.r = r;
            this.c = c;
            this.w = w;
            this.d = d;
            this.no = no;
        }
    }
}
