package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ11003_최솟값찾기 {
    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        final StringBuilder sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());
        int min = Integer.MAX_VALUE;
        Node[] arr = new Node[N];

        for (int i = 0; i < N; i++) {
            arr[i] = new Node(i, Integer.parseInt(st.nextToken()));
        }

        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.val - o2.val);

        for (int i = 0; i < N; i++) {
            pq.add(arr[i]);
            while (pq.peek().idx <= i - L) pq.poll();
            sb.append(pq.peek().val).append(" ");
        }
        System.out.println(sb.toString());
    }

    static class Node {
        int val;
        int idx;

        public Node(int idx, int val) {
            this.idx = idx;
            this.val = val;
        }
    }
}
