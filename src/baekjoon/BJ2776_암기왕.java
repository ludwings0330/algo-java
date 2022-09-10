package baekjoon;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ2776_암기왕 {
    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
//        StringBuilder sb = new StringBuilder();

        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            int[] arr = new int[N];

            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(arr);

            int M = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());

            for (int i = 0; i < M; i++) {
                int target = Integer.parseInt(st.nextToken());
                int value = binarySearch(arr, target);
                bw.write(String.valueOf(value));
                bw.write('\n');
            }
        }
        bw.flush();
    }

    private static int binarySearch(int[] arr, int target) {
        int ret = 0;
        int left = 0, right = arr.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (arr[mid] == target)
                return ret = 1;

            if (arr[mid] > target)
                right = mid - 1;
            else
                left = mid + 1;
        }

        return ret;
    }
}
