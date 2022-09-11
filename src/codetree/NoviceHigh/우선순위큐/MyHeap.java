package codetree.NoviceHigh.우선순위큐;

public class MyHeap {
    int last = 0;

    public static void main(String[] args) {

    }

    // 이진 트리의 특성을 가지기 때문에 배열로 구현
    public void heapify(int[] arr, int n, int idx) {
        int largest = idx;
        int left = idx * 2;
        int right = idx * 2 + 1;
        if (left <= n && arr[left] > arr[largest]) {
            largest = left;
        }
        if (right <= n && arr[right] > arr[largest]) {
            largest = right;
        }

        if (largest != idx) {
            int tmp = arr[idx];
            arr[idx] = arr[largest];
            arr[largest] = tmp;
            heapify(arr, n, largest);
        }
    }

    public void init(int[] arr) {
        int n = arr.length;
        for (int i = n / 2; i >= 1; i--) {
            heapify(arr, n, i);
        }
    }

    public void insert(int[] arr, int n, int x) {
        arr[last + 1] = x;
        // 가장 마지막 위치에 데이터를 삽입
        int idx = last + 1;
        // idx가 루트 노드가 아니고, 부모보다 더 클 동안 반복
        while (idx > 1 && arr[idx] > arr[idx / 2]) {
            // 부모 노드보다 현재 노드가 더 크면 교환
            int tmp = arr[idx];
            arr[idx] = arr[idx / 2];
            arr[idx / 2] = tmp;

            // 현재 노드 위치를 부모 노드로 이동
            idx = idx / 2;
        }
    }

    public void remove(int[] arr, int n) {
        arr[1] = arr[n];
        arr[n] = 0;
        heapify(arr, n - 1, 1);
    }
}
