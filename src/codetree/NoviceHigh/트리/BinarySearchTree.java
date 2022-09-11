package codetree.NoviceHigh.트리;

public class BinarySearchTree {
    static Node root = null;

    public static Node search(int target) {
        // root 에서 시작
        Node node = root;
        // 값이 없으면 null 을 반환하게 된다.
        while (node != null && node.value != target) {

            // 찾고자 하는 값이 더 크면 오른쪽에 있음
            if (node.value < target) {
                node = node.right;
            } else {
                // 찾고하자는 값이 더 작으면 왼쪽에 있음
                node = node.left;
            }
        }

        // 최종위치 반환.
        return node;
    }

    public static void insert(int target) {
        // root 부터 들어갈 위치를 찾는다.
        Node node = root;
        // 새로운 node 를 추가하려면 parent 를 알아야한다.
        Node parent = root;

        // target 이 들어갈 위치 null 이 될때까지 탐색
        while (node != null) {
            parent = node;
            if (node.value > target) {
                node = node.left;
            } else {
                node = node.right;
            }
        }

        // 비어있는 tree일 경우 root 에 새로운 노드를 만든다.
        if (parent == null) {
            root = new Node(target);
        } else if (parent.value > target) {
            // parent 에 적혀있는 값이 target 보다 크면 왼쪽으로 node 추가
            parent.left = new Node(target);
        } else {
            // parent 에 적혀있는 값이 target 보다 작으면 오른쪽으로 node 추가
            parent.right = new Node(target);
        }
    }

    public static void delete(int target) {
        Node node = search(target);

        if (node == null) return;

        if (node.left == null) {
            // case 1. 왼쪽 자식이 비어 있으면,
            // 오른쪽 자식을 node 위치로 올린다.
            node = node.right;
        } else if (node.right == null) {
            // case 2. 오른쪽 자식이 비어있으면,
            // 왼쪽 자식을 node 위치로 올린다.
            node = node.left;
        } else {
            // 왼쪽 오른쪽 둘다 있으면,
            // successor 를 찾는다.
            // successor 는 현재 노드의 오른쪽에서 가장 왼쪽 노드로 이동
            Node successor = node.right;
            while (successor.left != null) {
                successor = successor.left;
            }

            // node 에 successor 값을 넣는다.
            // successor 에 오른쪽 자식을 옮긴다.
            node.value = successor.value;
            successor = successor.right;
        }
    }

    static class Node {
        int value;

        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
        }
    }
}
