package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BJ11053_가장긴증가하는부분수열 {
    static int N;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        init();
        System.out.println(lis());
    }

    private static int lis() {
        List<Integer> result = new ArrayList<>();
        result.add(arr[0]);
        for (int i = 1; i < N; i++) {
            int idx = upperBound(result, arr[i]);
            if (idx == result.size())
                result.add(arr[i]);
            else
                result.set(idx, arr[i]);
        }

        return result.size();
    }

    private static int upperBound(List<Integer> result, int target) {
        int left = 0, right = result.size();
        int mid = 0;
        while (left < right) {
            mid = (left + right) / 2;

            if (result.get(mid) < target)
                left = mid + 1;
            else
                right = mid;
        }
        return right;
    }

    private static void init() throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        final StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
    }
}
