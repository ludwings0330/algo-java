package Test.KAKAO2023BLINDTEST;

public class C {
    static int[] discount = {10, 20, 30, 40};
    static int[] choice;
    static int answerMember;
    static int answerSale;

    public static int[] solution(int[][] users, int[] emoticons) {
        choice = new int[emoticons.length];
        answerMember = 0;
        answerSale = 0;
        permutations(users, emoticons, 0);

        int[] answer = new int[]{answerMember, answerSale};
        return answer;
    }

    static void permutations(int[][] users, int[] emoticons, int idx) {
        if (idx == emoticons.length) {
            // [비율, 가격]
            int totalSale = 0;
            int totalMember = 0;

            // 모든 유저들에 대해서 확인
            // 그냥 가입할거야? 모두 구매할거야?
            for (int[] user :
                    users) {
                // 해당 유저가 구매하는 총 금액
                int sale = 0;

                // 모든 이모티콘의 할인율 확인
                for (int i = 0; i < emoticons.length; i++) {
                    // 할인율이 일정 비율 이상이라면 구매한다.
                    if (discount[choice[i]] >= user[0]) {
                        // 구매한다
                        sale += (emoticons[i] * (100 - discount[choice[i]])) / 100.0;
                    }
                }
                // 총 구매비용이 유저가 일정 금액 이상이라면 구매를 취소
                if (sale >= user[1]) {
                    // 모든 구매를 취소하고
                    sale = 0;
                    // 구독한다
                    totalMember += 1;
                }
                // 총 판매 금액
                totalSale += sale;
            }
            // 총 구독자가 기존보다 크면
            if (totalMember > answerMember) {
                // 모두 대체 한다
                answerMember = totalMember;
                answerSale = totalSale;
            } else if (totalMember == answerMember) {
                // 기존 구독자와 수가 같으면
                // 더 큰 매출을 선택한다.
                answerSale = Math.max(answerSale, totalSale);
            }
            return;
        }

        for (int i = 0; i < discount.length; i++) {
            choice[idx] = i;
            permutations(users, emoticons, idx + 1);
        }
    }

    public static void main(String[] args) {
        solution(new int[][]{{40, 2900}, {23, 10000}, {11, 5200}, {5, 5900}, {40, 3100}, {27, 9200}, {32, 6900}}, new int[]{1300, 1500, 1600, 4900});
    }
}
