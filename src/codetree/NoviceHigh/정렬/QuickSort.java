package codetree.NoviceHigh.정렬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class QuickSort {
    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        final StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        quickSortDivide(arr, 0, arr.length - 1);

        for (int i = 0; i < N; i++) {
            sb.append(arr[i]).append(" ");
        }

        System.out.println(sb.toString());
    }

    private static void quickSortDivide(int[] arr, int left, int right) {
        if (left < right) {
            int pos = quickSort(arr, left, right);

            quickSortDivide(arr, left, pos - 1);
            quickSortDivide(arr, pos + 1, right);
        }


    }

    private static int quickSort(int[] arr, int left, int right) {
        // select pivot
        int pivot = arr[right];
        int i = left - 1;

        for (int j = left; j <= right - 1; j++) {
            if (arr[j] < pivot) {
                i += 1;
                int tmp = arr[j];
                arr[j] = arr[i];
                arr[i] = tmp;
            }
        }

        int tmp = arr[i + 1];
        arr[i + 1] = arr[right];
        arr[right] = tmp;

        return i + 1;
    }
}
