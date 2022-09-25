package Test.KAKAO2023BLINDTEST;

import java.util.Arrays;

public class F {
    public static String solution(int n, int m, int x, int y, int r, int c, int k) {
        String answer = "";
        int R = n;
        int C = m;
        int current_r = x;
        int current_c = y;
        int target_r = r;
        int target_c = c;
        final StringBuilder sb = new StringBuilder();
        // 아래 -> 왼쪽- > 오른쪽 -> 위쪽
        int[][] moves = {{1, 0}, {0, -1}, {0, 1}, {-1, 0}};
        while (k >= 0) {
            int dist = (Math.abs(target_r - current_r) + Math.abs(target_c - current_c));
            if (dist == k) {
                // 이제부터 최적으로 이동해야 도착할 수 있음
                int dr = target_r - current_r;
                int dc = target_c - current_c;
                final StringBuilder route = new StringBuilder();
                if (dr > 0) {
                    // target 이 더 위에 있으니까 위로 이동
                    for (; dr > 0; dr--) {
                        route.append('d');
                    }
                } else {
                    // target 이 더 아래 있으니까 아래로 이동
                    for (; dr < 0; dr++) {
                        route.append('u');
                    }
                }
                if (dc > 0) {
                    // target 이 더 오른쪽에 있으니 오른쪽으로 이동
                    for (; dc > 0; dc--) {
                        route.append('r');
                    }
                } else {
                    // target 이 더 왼쪽에 있으니 왼쪽으로 이동
                    for (; dc < 0; dc++) {
                        route.append('l');
                    }
                }
                char[] sortedRoute = route.toString().toCharArray();
                Arrays.sort(sortedRoute);
                sb.append(sortedRoute);
                break;
            } else if (dist > k) {
                // 아무리 용을 써도 도착을 못함
                return "impossible";
            }
            for (int i = 0; i < moves.length; i++) {
                int nr = current_r + moves[i][0];
                int nc = current_c + moves[i][1];

                if (nr < 1 || nr > R || nc < 1 || nc > C)
                    continue; // 움직일 수 없으면 다음 움직임으로

                current_r = nr;
                current_c = nc;
                switch (i) {
                    case 0:
                        sb.append("d");
                        break;
                    case 1:
                        sb.append("l");
                        break;
                    case 2:
                        sb.append("r");
                        break;
                    default:
                        sb.append("u");
                        break;
                }
                break;
            }
            k--;
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(solution(3, 4, 2, 3, 3, 1, 5));
        System.out.println(solution(2, 2, 1, 1, 2, 2, 2));
        System.out.println(solution(3, 3, 1, 2, 3, 3, 4));
    }
}
