package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ1062_가르침 {
    static int N, K;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static String[] words;

    // anta - tica
    static boolean[] choice = new boolean[26];
    static int answer = 0;

    public static void main(String[] args) throws Exception {
        init();
        solve();
        System.out.println(answer);
    }

    public static void solve() {
        // 중복 x 순서 x
        combination(K - 5, 0);
    }

    public static void combination(int remains, int idx) {
        if (idx == 26 && remains == 0) {
            check();
            return;
        }
        if (idx >= 26 || remains < 0) {
            return;
        }
        if (choice[idx]) {
            combination(remains, idx + 1);
            return;
        }
        if (remains == 0) {
            // choice 선택 완료 -> 체크 시작
            check();
            return;
        }
        choice[idx] = true;
        combination(remains - 1, idx + 1);
        choice[idx] = false;
        combination(remains, idx + 1);
    }

    public static void check() {
        int count = 0;
        for (String word :
                words) {
            if (word.length() < 8) {
                continue;
            }
            String ext = word.substring(4, word.length() - 4);
            boolean ok = true;
            for (int i = 0; i < ext.length(); i++) {
                if (!choice[ext.charAt(i) - 'a']) {
                    ok = false;
                    break;
                }
            }

            if (ok) count++;
        }
        answer = Math.max(answer, count);
    }

    public static void init() throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        words = new String[N];

        for (int i = 0; i < N; i++) {
            words[i] = br.readLine();
        }

        for (char ch :
                "antatica".toCharArray()) {
            choice[ch - 'a'] = true;
        }
    }
}
