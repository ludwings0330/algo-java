package programmers.해시;

import java.util.*;

public class 베스트앨범 {
    static HashMap<String, Integer> genreRank;
    static HashMap<String, PriorityQueue<Node>> titleRank;

    public static void main(String[] args) {
        System.out.println(solution(new String[]{"classic", "pop", "classic", "classic", "pop"}, new int[]{500, 600, 150, 800, 2500}));
    }

    public static int[] solution(String[] genres, int[] plays) {
        List<Integer> answer = new ArrayList<>();
        genreRank = new HashMap<>();
        titleRank = new HashMap<>();

        for (int i = 0; i < genres.length; i++) {
            genreRank.put(genres[i], genreRank.getOrDefault(genres[i], 0) + plays[i]);

            Node node = new Node(plays[i], i);
            PriorityQueue<Node> pq = titleRank.getOrDefault(genres[i], new PriorityQueue<>());
            pq.offer(node);
            titleRank.put(genres[i], pq);
        }

        List<Map.Entry<String, Integer>> entries = new LinkedList<>(genreRank.entrySet());
        entries.sort(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue() - o1.getValue();
            }
        });

        for (var entry :
                entries) {
            String key = entry.getKey();
            PriorityQueue<Node> pq = titleRank.get(key);
            int cnt = 0;
            while (!pq.isEmpty() && cnt++ < 2) {
                final Node poll = pq.poll();
                answer.add(poll.idx);
            }
        }

        return answer.stream().mapToInt(i -> i).toArray();
    }

    static class Node implements Comparable<Node> {
        int plays;
        int idx;

        public Node(int plays, int idx) {
            this.plays = plays;
            this.idx = idx;
        }

        @Override
        public int compareTo(Node o) {
            if (this.plays != o.plays)
                return -this.plays + o.plays;
            return this.idx - o.idx;
        }
    }
}
