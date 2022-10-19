package cheat.문자열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
/*
9
I W A N T M E A T
E A T I W A N T M
 */

public class kmp {
    static int N;
    static String search;
    static String origin;
    static int[] table;

    public static void main(String[] args) throws IOException {
        init();
        table = makeKmpTable(search);
        List<Integer> list = kmpSearch(origin, search);
        int n = list.size();
        int g = gcd(n, N);
        System.out.println(n / g + "/" + N / g);
    }

    private static int gcd(int A, int B) {
        if (A % B == 0)
            return B;
        return gcd(B, A % B);
    }

    private static List<Integer> kmpSearch(String origin, String search) {
        int n = origin.length();
        int m = search.length();

        int ret = 0;
        List<Integer> answer = new ArrayList<>();
        int begin = 0;
        int matched = 0;
        while (begin <= n - m) {
            if (matched < m && search.charAt(matched) == origin.charAt(begin + matched)) {
                matched++;
                // 완전히 일치한다면
                if (matched == m) {
                    ret++;
                    // 정답 위치 저장
                    answer.add(begin);
                }
            }
            // 불일치가 발생한다면
            else {
                if (matched == 0)
                    begin++;
                else {
                    // 점프
                    begin += matched - table[matched - 1];
                    matched = table[matched - 1];
                }
            }
        }

        return answer;
    }

    private static int[] makeKmpTable(String search) {
        int n = search.length();
        int[] table = new int[n];
        int begin = 1;
        int matched = 0;

        while (begin + matched < n) {
            // 탐색 문자열과 탐색 문자열 자신을 매칭 시켜봄
            if (search.charAt(begin + matched) == search.charAt(matched)) {
                matched++;
                // 매칭을 진행하면서 접두 접미사 배열을 바로 갱싱
                // 이 아래 부분과 begin 이 1 부터 시작한다는 것을 빼면 kmp 구현과 동일
                table[begin + matched - 1] = matched;
            }
            // 불일치가 발생했다면 table 이용하여 점프
            else {
                if (matched == 0)
                    begin++;
                else {
                    // KMP 알고리즘과 동일, 불일치 발생시
                    // 매칭을 진행하면서 구한 접두 접미사 길이 만큼 탐색을 건너뛸 수 있다.
                    begin += matched - table[matched - 1];
                    matched = table[matched - 1];
                }
            }
        }
        return table;
    }

    private static void init() throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();

        for (int i = 0; i < N; i++) {
            sb.append(st.nextToken());
            sb2.append(st2.nextToken());
        }

        search = sb.toString();
        origin = sb2.toString() + sb2.toString();
    }
}
