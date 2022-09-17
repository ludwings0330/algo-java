package swexpert.이미지검색하기;

import java.util.ArrayList;

class UserSolution {
    final int MAX_N = 10000;
    final int MAX_M = 10;
    int N;
    int M;
    char[][][] images;
    // n번 이미지의 1의 갯수를 세둔다.
    int[] cache;

    void init(int N, int M, char mImageList[][][]) {
        images = mImageList;
        cache = new int[N];

        this.N = N;
        this.M = M;

        for (int n = 0; n < N; n++) {
            for (int r = 0; r < M; r++) {
                for (int c = 0; c < M; c++) {
                    if (mImageList[n][r][c] == 1) {
                        cache[n]++;
                    }
                }
            }
        }
    }

    int findImage(char mImage[][]) {
        int numberOfOne = 0;

        for (int r = 0; r < M; r++) {
            for (int c = 0; c < M; c++) {
                if (mImage[r][c] == 1)
                    numberOfOne++;
            }
        }

        ArrayList<Integer> candidates = new ArrayList<>();
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            int diff = Math.abs(cache[i] - numberOfOne);
            if (diff <= 2) {
                candidates.add(i);
            }
        }

        min = 3;
        int id = 0;
        for (int currentID :
                candidates) {
            char[][] image = images[currentID];
            int count = 0;
            for (int r = 0; r < M; r++) {
                for (int c = 0; c < M; c++) {
                    if (image[r][c] != mImage[r][c])
                        count++;
                    if (count >= min) break;
                }
                if (count >= min) break;
            }
            if (count < min) {
                min = count;
                id = currentID;
            }
        }

        return id + 1;
    }
}

