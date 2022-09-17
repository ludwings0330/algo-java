package swexpert.생산시스템;

import java.util.*;

class UserSolution {
    int L, M;
    HashMap<Integer, Product> hashProductInfo = new HashMap<>();

    Line[] arrayLineInfo;
    Tool[] arrayToolInfo;

    Queue<Product>[] queLines;
    PriorityQueue<Product>[] pqTools;

    Comparator comparator = new Comparator<Product>() {
        @Override
        public int compare(Product o1, Product o2) {
            return o1.mLine - o2.mLine;
        }
    };

    void init(int L, int M) {
        this.L = L;
        this.M = M;

        hashProductInfo.clear();

        arrayLineInfo = new Line[L];
        queLines = new Queue[L];
        for (int i = 0; i < L; i++) {
            queLines[i] = new LinkedList<>();
        }

        arrayToolInfo = new Tool[M];
        pqTools = new PriorityQueue[M];
        for (int i = 0; i < M; i++) {
            pqTools[i] = new PriorityQueue<>(comparator);
        }


        return;
    }

    int request(int tStamp, int pId, int mLine, int eId, int mTime) {
        Product product = new Product(tStamp, pId, mLine, eId, mTime);
        if (arrayLineInfo[mLine] == null) {
            // 해당 라인이 생산 중이 아니라면
            if (arrayToolInfo[pId] == null) {
                // 해당 도구가 사용 중이 아니라면
                // 해당 라인 정보에 Product 객체 저장
                arrayLineInfo[mLine] = new Line(product);
                arrayToolInfo[pId] = new Tool(product);

                arrayToolInfo[pId].startTime = tStamp; // 툴 시작 시간
                arrayToolInfo[pId].endTime = tStamp + product.mTime; // 툴 종료 시간

                arrayLineInfo[mLine].startTime = tStamp; // 라인 시작시간
                arrayLineInfo[mLine].endTime = tStamp + product.mTime; // 라인 종료 시간

                product.state = 2; // 생산 중 표시
            } else {
                // 해당 도구가 사용 중이라면
                // pq에 저장
                pqTools[pId].offer(product);
                // 해당 도구를 사용하지 못하므로 실행을 못함
                queLines[mLine].offer(product);
            }
        } else {
            // 해당 라인이 생산 중이라면
            // 해당 라인 queue 에 product 객체 저장
            queLines[mLine].offer(product);
        }

        // 시스템을 현재 상태로 업데이트
        updateSystem(tStamp);

        // 생산중인 제품의 id 반환, 생산 중인 제품이 없는 경우 -1 반환
        return (arrayLineInfo[mLine] == null) ? -1 : arrayLineInfo[mLine].product.pId;
    }

    // 전체 시스템을 tStamp 로 업데이트한다.
    private void updateSystem(int tStamp) {
        for (int i = 0; i < L; i++) {
            if (arrayLineInfo[i] == null) continue;
            if (arrayLineInfo[i].endTime <= tStamp) {
                Product product = arrayLineInfo[i].product;
                // 생산 완료 표시
                product.state = 3;

                // 도구 반납
                // 도구 반납하면서 -> 이 도구가 필요한 애들 확인
                // -> 이 도구가 필요한애의 line 이 비어있다면 -> line 에 올림

            } else {
                continue;
            }
        }
    }

    int status(int tStamp, int pId) {
        // 시스템을 현재 상태로 업데이트
        updateSystem(tStamp);

        // 요청된적 없으면 0 반환
        if (!hashProductInfo.containsKey(pId)) return 0;
        // 요청된적 있으면 현재 state 반환
        return hashProductInfo.get(pId).state;
    }

    class Product {
        int tStamp; // 요청 시간
        int pId; // 제품 생산 id
        int mLine; // 생산 라인
        int eId; // 필요장비 id
        int mTime; // 소요 시간
        int state; // 1 : 생산 라인에서 대기중, 2 : 생산 중, 3 : 생산 완료

        public Product(int tStamp, int pId, int mLine, int eId, int mTime) {
            this.tStamp = tStamp;
            this.pId = pId;
            this.mLine = mLine;
            this.eId = eId;
            this.mTime = mTime;
            this.state = 1;
        }
    }

    class Line {
        int startTime;
        int endTime;
        Product product;

        public Line(Product product) {
            this.startTime = -1;
            this.product = product;
        }
    }

    class Tool {
        int startTime;
        int endTime;
        Product product;

        public Tool(Product product) {
            this.startTime = -1;
            this.product = product;
        }
    }
}