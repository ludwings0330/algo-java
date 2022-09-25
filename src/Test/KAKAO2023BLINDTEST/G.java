package Test.KAKAO2023BLINDTEST;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class G {
    PriorityQueue<Node>[] graph;

    public int[] solution(int[][] edges, int[] target) {
        ArrayList<Integer> answer = new ArrayList<>();


        int[] sum = new int[edges.length + 2];
        for (int i = 0; i < sum.length; i++) {
            sum[i] = 0;
        }

        graph = new PriorityQueue[edges.length + 2];
        for (int i = 0; i <= edges.length + 1; i++) {
            graph[i] = new PriorityQueue<>();
        }
        Node[] nodes = new Node[edges.length + 2];
        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = new Node(i);
        }
        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            graph[from].offer(nodes[to]);
        }
        while (true) {
            // 1부터 이동경로를 따라 리프노드까지 내려감
            int current = 1;
            while (graph[current].size() != 0) {
                final Node poll = graph[current].poll();
                int next = poll.value;
                poll.visitCount += 1;
                graph[current].offer(poll);
                // 다음으로 이동하는 방법
                current = next;
            }
            // current 에는 들어갈 node 가 담겨있음
            if (sum[current] == target[current])
                break;
            else sum[current] += 1;
        }
        return answer.stream().mapToInt(i -> i).toArray();
    }

    static class Node implements Comparable<Node> {
        int visitCount;
        int value;

        public Node(int value) {
            this.visitCount = 0;
            this.value = value;
        }

        @Override
        public int compareTo(Node o) {
            if (this.visitCount != o.visitCount)
                return this.visitCount - o.visitCount;
            return this.value - o.value;
        }
    }
}
