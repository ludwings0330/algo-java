package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BJ2812_크게만들기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        String num = br.readLine();
        Stack<Character> stack = new Stack<>();

        stack.push(num.charAt(0));
        for (int i = 1; i < N; i++) {
            char current = num.charAt(i);
            if (K > 0) {
                if (stack.peek() < current) {
                    while (K > 0 && !stack.isEmpty() && stack.peek() < current) {
                        stack.pop();
                        K--;
                    }
                }

                stack.push(current);
            } else {
                stack.push(num.charAt(i));
            }
        }
        while (K-- > 0)
            stack.pop();
        StringBuilder sb = new StringBuilder();

        for (var n :
                stack) {
            sb.append(n);
        }

        System.out.println(sb.toString());
    }
}
