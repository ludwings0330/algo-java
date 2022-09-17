package swexpert.메모장프로그램;


//class UserSolution {
//    int H, W;
//    int cursor;
//    LinkedList<Character> notepad;
//    HashMap<Character, Integer> hashTotalCount;
//    HashMap<Character, Integer> hashForwardCount;
//
//    void init(int H, int W, char mStr[]) {
//        this.H = H;
//        this.W = W;
//        this.cursor = 0;
//
//        notepad = new LinkedList<>();
//        notepad = new <>();
//        hashTotalCount = new HashMap<>();
//        hashForwardCount = new HashMap<>();
//
//        for (char ch :
//                mStr) {
//            if (ch == 0) break;
//            notepad.add(ch);
//
//            if (!hashTotalCount.containsKey(ch)) {
//                hashTotalCount.put(ch, 0);
//            }
//
//            hashTotalCount.replace(ch, hashTotalCount.get(ch) + 1);
//        }
//    }
//
//    void insert(char mChar) {
//        notepad.add(cursor, mChar);
//        hashTotalCount.put(mChar, hashTotalCount.getOrDefault(mChar, 0) + 1);
//        hashForwardCount.put(mChar, hashForwardCount.getOrDefault(mChar, 0) + 1);
//        cursor++;
//    }
//
//    char moveCursor(int mRow, int mCol) {
//        cursor = Math.min(notepad.size(), (mRow - 1) * W + mCol - 1);
//        hashForwardCount = new HashMap<>();
//
//        if (cursor < notepad.size()) {
//            int cnt = 0;
//            final Iterator<Character> iter = notepad.iterator();
//            while (cnt++ < cursor) {
//                char ch = iter.next();
//                hashForwardCount.put(ch, hashForwardCount.getOrDefault(ch, 0) + 1);
//            }
//
//            return iter.next();
//        }
//
//        return '$';
//    }
//
//    int countCharacter(char mChar) {
//        return hashTotalCount.getOrDefault(mChar, 0) - hashForwardCount.getOrDefault(mChar, 0);
//    }
//}

//
//class UserSolution {
//    int H, W;
//    int cursor;
//    int length;
//    StringBuilder notepad;
//
//    void init(int H, int W, char mStr[]) {
//        this.H = H;
//        this.W = W;
//        this.cursor = 0;
//        this.length = 0;
//        this.notepad = new StringBuilder();
//
//        for (char ch : mStr) {
//            if (ch == 0) break;
//            length++;
//            notepad.append(ch);
//        }
//    }
//
//    void insert(char mChar) {
//        notepad.insert(cursor, mChar);
//        cursor++;
//        length++;
//    }
//
//    char moveCursor(int mRow, int mCol) {
//        cursor = Math.min(length, (mRow - 1) * W + mCol - 1);
//
//        return (cursor < length) ? notepad.charAt(cursor) : '$';
//    }
//
//    int countCharacter(char mChar) {
//        int ret = 0;
//        for (int i = cursor; i < length; i++) {
//            if (notepad.charAt(i) == mChar)
//                ret++;
//        }
//
//        return ret;
//    }
//}

//class UserSolution {
//    int size;
//    int H, W;
//    Node head;
//    Node cursor;
//    Node tail;
//
//    void init(int H, int W, char mStr[]) {
//        size = 0;
//        head = new Node(' ');
//        tail = new Node(' ');
//        head.next = tail;
//        tail.prev = head;
//        this.H = H;
//        this.W = W;
//        for (char ch :
//                mStr) {
//            if (ch == 0) break;
//            size += 1;
//            tail.add(new Node(ch));
//        }
//        cursor = head.next;
//    }
//
//    void insert(char mChar) {
//        cursor.add(new Node(mChar));
//        size++;
//    }
//
//    char moveCursor(int mRow, int mCol) {
//        int pos = Math.min(size, (mRow - 1) * W + mCol - 1);
//        if (pos == size) {
//            cursor = tail;
//            return '$';
//        }
//
//        Node nextCursor = head.next;
//        for (int i = 0; i < pos; i++) {
//            nextCursor = nextCursor.next;
//        }
//
//        cursor = nextCursor;
//        return cursor.ch;
//    }
//
//    int countCharacter(char mChar) {
//        int ret = 0;
//        Node checker = cursor;
//        while (checker != tail) {
//            if (checker.ch == mChar) ret++;
//            checker = checker.next;
//        }
//
//        return ret;
//    }
//
//    static class Node {
//        Node next;
//        Node prev;
//        char ch;
//
//        public Node(char ch) {
//            this.ch = ch;
//            this.next = null;
//            this.prev = null;
//        }
//
//        public void add(Node node) {
//            node.next = this.prev.next;
//            node.prev = this.prev;
//            this.prev.next = node;
//            this.prev = node;
//        }
//    }
//}


//import java.util.Deque;
//import java.util.HashMap;
//import java.util.LinkedList;
//
//class UserSolution {
//    int H, W;
//    int cursor;
//    int length;
//    Deque<Character> left;
//    Deque<Character> right;
//    HashMap<Character, Integer> hashCount;
//
//    void init(int H, int W, char mStr[]) {
//        this.H = H;
//        this.W = W;
//        length = 0;
//        left = new LinkedList<>();
//        right = new LinkedList<>();
//        hashCount = new HashMap<>();
//
//        for (char ch :
//                mStr) {
//            if (ch == 0) break;
//            right.offerLast(ch);
//            hashCount.put(ch, hashCount.getOrDefault(ch, 0) + 1);
//            length++;
//        }
//    }
//
//    void insert(char mChar) {
//        left.offerLast(mChar);
//        length++;
//    }
//
//    char moveCursor(int mRow, int mCol) {
//        int cursor = Math.min(length, (mRow - 1) * W + mCol - 1);
//        if (cursor < left.size()) {
//            while (left.size() > cursor) {
//                right.offerFirst(left.pollLast());
//                char ch = right.peekFirst();
//                hashCount.put(ch, hashCount.getOrDefault(ch, 0) + 1);
//            }
//        } else {
//            while (left.size() < cursor) {
//                left.offerLast(right.pollFirst());
//                char ch = left.peekLast();
//                hashCount.put(ch, Math.max(0, hashCount.getOrDefault(ch, 0) - 1));
//            }
//        }
//        return (right.isEmpty()) ? '$' : right.peekFirst();
//    }
//
//    int countCharacter(char mChar) {
//        return hashCount.getOrDefault(mChar, 0);
//    }
//}
//
//import java.util.HashMap;
//import java.util.LinkedList;
//
//class UserSolution {
//    int H, W;
//    int cursor;
//    int length;
//    StringBuilder notepad;
//    HashMap<Character, LinkedList<Integer>> hashTotalCount;
//
//    void init(int H, int W, char mStr[]) {
//        this.H = H;
//        this.W = W;
//        this.cursor = 0;
//        this.length = 0;
//        this.notepad = new StringBuilder();
//        hashTotalCount = new HashMap<>();
//
//        for (int i = 0; i < mStr.length; i++) {
//            char ch = mStr[i];
//
//            if (ch == 0) break;
//            length++;
//
//            notepad.append(ch);
//        }
//    }
//
//    void insert(char mChar) {
//        notepad.insert(cursor, mChar);
//        cursor++;
//        length++;
//    }
//
//    char moveCursor(int mRow, int mCol) {
//        cursor = Math.min(length, (mRow - 1) * W + mCol - 1);
//
//        return (cursor < length) ? notepad.charAt(cursor) : '$';
//    }
//
//    int countCharacter(char mChar) {
//        return (int) notepad.substring(cursor).chars()
//                .filter(ch -> ch == mChar)
//                .count();
//    }
//}

class UserSolution {
    int H, W;
    int cursor;
    int length;
    StringBuilder notepad;


    int[][] cache;
    int[] alphas;

    void init(int H, int W, char mStr[]) {
        this.H = H;
        this.W = W;
        this.cursor = 0;
        this.length = 0;
        this.notepad = new StringBuilder();

        cache = new int[90001][26];
        alphas = new int[26];

        int idx = 0;
        for (char ch : mStr) {
            if (ch == 0) break;
            length++;
            notepad.append(ch);
            alphas[ch - 'a']++;

            for (int alpha = 0; alpha < 26; alpha++) {
                cache[idx][alpha] = alphas[alpha];
            }
            idx++;
        }
    }

    void insert(char mChar) {
        notepad.insert(cursor, mChar);

        alphas = new int[26];
        for (int i = 0; i < notepad.length(); i++) {
            char ch = notepad.charAt(i);
            alphas[ch - 'a']++;
            for (int alpha = 0; alpha < 26; alpha++) {
                cache[i][alpha] = alphas[alpha];
            }
        }

        cursor++;
        length++;
    }

    char moveCursor(int mRow, int mCol) {
        cursor = Math.min(length, (mRow - 1) * W + mCol - 1);
        return (cursor < length) ? notepad.charAt(cursor) : '$';
    }

    int countCharacter(char mChar) {
        int ret = 0;
//        for (char ch :
//                notepad.substring(cursor).toCharArray()) {
//            if (ch == mChar) ret++;
//        }
        return cache[length][mChar - 'a'] - cache[cursor][mChar - 'a'];
    }
}