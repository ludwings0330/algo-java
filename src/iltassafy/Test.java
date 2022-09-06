package iltassafy;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        final Scanner sc = new Scanner(System.in);
//        int N = sc.nextInt();
//        int[][] balls = new int[N][];
//        for (int i = 0; i < N; i++) {
//            balls[i] = new int[2];
//            balls[i][0] = sc.nextInt();
//            balls[i][1] = sc.nextInt();
//        }
//
//        System.out.println("getDistance = " + getDistance(balls[0], balls[1]));
        System.out.println("getAngle(0, 0, 0, 5) = " + getAngle(0, 0, 0, 0));
        System.out.println("getAngle(0, 0, 5, 5) = " + getAngle(0, 0, 5, 5));
        System.out.println("getAngle(0, 0, 5, 0) = " + getAngle(0, 0, 5, 0));
        System.out.println("getAngle(0, 0, 5, -5) = " + getAngle(0, 0, 5, -5));
        System.out.println("getAngle(0, 0, 0, -5) = " + getAngle(0, 0, 0, -5));
        System.out.println("getAngle(0, 0, -5, -5) = " + getAngle(0, 0, -5, -5));
        System.out.println("getAngle(0, 0, -5, 0) = " + getAngle(0, 0, -5, 0));
        System.out.println("getAngle(0, 0, -5, 5) = " + getAngle(0, 0, -5, 5));
    }

    public static double getDistance(int[] pos1, int[] pos2) {
        int dx = pos1[0] - pos2[0];
        int dy = pos1[1] - pos2[1];

        return Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
    }

    public static double getAngle(int whiteBallX, int whiteBallY, int targetBallX, int targetBallY) {
        double dx = targetBallX - whiteBallX;
        double dy = targetBallY - whiteBallY;
        double degree = Math.toDegrees(Math.atan2(dx, dy));
        return (degree >= 0) ? degree : degree + 360;
    }

}
