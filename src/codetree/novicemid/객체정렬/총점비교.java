package codetree.novicemid.객체정렬;

import java.io.*;
import java.util.*;

public class 총점비교 {
    public static void main(String[] args) {
        // Your Program Goes Here
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        Student[] students = new Student[N];
        for(int i = 0 ; i < N ; i++) {
            String name = sc.next();
            int kor = sc.nextInt();
            int eng = sc.nextInt();
            int math = sc.nextInt();

            students[i] = new Student(name, kor, eng, math);
        }
        Arrays.sort(students);
        for(int i = 0 ; i < N ; i++) {
            System.out.println(students[i].name + " " + students[i].kor + " " + students[i].eng + " " + students[i].math);
        }
    }

    static class Student implements Comparable<Student> {
        String name;
        int kor, eng, math;

        public Student(String name, int kor, int eng, int math) {
            this.name = name;
            this.kor = kor;
            this.eng = eng;
            this.math = math;
        }

        public int compareTo(Student o) {
            return this.kor + this.eng + this.math - (o.kor + o.eng + o.math);
        }
    }
}