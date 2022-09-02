package codetree.novicemid.정렬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 문자열내문내림차순정렬 {
    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final char[] chars = br.readLine().toCharArray();
        Arrays.sort(chars);
        System.out.println(new String(chars));
    }
}
