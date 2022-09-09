package codetree.NoviceHigh.정렬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class HeapSort {
    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N + 1];
        final StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        heapSort(arr, N);

        for (int i = 1; i <= N; i++) {
            sb.append(arr[i]).append(" ");
        }

        System.out.println(sb.toString());
    }

    private static void heapSort(int[] arr, int n) {
        for (int i = n / 2; i >= 1; i--) {
            heapify(arr, n, i);
        }

        for (int i = n; i > 1; i--) {
            swap(arr, i, 1);
            heapify(arr, i - 1, 1);
        }
    }

    private static void heapify(int[] arr, int n, int i) {
        int maxIndex = i;
        int left = i * 2;
        int right = i * 2 + 1;

        if (left <= n && arr[left] > arr[maxIndex])
            maxIndex = left;
        if (right <= n && arr[right] > arr[maxIndex])
            maxIndex = right;

        if (maxIndex != i) {
            swap(arr, i, maxIndex);
            heapify(arr, n, maxIndex);
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

}
