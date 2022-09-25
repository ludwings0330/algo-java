package Test.KAKAO2023BLINDTEST;

import java.util.ArrayList;
import java.util.HashMap;

public class E {
    static Cell[][] board;
    static HashMap<String, ArrayList<Cell>> hashValue;
    static HashMap<Integer, ArrayList<Cell>> hashGroup;
    static int groupNo = 1;

    public static String[] solution(String[] commands) {
        ArrayList<String> answer = new ArrayList<>();

        board = new Cell[51][];
        for (int r = 0; r < 51; r++) {
            board[r] = new Cell[51];
            for (int c = 0; c < 51; c++) {
                board[r][c] = new Cell(r, c);
            }
        }

        hashValue = new HashMap<>();

        for (String command :
                commands) {
            final String[] cmd = command.split(" ");
            if (cmd[0].equals("UPDATE")) {
                if (cmd.length == 4) {
                    int r = Integer.parseInt(cmd[1]);
                    int c = Integer.parseInt(cmd[2]);
                    String nextValue = cmd[3];
                    // update r c value

                    if (!hashValue.containsKey(nextValue)) {
                        hashValue.put(nextValue, new ArrayList<>());
                    }
                    // 기존 value 에서 삭제
                    Cell selected = board[r][c];
                    final ArrayList<Cell> cells = hashValue.get(selected.value);
                    if (cells != null)
                        cells.remove(selected);
                    // 새로운 value 로 추가
                    hashValue.get(nextValue).add(selected);

                    // board 의 value 변경
                    selected.value = nextValue;
                } else if (cmd.length == 3) {
                    // update value1 value2
                    String currentValue = cmd[1];
                    String nextValue = cmd[2];

                    if (!hashValue.containsKey(nextValue)) {
                        hashValue.put(nextValue, new ArrayList<>());
                    }

                    final ArrayList<Cell> cells = hashValue.get(currentValue);

                    for (Cell cell :
                            cells) {
                        // board 의 value 변경
                        cell.value = nextValue;
                        // 새로운 value 로 추가
                        hashValue.get(nextValue).add(cell);
                    }

                    // 기존 value 에서 삭제
                    hashValue.put(currentValue, new ArrayList<>());
                }
            } else if (cmd[0].equals("MERGE")) {
                int r1 = Integer.parseInt(cmd[1]);
                int c1 = Integer.parseInt(cmd[2]);
                int r2 = Integer.parseInt(cmd[3]);
                int c2 = Integer.parseInt(cmd[4]);

                Cell cellA = board[r1][c1];
                Cell cellB = board[r2][c2];

                String value = "";
                // 둘다 있으면 무조건 첫번째 걸로
                if (cellA.value.length() > 0 && cellA.value.length() > 0) {
                    value = cellA.value;
                } else {
                    // 둘중에 있는 값으로 설정
                    value = (cellA.value.length() > cellB.value.length()) ? cellA.value : cellB.value;
                }

                cellA.value = value;
                cellB.value = value;
                groupNo++;
                hashGroup.put(groupNo, new ArrayList<>());

                if (cellA.group != -1) {
                    for (Cell element :
                            hashGroup.get(cellA.group)) {
                        element.group = groupNo;
                        hashGroup.get(groupNo).add(element);
                    }
                }
                if (cellB.group != -1) {
                    for (Cell element :
                            hashGroup.get(cellB.group)) {
                        element.group = groupNo;
                        hashGroup.get(groupNo).add(element);
                    }
                }
                cellA.group = groupNo;
                cellB.group = groupNo;

            } else if (cmd[0].equals("UNMERGE")) {
                int r = Integer.parseInt(cmd[1]);
                int c = Integer.parseInt(cmd[2]);

                Cell cell = board[r][c];
                if (cell.group != -1) {
                    for (Cell element :
                            hashGroup.get(cell.group)) {
                        element.group = -1;
                        element.value = "";
                    }

                    hashGroup.put(cell.group, new ArrayList<>());
                }

            } else if (cmd[0].equals("PRINT")) {
                int r = Integer.parseInt(cmd[1]);
                int c = Integer.parseInt(cmd[2]);
                String ans = board[r][c].value;
                answer.add((ans.equals("")) ? "EMPTY" : ans);
            }

        }

        return answer.toArray(new String[0]);
    }

    public static void main(String[] args) {
//        solution(new String[]{"UPDATE 1 1 menu", "UPDATE 1 2 category", "UPDATE 2 1 bibimbap", "UPDATE 2 2 korean", "UPDATE 2 3 rice", "UPDATE 3 1 ramyeon", "UPDATE 3 2 korean", "UPDATE 3 3 noodle", "UPDATE 3 4 instant", "UPDATE 4 1 pasta", "UPDATE 4 2 italian", "UPDATE 4 3 noodle", "MERGE 1 2 1 3", "MERGE 1 3 1 4", "UPDATE korean hansik", "UPDATE 1 3 group", "UNMERGE 1 4", "PRINT 1 3", "PRINT 1 4"});
        solution(new String[]{"UPDATE 1 1 a", "UPDATE 1 2 b", "UPDATE 2 1 c", "UPDATE 2 2 d", "MERGE 1 1 1 2", "MERGE 2 2 2 1", "MERGE 2 1 1 1", "PRINT 1 1", "UNMERGE 2 2", "PRINT 1 1"});
    }

    static class Cell {
        int r, c;
        String value;
        int group;

        public Cell(int r, int c) {
            this.r = r;
            this.c = c;
            this.value = "";
            this.group = -1;
        }
    }
}
