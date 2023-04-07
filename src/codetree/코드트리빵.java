package codetree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 코드트리빵 {

    static int[][] board;
    static int n, m;
    static int[][] moves = { { -1, 0 }, { 0, -1 }, { 0, 1 }, { 1, 0 } };
    static List<BaseCamp> camps = new ArrayList<>();
    static List<Shop> shops = new ArrayList<>();
    static List<Person> persons = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        init();
        int answer = solve();

        System.out.println(answer);
    }

    private static int solve() {
        int time = 0;

        do {
            moveAllPersons(); // move and disable
            addPerson(time);

            time++;
        } while (persons.size() > 0);

        return time;
    }

    private static void addPerson(int time) {
        if (time >= m) {
            return;
        }

        // 자신이 가고 싶은 편의점과 가장 가까운 베이스 캠프로 이동한다.
        Pos pos = findShortestBaseCampWithShop(shops.get(time));

        board[pos.r][pos.c] = -1;

        persons.add(new Person(pos.r, pos.c, time));
    }

    private static Pos findShortestBaseCampWithShop(Shop shop) {
        Deque<Pos> deque = new ArrayDeque<>();
        PriorityQueue<Pos> pq = new PriorityQueue<>((p1, p2) -> {
            if (p1.initialDirection != p2.initialDirection) {
                return p1.initialDirection - p2.initialDirection;
            }

            if (p1.r != p2.r) {
                return p1.r - p2.r;
            }

            return p1.c - p2.c;
        });

        boolean[][] visited = new boolean[n][n];

        deque.add(new Pos(shop.r, shop.c, 0)); // 여기서 initialDirection 은 dist 로 쓰임
        visited[shop.r][shop.c] = true;

        // shop 에서 가장 가까운 곳을 찾아야 한다.
        while (!deque.isEmpty()) {
            Pos pos = deque.poll();

            for (BaseCamp camp :
                    camps) {
                if (pos.r == camp.r && pos.c == camp.c) {
                    pq.add(pos);
                }
            }

            for (int[] move :
                    moves) {
                int nr = pos.r + move[0];
                int nc = pos.c + move[1];

                if (nr < 0 || nr >= n || nc < 0 || nc >= n) {
                    continue;
                }

                if (visited[nr][nc] || board[nr][nc] == -1) {
                    continue;
                }

                visited[nr][nc] = true;
                deque.add(new Pos(nr, nc, pos.initialDirection + 1));
            }
        }

        return pq.peek();
    }

    private static void init() throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new int[n][];

        for (int r = 0; r < n; r++) {
            board[r] = new int[n];
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < n; c++) {
                board[r][c] = Integer.parseInt(st.nextToken());

                if (board[r][c] == 1) {
                    camps.add(new BaseCamp(r, c));
                }
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;

            shops.add(new Shop(r, c));
        }
    }

    private static void moveAllPersons() {
        List<Person> nextPerson = new ArrayList<>();

        for (Person person :
                persons) {
            Shop shop = shops.get(person.id);
            movePersonToShop(nextPerson, person, shop);
        }

        persons = nextPerson;
    }

    private static void movePersonToShop(List<Person> nextPerson, Person person, Shop shop) {
        Deque<Pos> deque = new ArrayDeque<>();
        boolean[][] visited = new boolean[n][n];
        visited[person.r][person.c] = true;

        for (int i = 0; i < moves.length; i++) {
            int nr = person.r + moves[i][0];
            int nc = person.c + moves[i][1];
            if (nr < 0 || nr >= n || nc < 0 || nc >= n) {
                continue;
            }

            // 이동할 수 없다면?
            if (visited[nr][nc] || board[nr][nc] == -1) {
                continue;
            }

            final Pos pos = new Pos(nr, nc, i);
            deque.add(pos);
            visited[pos.r][pos.c] = true;
        }

        int direction = -1;
        while (!deque.isEmpty()) {
            final Pos pos = deque.poll();
            if (pos.r == shop.r && pos.c == shop.c) {
                direction = pos.initialDirection;
                break;
            }

            for (int i = 0; i < moves.length; i++) {
                int nr = pos.r + moves[i][0];
                int nc = pos.c + moves[i][1];
                if (nr < 0 || nr >= n || nc < 0 || nc >= n) {
                    continue;
                }

                // 이동할 수 없다면 -> 이미 방문했거나, 방문할 수 없는 칸이거나
                if (visited[nr][nc] || board[nr][nc] == -1) {
                    continue;
                }

                final Pos nextPos = new Pos(nr, nc, pos.initialDirection);
                deque.add(nextPos);
                visited[nextPos.r][nextPos.c] = true;

            }
        }

        // 최단 방향으로 이동하고 현재위치 저장.
        person.r += moves[direction][0];
        person.c += moves[direction][1];
        if (person.r == shop.r && person.c == shop.c) {
            // 편의점에 도착했다면 추가하지 않는다.
            // 해당 칸은 올라올 수 없도록 표시한다.
            board[shop.r][shop.c] = -1;
        } else {
            nextPerson.add(person);
        }
    }

    static class Shop {

        int r, c;

        public Shop(int r, int c) {
            this.r = r;
            this.c = c;
        }

    }

    static class BaseCamp {

        int r, c;

        public BaseCamp(int r, int c) {
            this.r = r;
            this.c = c;
        }

    }

    static class Person {

        int r, c, id;

        public Person(int r, int c, int id) {
            this.r = r;
            this.c = c;
            this.id = id;
        }

    }

    static class Pos {

        int r, c;
        int initialDirection;

        public Pos(int r, int c, int initialDirection) {
            this.r = r;
            this.c = c;
            this.initialDirection = initialDirection;
        }

    }

}
