package cheat.구간합;

public class SegmentTree {
    private long[] tree;
    private long[] input;

    // n 은 문제에서 사용되는 노드 갯수
    public SegmentTree(int n, long[] arr) {
        // tree height 계산
        double height = Math.ceil(Math.log(n) / Math.log(2)) + 1;

        // tree 의 최대 node 갯수 계산
        long count = Math.round(Math.pow(2, height));

        // tree 공간 확보
        tree = new long[Math.toIntExact(count)];
        this.input = arr;
    }

    long init(int start, int end, int idx) {
        // 리프 노드인 경우
        if (start == end) {
            return tree[idx] = input[start];
        }
        // 리프 노드가 아닌 경우 부분합을 구함
        int mid = (start + end) / 2;
        return tree[idx] = init(start, mid, idx * 2)
                + init(mid + 1, end, idx * 2 + 1);
    }

    long query(int start, int end, int queryStart, int queryEnd, int idx) {
        // 범위 안에 있는 경우 모든 값이 필요함
        if (queryStart <= start && end <= queryEnd) {
            return tree[idx];
        }
        // 범위 밖에 있는 경우 필요 없음
        if (queryEnd < start || end < queryStart) {
            return 0;
        }
        // 걸쳐 있는 경우 왼쪽으로 걸치거나, 오른쪽으로 걸치거나.
        int mid = (start + end) / 2;
        return query(start, mid, queryStart, queryEnd, idx * 2) +
                query(mid + 1, end, queryStart, queryEnd, idx * 2 + 1);
    }

    long update(int start, int end, int targetIdx, int idx, int updateValue) {
        if (targetIdx < start || end < targetIdx)
            return tree[idx];
        if (start == end) {
            return tree[idx] = updateValue;
        }
        int mid = (start + end) / 2;
        return tree[idx] = update(start, mid, targetIdx, idx * 2, updateValue) +
                update(mid + 1, end, targetIdx, idx * 2 + 1, updateValue);
    }
}
