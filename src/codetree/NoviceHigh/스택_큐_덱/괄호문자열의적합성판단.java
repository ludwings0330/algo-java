package codetree.NoviceHigh.스택_큐_덱;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class 괄호문자열의적합성판단 {
    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final char[] chars = br.readLine().toCharArray();

        System.out.println((isOk(chars)) ? "Yes" : "No");
    }

    private static boolean isOk(char[] chars) {
        final Stack<Character> stack = new Stack<>();
        boolean ret = true;

        for (char ch : chars) {
            if (ch == '(') {
                stack.push(ch);
            } else {
                if (!stack.isEmpty() && stack.peek() == '(')
                    stack.pop();
                else
                    return false;
            }
        }

        return stack.isEmpty();
    }
}
