package codetree.NoviceHigh.배열과연결리스트.동적배열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 정수명령처리5 {
    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            final StringTokenizer st = new StringTokenizer(br.readLine());
            String query = st.nextToken();
            int parameter = -1;
            if (st.hasMoreTokens()) {
                parameter = Integer.parseInt(st.nextToken());
            }

            if (query.equals("push_back")) {
                list.add(parameter);
            } else if (query.equals("get")) {
                System.out.println(list.get(parameter - 1));
            } else if (query.equals("size")) {
                System.out.println(list.size());
            } else if (query.equals("pop_back")) {
                list.remove(list.size() - 1);
            }
        }
    }
}
