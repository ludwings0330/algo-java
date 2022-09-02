package codetree.novicemid.객체정렬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class 객체정렬 {
    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Student[] studentList = new Student[N];
        for (int i = 0; i < N; i++) {
            final StringTokenizer st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            int height = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            studentList[i] = new Student(name, height, weight);
        }
        Arrays.sort(studentList);
        Arrays.stream(studentList).forEach(System.out::println);
    }

    static class Student implements Comparable<Student> {
        String name;
        int height;
        int weight;

        public Student(String name, int height, int weight) {
            this.name = name;
            this.height = height;
            this.weight = weight;
        }

        @Override
        public int compareTo(Student o) {
            return this.height - o.height;
        }

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer();
            sb.append(name).append(" ").append(height).append(" ").append(weight);
            return sb.toString();
        }
    }
}
