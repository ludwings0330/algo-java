package codetree.NoviceHigh.정렬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class RadixSort {
    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        final StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        radixSort(arr);

        for (int i = 0; i < N; i++) {
            sb.append(arr[i]).append(" ");
        }

        System.out.println(sb.toString());
    }

    private static void radixSort(int[] arr) {
        for (int k = 0; k <= 5; k++) {
            ArrayList<Integer>[] store = new ArrayList[10];

            for (int i = 0; i < 10; i++) {
                store[i] = new ArrayList<>();
            }

            for (int i = 0; i < arr.length; i++) {
                int idx = -1;
                idx = arr[i] / (int) Math.pow(10, k) % 10;
                store[idx].add(arr[i]);
            }

            int idx = 0;

            for (int r = 0; r < 10; r++) {
                for (int num :
                        store[r]) {
                    arr[idx++] = num;
                }
            }

        }
    }
}
