package codetree.novicemid.DFS;

import java.io.*;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Stack;
import java.util.StringTokenizer;

public class 인접리스트활용 {
    static int N, M;

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        LinkedList<Integer>[] graph = new LinkedList[N + 1];

        for (int i = 0; i < N + 1; i++) {
            graph[i] = new LinkedList<>();
        }


        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            graph[from].add(to);
            graph[to].add(from);
        }

        Stack<Integer> stack = new Stack<>();
        stack.push(1);

        HashSet<Integer> visited = new HashSet<>();
        visited.add(1);

        int result = 0;
        while (!stack.isEmpty()) {
            int current = stack.pop();

            for (int next :
                    graph[current]) {
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
