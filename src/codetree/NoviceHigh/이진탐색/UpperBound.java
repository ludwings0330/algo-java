package codetree.NoviceHigh.이진탐색;

public class UpperBound {
    public static void main(String[] args) {

    }

    // upperBound는 초과되는 값 찾기
    private static int upperBound(int[] arr, int target) {
        int ret = -1;
        int left = 0, right = arr.length - 1;

        while (left < right) {
            int mid = (left + right) / 2;

            // 정답 가능성 없음
            if (arr[mid] <= target)
                left = mid + 1;
            else
                right = mid;
        }

        return ret = right;
    }
}
