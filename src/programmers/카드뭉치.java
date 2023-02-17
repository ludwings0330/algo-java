package programmers;

public class 카드뭉치 {

    public static void main(String[] args) {
        System.out.println(solution(new String[] { "i", "drink", "water" }, new String[] { "want", "to" },
                                    new String[] { "i", "want", "to", "drink", "water" }));
        System.out.println(solution(new String[] { "i", "water", "drink" }, new String[] { "want", "to" },
                                    new String[] { "i", "want", "to", "drink", "water" }));
    }

    public static String solution(String[] cards1, String[] cards2, String[] goal) {
        int first = 0, second = 0;

        for (String target :
                goal) {
            if (first < cards1.length && target.equals(cards1[first])) {
                first++;
            } else if (second < cards2.length && target.equals(cards2[second])) {
                second++;
            } else {
                return "NO";
            }
        }
        return "YES";
    }

}
