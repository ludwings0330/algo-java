package codetree.NoviceHigh.스택_큐_덱;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class MyDeque {
    public static void main(String[] args) throws IOException {
        Deque<Integer> dq = new ArrayDeque<>();
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

            if (query.equals("push_front")) {
                dq.offerFirst(number);
            } else if (query.equals("push_back")) {
                dq.offerLast(number);
            } else if (query.equals("size")) {
                bw.write(String.valueOf(dq.size()));
                bw.write('\n');
            } else if (query.equals("empty")) {
                bw.write(String.valueOf((dq.isEmpty()) ? 1 : 0));
                bw.write('\n');
            } else if (query.equals("pop_front")) {
                bw.write(String.valueOf(dq.pollFirst()));
                bw.write('\n');
            } else if (query.equals("pop_back")) {
                bw.write(String.valueOf(dq.pollLast()));
                bw.write('\n');
            } else if (query.equals("front")) {
                bw.write(String.valueOf(dq.peekFirst()));
                bw.write('\n');
            } else if (query.equals("back")) {
                bw.write(String.valueOf(dq.peekLast()));
                bw.write('\n');
            }
        }

        bw.flush();
    }
}
