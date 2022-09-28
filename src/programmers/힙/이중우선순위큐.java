package programmers.힙;

import java.util.Comparator;
import java.util.PriorityQueue;

public class 이중우선순위큐 {
    PriorityQueue<Integer> minHeap;
    PriorityQueue<Integer> maxHeap;

    public int[] solution(String[] operations) {
        int[] answer = new int[2];

        minHeap = new PriorityQueue<>();
        maxHeap = new PriorityQueue<>(Comparator.reverseOrder());

        for (var operation :
                operations) {
            String[] split = operation.split(" ");
            if (split[0].equals("I")) {
                int num = Integer.parseInt(split[1]);
                minHeap.offer(num);
                maxHeap.offer(num);
            } else if (split[0].equals("D")) {
                if (minHeap.size() == 0)
                    continue;
                int num = Integer.parseInt(split[1]);
                if (num == -1) {
                    Integer min = minHeap.poll();
                    maxHeap.remove(min);
                } else {
                    Integer max = maxHeap.poll();
                    minHeap.remove(max);
                }
            }
        }
        if (minHeap.isEmpty()) {
            return new int[]{0, 0};
        } else {
            return new int[]{maxHeap.poll(), minHeap.poll()};
        }
    }
}
