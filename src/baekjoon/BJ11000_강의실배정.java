package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ11000_강의실배정 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static PriorityQueue<Lesson> lessons;
    static PriorityQueue<Lesson> lessonsTmp;

    public static void main(String[] args) throws IOException {
        init();
        int answer = solve();

        System.out.println(answer);
    }

    private static int solve() {
        int ret = 0;

        while (!lessons.isEmpty()) {
            Lesson lesson = lessons.poll();
            while (!lessonsTmp.isEmpty() && lessonsTmp.peek().t <= lesson.s) {
                lessonsTmp.poll();
            }
            lessonsTmp.add(lesson);
            ret = Math.max(ret, lessonsTmp.size());
        }

        return ret;
    }

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());
        lessons = new PriorityQueue<>((l1, l2) -> l1.s - l2.s); // order by start time asc
        lessonsTmp = new PriorityQueue<>((l1, l2) -> l1.t - l2.t); // order by end time asc
        for (int i = 0; i < N; i++) {
            final StringTokenizer st = new StringTokenizer(br.readLine());
            int si = Integer.parseInt(st.nextToken());
            int ti = Integer.parseInt(st.nextToken());

            lessons.add(new Lesson(si, ti));
        }
    }

    static class Lesson {

        int s; // start time
        int t; // end time

        public Lesson(int s, int t) {
            this.s = s;
            this.t = t;
        }

    }

}
