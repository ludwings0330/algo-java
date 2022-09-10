package codetree.NoviceHigh.이진탐색;

public class BinarySearch {
    public static void main(String[] args) {

    }

    private static int binarySearch(int[] arr, int target) {
        int ret = 0;
        int left = 0, right = arr.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;

            // 찾으면 해당 idx return
            if (arr[mid] == target)
                return ret = mid;

            if (arr[mid] > target)
                right = mid - 1;
            else
                left = mid + 1;
        }
        // 찾지못하면 -1
        return -1;
    }
}
