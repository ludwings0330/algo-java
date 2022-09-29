package programmers.DFSBFS;

import java.util.Deque;
import java.util.LinkedList;


public class 단어변환 {

    public int solution(String begin, String target, String[] words) {
        int answer = 0;

        boolean[] visited = new boolean[words.length];
        Deque<Node> dq = new LinkedList<>();

        dq.offerLast(new Node(begin, 0));

        while (!dq.isEmpty()) {
            Node current = dq.pollFirst();
            if (current.value.equals(target)) {
                answer = current.depth;
                break;
            }
            for (int i = 0; i < words.length; i++) {
                if (visited[i]) continue;
                String next = words[i];
                if (difference(current.value, next) != 1) continue;
                visited[i] = true;
                Node nextNode = new Node(next, 0);
                nextNode.depth = current.depth + 1;
                dq.offerLast(nextNode);
            }
        }

        return answer;
    }

    private int difference(String current, String next) {
        int diff = 0;
        for (int i = 0; i < current.length(); i++) {
            if (current.charAt(i) != next.charAt(i)) diff++;
        }

        return diff;
    }

    class Node {
        String value;
        int depth;

        public Node(String value, int depth) {
            this.value = value;
            this.depth = depth;
        }
    }
}
