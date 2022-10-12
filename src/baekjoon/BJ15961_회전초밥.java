package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ15961_회전초밥 {
    static int N, d, k, c;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        init();
        System.out.println(solve());
    }

    private static int solve() {
        int ret = 0;
        int s = 0, e = k;
        int cnt = 0;

        int[] visited = new int[d + 1];

        for (int i = s; i < e; i++) {
            visited[arr[i]]++;
            if (visited[arr[i]] == 1)
                cnt++;
        }

        while (s < N) {
            visited[arr[s]]--;
            if (visited[arr[s]] == 0) {
                cnt--;
            }
            visited[arr[e % N]]++;
            if (visited[arr[e % N]] == 1) {
                cnt++;
            }
            ret = Math.max(ret, cnt + ((visited[c] == 0) ? 1 : 0));
            s++;
            e++;
        }

        return ret;
    }

    private static void init() throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
    }
}
