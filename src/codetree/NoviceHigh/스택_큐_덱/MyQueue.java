package codetree.NoviceHigh.스택_큐_덱;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class MyQueue {
    public static void main(String[] args) throws IOException {
        Queue<Integer> que = new LinkedList<>();
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());

        while (N-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String query = st.nextToken();
            int number = -1;

            if (st.hasMoreTokens()) {
                number = Integer.parseInt(st.nextToken());
            }

            if (query.equals("push")) {
                que.offer(number);
            } else if (query.equals("size")) {
                bw.write(String.valueOf(que.size()));
                bw.write('\n');
            } else if (query.equals("empty")) {
                bw.write(String.valueOf((que.isEmpty()) ? 1 : 0));
                bw.write('\n');
            } else if (query.equals("pop")) {
                bw.write(String.valueOf(que.poll()));
                bw.write('\n');
            } else if (query.equals("front")) {
                bw.write(String.valueOf(que.peek()));
                bw.write('\n');
            }
        }

        bw.flush();
    }
}
