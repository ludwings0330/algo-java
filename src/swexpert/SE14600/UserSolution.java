package swexpert.SE14600;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

class UserSolution {
    List<Student>[] teams;
    List<Student> students;
    PriorityQueue<Student>[] pqTeams;

    public void init() {
        teams = new LinkedList[6];
        pqTeams = new PriorityQueue[6];
        students = new LinkedList<>();

        for (int i = 0; i < 6; i++) {
            teams[i] = new LinkedList<>();
            pqTeams[i] = new PriorityQueue<>();
        }
    }

    public void hire(int mID, int mTeam, int mScore) {
        Student student = new Student(mID, mTeam, mScore);

        pqTeams[mTeam].offer(student);
        int idx = binarySearch(students, mID);
        students.add(idx + 1, student);
        idx = binarySearch(teams[mTeam], mID);
        teams[mTeam].add(idx + 1, student);
    }

    public void fire(int mID) {
        int idx = binarySearch(students, mID);
        students.get(idx).isExist = false;
    }

    // 처음으로 targetID 보다 커지거나 같은 곳의 위치를 찾아야한다.

    private int binarySearch(List<Student> students, int targetID) {
        int left = 0, right = students.size() - 1;
        int mid = -1;

        while (left < right) {
            mid = (left + right) / 2;
            int midID = students.get(mid).id;

            if (midID < targetID) {
                // mid가 target 보다 작으면 정답이 될 수 있음
                // left를 키워야함
                left = mid + 1;
            } else {
                // mid가 target 보다 크거나 같으면 정답이 될 수 있음,
                // right를 줄여야함
                right = mid;
            }
        }
        return right;
    }

    public void updateStudent(int mID, int mScore) {
        int idx = binarySearch(students, mID);
        students.get(idx).score = mScore;
    }

    public void updateTeam(int mTeam, int mChangeScore) {
        for (Student student :
                teams[mTeam]) {
            if (mChangeScore > 0) {
                student.score = Math.min(5, student.score + mChangeScore);
            } else {
                student.score = Math.max(1, student.score + mChangeScore);
            }
        }
    }

    public int bestStudent(int mTeam) {
        while (!pqTeams[mTeam].peek().isExist) {
            pqTeams[mTeam].poll();
        }

        return pqTeams[mTeam].peek().id;
    }

    private class Student implements Comparable<Student> {
        int id, team, score;
        boolean isExist;

        public Student(int id, int team, int score) {
            this.id = id;
            this.team = team;
            this.score = score;
            this.isExist = true;
        }

        @Override
        public int compareTo(Student o) {
            if (this.score != o.score) {
                return o.score - this.score;
            }
            return o.id - this.id;
        }
    }
}