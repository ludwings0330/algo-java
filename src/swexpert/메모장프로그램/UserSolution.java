package swexpert.메모장프로그램;


//class UserSolution {
//    int H, W;
//    int cursor;
//    LinkedList<Character> notepad;
//
//    void init(int H, int W, char mStr[]) {
//        this.H = H;
//        this.W = W;
//        this.cursor = 0;
//        notepad = new LinkedList<>();
//        for (char ch :
//                mStr) {
//            if (ch == 0) break;
//            notepad.add(ch);
//        }
//    }
//
//    void insert(char mChar) {
//        notepad.add(cursor, mChar);
//        cursor++;
//    }
//
//    char moveCursor(int mRow, int mCol) {
//        cursor = Math.min(notepad.size(), (mRow - 1) * W + mCol - 1);
//
//        return (cursor < notepad.size()) ? notepad.get(cursor) : '$';
//    }
//
//    int countCharacter(char mChar) {
//        int ret = 0;
//        final ListIterator<Character> iter = notepad.listIterator(cursor);
//
//        while (iter.hasNext()) {
//            if (mChar == iter.next()) ret++;
//        }
//
//        return ret;
//    }
//}


class UserSolution {
    int H, W;
    int cursor;
    int length;
    StringBuilder notepad;

    void init(int H, int W, char mStr[]) {
        this.H = H;
        this.W = W;
        this.cursor = 0;
        this.length = 0;
        this.notepad = new StringBuilder();

        for (char ch : mStr) {
            if (ch == 0) break;
            length++;
            notepad.append(ch);
        }
    }

    void insert(char mChar) {
        notepad.insert(cursor, mChar);
        cursor++;
        length++;
    }

    char moveCursor(int mRow, int mCol) {
        cursor = Math.min(length, (mRow - 1) * W + mCol - 1);

        return (cursor < length) ? notepad.charAt(cursor) : '$';
    }

    int countCharacter(char mChar) {
        int ret = 0;
        for (int i = cursor; i < length; i++) {
            if (notepad.charAt(i) == mChar)
                ret++;
        }

        return ret;
    }
}

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