package swexpert.세젤예어플리케이션;

import java.util.HashMap;
import java.util.HashSet;

public class UserSolution {
    HashMap<String, Cell> cells;
    int MOD = 100000007;

    public void initTable() {
        // TO DO
        cells = new HashMap<String, Cell>();
    }

    public boolean updateCell(int row, int col, char equation[], int value[][]) {
        // TO Do
        boolean ret = true;

        // 새로운 값 입력
        Cell createdCell = new Cell(equation, row, col);
        cells.put(createdCell.strPos, createdCell);

        // 미결정셀 모두 확인
        HashSet<String> visited = new HashSet<>();
        HashSet<String> loop = new HashSet<>();
        HashSet<Cell> undecidedCells = new HashSet<>();

        for (Cell current :
                cells.values()) {

            visited.add(current.strPos);
            loop.add(current.strPos);
            boolean result = dfs(current, visited, undecidedCells, loop);
            loop.remove(current.strPos);
        }

        // 미결정셀들 값 입력
        int size = undecidedCells.size();
        ret = (size == 0);

        for (Cell cell :
                undecidedCells) {
            cell.value = size;
        }

        // 정답 입력
        for (int r = 0; r < 99; r++) {
            for (int c = 0; c < 26; c++) {
                value[r][c] = getValue(r, c);
            }
        }

        return ret; // Need to be changed
    }

    private boolean dfs(Cell current, HashSet<String> visited,
                        HashSet<Cell> undecidedCells, HashSet<String> loop) {
        // current 의 equation 을 파싱해야한다.
        // 1. 숫자인경우
        // -> 맨앞이 = 이 아니라 숫자
        // 2. equation 인 경우
        // -> 맨앞이 = 이고 그 뒤에 수식이 따름
        current.determinable = true;
        char[] equation = current.equation.toCharArray();

        // 수식이라면
        if (equation[0] == '=') {
            int idx = 1;
            int value = 0;
            while (idx < equation.length) {
                StringBuilder sb = new StringBuilder();
                sb.append(equation[idx]);
                idx++;
                while (idx < equation.length &&
                        (equation[idx] != '-' && equation[idx] != '+')) {
                    sb.append(equation[idx]);
                    idx++;
                }
                // 여기서 sb.toString 은 +3, -3, 3 혹은 -A13, +A13, A13
                String subEquation = sb.toString();
                try {
                    // 변환할때 에러가 안나면 +3, -3, 3
                    int v = Integer.valueOf(subEquation);
                    value += v;
                    value %= MOD;
                } catch (Exception e) {
                    // 변환할때 에러가 나면 -A13, +A13, A13
                    String strPos = subEquation;
                    char operator = '+';
                    if (subEquation.charAt(0) == '+' || subEquation.charAt(0) == '-') {
                        strPos = subEquation.substring(1);
                        operator = subEquation.charAt(0);
                    }

                    // 본인을 참조하는 경우 미결정
                    if (strPos.equals(current.strPos)) {
                        current.determinable = false;
                    }

                    // 내용이 존재하는 셀이면 확인을 해야함
                    // 내용이 존재하지 않으면 0 을 더해서 처리가 필요없음
                    else if (cells.containsKey(strPos)) {
                        Cell next = cells.get(strPos);

                        // 다움 위치 방문 여부
                        if (visited.contains(strPos)) {
                            if (loop.contains(strPos)) {
                                // 다음 cell 이 이번 루프에 포함되는 경우
                                current.determinable = false;
                            } else {
                                // 다음위치를 방문했지만 이번 루프는 아닐때에는 다음위치의 결정 여부에 따라 결정된다
                                current.determinable = current.determinable && next.determinable;
                            }
                        } else {
                            // 미방문이면 무조건 들어감
                            visited.add(strPos);
                            loop.add(strPos);
                            // next 의 결정여부에 따라 current의 결정여부가 결정됨.
                            current.determinable = current.determinable && dfs(next, visited, undecidedCells, loop);
                            loop.remove(strPos);
                        }
                        if (operator == '+')
                            value += next.value;
                        else
                            value -= next.value;
                        value %= MOD;
                    }
                }
            }
            // 현재 cell 에 계산 결과를 저장
            current.value = value;
        } else {
            // 단순 숫자일 경우 값을 변경
            current.value = Integer.valueOf(current.equation);
            current.determinable = true;
        }

        if (!current.determinable) {
            undecidedCells.add(current);
        }

        return current.determinable;
    }

    private int getValue(int r, int c) {
        String pos = toStrPos(r, c);

        // 해당 셀 정보가 존재하면 value 를 return
        if (cells.containsKey(pos)) {
            return cells.get(pos).value;
        }
        // 정보가 없으면 0 으로 판단
        return 0;
    }

    // r, c 를 A1, B11 등 문자열 위치정보로 변환
    private String toStrPos(int r, int c) {
        r++;
        String ch = "" + (char) ('A' + c);
        return ch + r;
    }

    class Cell {
        String equation;
        int value, r, c;
        String strPos;
        boolean determinable;

        public Cell(char[] equation, int r, int c) {
            this.equation = String.valueOf(equation).trim();
            this.r = r;
            this.c = c;
            this.strPos = toStrPos(r, c);
            this.determinable = true;
            this.value = 0;
        }
    }
}
