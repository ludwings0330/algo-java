package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BJ4195_친구네트워크 {
    static int F;
    static int id;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static HashMap<String, Integer> hashID;
    static ArrayList<Integer> parent;
    static ArrayList<Integer> rank;

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            solve();
        }
        System.out.println(sb.toString());
    }

    private static void solve() throws IOException {
        init();
        for (int i = 0; i < F; i++) {
            final StringTokenizer st = new StringTokenizer(br.readLine());
            String f1 = st.nextToken();
            String f2 = st.nextToken();

            if (!hashID.containsKey(f1)) {
                rank.add(1);
                parent.add(id);
                hashID.put(f1, id++);
            }

            if (!hashID.containsKey(f2)) {
                rank.add(1);
                parent.add(id);
                hashID.put(f2, id++);
            }
            int id1 = hashID.get(f1);
            int id2 = hashID.get(f2);

            union(id1, id2);
            sb.append(rank.get(find(id1))).append('\n');
        }
    }

    private static void union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x == y)
            return;
        if (rank.get(x) > rank.get(y)) {
            rank.set(x, rank.get(x) + rank.get(y));
            parent.set(y, x);
        } else {
            rank.set(y, rank.get(x) + rank.get(y));
            parent.set(x, y);
        }
    }

    private static int find(int x) {
        if (parent.get(x) == x) return x;

        parent.set(x, find(parent.get(x)));

        return parent.get(x);
    }

    private static void init() throws IOException {
        F = Integer.parseInt(br.readLine());
        id = 0;

        hashID = new HashMap<>();
        parent = new ArrayList<>();
        rank = new ArrayList<>();
    }
}
