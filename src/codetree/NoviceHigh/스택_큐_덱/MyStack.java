package codetree.NoviceHigh.스택_큐_덱;

import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class MyStack {
    public static void main(String[] args) throws IOException {
        Stack<Integer> stack = new Stack<>();
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
                stack.push(number);
            } else if (query.equals("size")) {
                bw.write(String.valueOf(stack.size()));
                bw.write('\n');
            } else if (query.equals("empty")) {
                bw.write(String.valueOf((stack.isEmpty()) ? 1 : 0));
                bw.write('\n');
            } else if (query.equals("pop")) {
                bw.write(String.valueOf(stack.pop()));
                bw.write('\n');
            } else if (query.equals("top")) {
                bw.write(String.valueOf(stack.peek()));
                bw.write('\n');
            }
        }

        bw.flush();
    }

}
