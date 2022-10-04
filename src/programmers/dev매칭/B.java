package programmers.dev매칭;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class B {
    static char[][] board;
    static boolean[][] visited;
    static int R, C;

    /*
    1. BFS 로 같은 덩어리 구분
    2. 같은 덩어리 내에서 나라별로 갯수세기
    3. sort 해서 내림차순 + 알파벳 내림차순해서 전쟁 이긴 나라 확인
    4. 전쟁 이긴 나라보다 영토가 작으면 변경
     */
    public static void main(String[] args) {
        System.out.println(solution(new String[]{"AABCA.QA", "AABC..QX", "BBBC.Y..", ".A...T.A", "....EE..", ".M.XXEXQ", "KL.TBBBQ"}));
        System.out.println(solution(new String[]{"AA", "AA", "AA", "AB"}));
        System.out.println(solution(new String[]{"BB", "AA", "BB", "AA"}));
        System.out.println(solution(new String[]{"..", "..", "..", ".."}));
        System.out.println(solution(new String[]{".B", "A.", ".B", "..", ".A", "B."}));
    }

    public static int solution(String[] maps) {
        int answer = 0;

        R = maps.length;
        C = maps[0].length();
        board = new char[R][];
        for (int r = 0; r < R; r++) {
            board[r] = maps[r].toCharArray();
        }
        visited = new boolean[R][C];

        HashMap<Character, Integer> countryCnt = new HashMap<>();

        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (visited[r][c] || board[r][c] == '.')
                    continue;

                Country country = dfs(r, c);
                countryCnt.put(country.name,
                        countryCnt.getOrDefault(country.name, 0) + country.count);
            }
        }
        PriorityQueue<Country> pq = new PriorityQueue<>();
        for (var country :
                countryCnt.entrySet()) {
            pq.offer(new Country(country.getKey(), country.getValue()));
        }
        if (pq.isEmpty())
            return 0;

        Country winner = pq.poll();
        return winner.count;
    }

    private static Country dfs(int r, int c) {
        // 나라별로 갯수세기
        HashMap<Character, Integer> countryCnt = new HashMap<>();
        Queue<Pair> que = new LinkedList<>();

        que.offer(new Pair(r, c));
        visited[r][c] = true;

        int[][] moves = new int[][]{{1, 0}, {-1, 0}, {0, -1}, {0, 1}};

        while (!que.isEmpty()) {
            Pair current = que.poll();
            countryCnt.put(board[current.r][current.c],
                    countryCnt.getOrDefault(board[current.r][current.c], 0) + 1);
            for (int[] move :
                    moves) {
                int nr = current.r + move[0];
                int nc = current.c + move[1];
                if (nr < 0 || nr >= R || nc < 0 || nc >= C ||
                        visited[nr][nc] || board[nr][nc] == '.')
                    continue;

                visited[nr][nc] = true;
                que.offer(new Pair(nr, nc));
            }
        }

        // find winner
        PriorityQueue<Country> pq = new PriorityQueue<>();
        for (var country :
                countryCnt.entrySet()) {
            pq.offer(new Country(country.getKey(), country.getValue()));
        }

        Country winner = pq.peek();

        // update

        boolean[][] updated = new boolean[R][C];
        int addCount = 0;
        que = new LinkedList<Pair>();
        que.offer(new Pair(r, c));
        updated[r][c] = true;
        while (!que.isEmpty()) {
            Pair current = que.poll();

            int cnt = countryCnt.get(board[current.r][current.c]);
            if (board[current.r][current.c] != winner.name &&
                    cnt < winner.count) {
                board[current.r][current.c] = winner.name;
                addCount++;
            }

            for (var move :
                    moves) {
                int nr = current.r + move[0];
                int nc = current.c + move[1];
                if (nr < 0 || nr >= R || nc < 0 || nc >= C ||
                        updated[nr][nc] || board[nr][nc] == '.')
                    continue;

                updated[nr][nc] = true;
                que.offer(new Pair(nr, nc));
            }
        }

        winner.count += addCount;

        return winner;
    }

    private static class Country implements Comparable<Country> {
        char name;
        int count;

        public Country(char name, int count) {
            this.name = name;
            this.count = count;
        }

        @Override
        public int compareTo(Country o) {
            // 갯수 내림차순
            if (this.count != o.count)
                return o.count - this.count;
            // 갯수가 같으면 이름 내림차순
            return o.name - this.name;
        }
    }

    private static class Pair {
        int r, c;

        public Pair(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}
