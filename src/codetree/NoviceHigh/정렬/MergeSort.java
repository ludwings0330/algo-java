package codetree.NoviceHigh.정렬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MergeSort {
    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        final StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        mergeSort(arr, 0, arr.length - 1);

        for (int i = 0; i < N; i++) {
            sb.append(arr[i]).append(" ");
        }

        System.out.println(sb.toString());
    }

    private static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        int l = left, r = mid + 1, idx = 0;
        int[] tmp = new int[right - left + 1];

        while (l <= mid && r <= right) {
            if (arr[l] <= arr[r])
                tmp[idx++] = arr[l++];
            else
                tmp[idx++] = arr[r++];

        }

        while (l <= mid)
            tmp[idx++] = arr[l++];

        while (r <= right)
            tmp[idx++] = arr[r++];

        for (int i = left; i <= right; i++)
            arr[i] = tmp[i - left];
    }
}
