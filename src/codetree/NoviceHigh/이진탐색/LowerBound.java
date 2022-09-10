package codetree.NoviceHigh.이진탐색;

public class LowerBound {
    public static void main(String[] args) {

    }

    // lowerBound 는 target 값 이상 찾기
    // upperBound 는 target 값 초과 찾기
    private int lowerBound(int[] arr, int target) {
        int ret = -1;
        int left = 0, right = arr.length - 1;

        while (left < right) {
            int mid = (left + right) / 2;

            // 정답 가능성 없음
            if (arr[mid] < target)
                left = mid + 1;
            else
                right = mid;
        }

        return ret = right;
    }
}
