package cheat.DisjointSet;

public class UnionFind {
    static int[] parent;
    static int[] rank;

    public static void init(int n) {
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    public static int find(int x) {
        if (x == parent[x]) return x;
        return parent[x] = find(parent[x]);
    }

    public static boolean union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x == y) return false;
        if (rank[x] > rank[y]) {
            int tmp = x;
            x = y;
            y = tmp;
        }

        parent[x] = y;

        if (rank[x] == rank[y]) rank[y]++;
        return true;
    }

}
