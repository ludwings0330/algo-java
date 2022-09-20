package codetree.novicemid.DFS;

import java.io.*;
import java.util.*;

public class 그래프탐색 {
    static int N, M;

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        HashMap<Integer, LinkedList<Integer>> graph = new HashMap<Integer, LinkedList<Integer>>();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            if (!graph.containsKey(from)) graph.put(from, new LinkedList<>());
            if (!graph.containsKey(to)) graph.put(to, new LinkedList<>());

            graph.get(from).add(to);
            graph.get(to).add(from);
        }

        Stack<Integer> stack = new Stack<>();
        stack.push(1);

        HashSet<Integer> visited = new HashSet<>();
        visited.add(1);

        int result = 0;
        while (!stack.isEmpty()) {
            int current = stack.pop();

            for (int next :
                    graph.getOrDefault(current, new LinkedList<>())) {
                if (visited.contains(next)) continue;
                stack.push(next);
                visited.add(next);
                result++;
            }
        }

        bw.write(String.valueOf(result));
        bw.flush();

        bw.close();
        br.close();
    }
}
