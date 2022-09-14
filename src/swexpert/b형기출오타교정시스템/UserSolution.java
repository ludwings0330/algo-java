package swexpert.b형기출오타교정시스템;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

class UserSolution {

    // The below commented methods are for your reference. If you want
// to use it, uncomment these methods.
    Log[] logs;
    HashMap<String, ArrayList<String>> database;
    HashMap<String, HashMap<String, HashSet<Integer>>> candidate;

    int mstrcmp(char[] a, char[] b) {
        int i;
        for (i = 0; a[i] != '\0'; i++) {
            if (a[i] != b[i])
                return a[i] - b[i];
        }
        return a[i] - b[i];
    }

    int mstrncmp(char[] a, char[] b, int len) {
        for (int i = 0; i < len; i++) {
            if (a[i] != b[i])
                return a[i] - b[i];
        }
        return 0;
    }

    int mstrlen(char[] a) {
        int len = 0;

        while (a[len] != '\0')
            len++;

        return len;
    }

    void mstrcpy(char[] dest, char[] src) {
        int i = 0;
        while (src[i] != '\0') {
            dest[i] = src[i];
            i++;
        }
        dest[i] = src[i];
    }

    void mstrncpy(char[] dest, char[] src, int len) {
        for (int i = 0; i < len; i++) {
            dest[i] = src[i];
        }
        dest[len] = '\0';
    }

    void init(int n) {
        // 사용자 id 만큼 초기화 1<= id <= n
        logs = new Log[n + 1];
        for (int i = 0; i <= n; i++) {
            logs[i] = new Log(0, null);
        }
        database = new HashMap<>();
        candidate = new HashMap<>();
    }

    int search(int mId, int searchTimestamp, char[] searchWord, char[][] correctWord) {
        Log pastLog = logs[mId];
        Log currentLog = new Log(searchTimestamp, searchWord);
        boolean isTypo = isTypoInput(currentLog, pastLog);

        int ret = 0;
        String key = String.valueOf(searchWord);
        if (database.containsKey(key)) {
            for (String correct :
                    database.get(key)) {
                correctWord[ret++] = correct.toCharArray();
            }
        }

        if (isTypo) {
            // 오타 교정 DB 등록 가능 여부 확인
            if (isAvailable(currentLog.word, pastLog.word)) {
                // 등록가능하면 후보에 등록
                // 후보에 등록했을때 사용자 id 수가 3개가되면 database 에 등록
                int numberOfUser = registerToCandidate(currentLog.word, pastLog.word, mId);
                if (numberOfUser == 3) {
                    registerToDatabase(currentLog.word, pastLog.word);
                }
            }
        }

        // 사용자 입력을 최신으로 변경
        logs[mId] = currentLog;
        return ret;
    }

    private void registerToDatabase(char[] currentWord, char[] pastWord) {
        String correct = String.valueOf(currentWord);
        String typo = String.valueOf(pastWord);

        if (!database.containsKey(typo)) {
            database.put(typo, new ArrayList<>());
        }
        database.get(typo).add(correct);
    }

    private int registerToCandidate(char[] currentWord, char[] pastWord, int mId) {
        String correct = String.valueOf(currentWord);
        String typo = String.valueOf(pastWord);

        if (!candidate.containsKey(typo)) {
            candidate.put(typo, new HashMap<>());
        }

        final HashMap<String, HashSet<Integer>> tmp = candidate.get(typo);
        if (!tmp.containsKey(correct)) {
            tmp.put(correct, new HashSet<>());
        }
        if (tmp.get(correct).contains(mId)) return -1;
        tmp.get(correct).add(mId);
        return candidate.get(typo).get(correct).size();
    }

    private boolean isAvailable(char[] currentWord, char[] pastWord) {
        int cnt = 0;
        int currentLen = mstrlen(currentWord);
        int pastLen = mstrlen(pastWord);
        int diff = pastLen - currentLen;

        if (diff == -1) {
            // 1. 글자 하나가 추가됨
            cnt += (isOneLetterAdded(currentWord, pastWord)) ? 1 : 0;
        } else if (diff == 1) {
            // 2. 글자 하나가 삭제됨
            cnt += (isOneLetterDeleted(currentWord, pastWord)) ? 1 : 0;
        } else if (diff == 0) {
            cnt += (isOneLetterChanged(currentWord, pastWord)) ? 1 : 0;
        }
        // 3. 글자 하나가 변경됨
        return cnt == 1;
    }

    private boolean isOneLetterChanged(char[] currentWord, char[] pastWord) {
        int diffCount = 0;
        for (int i = 0; i < mstrlen(currentWord); i++) {
            if (currentWord[i] != pastWord[i])
                diffCount++;
        }

        return diffCount == 1;
    }

    private boolean isOneLetterDeleted(char[] currentWord, char[] pastWord) {
        // current 가 1개 짧음
        int diffCount = 0;
        int j = 0;
        for (int i = 0; i < mstrlen(pastWord); i++) {
            if (pastWord[i] != currentWord[j]) {
                diffCount++;
                continue;
            }
            j++;
        }

        return diffCount == 1;
    }

    private boolean isOneLetterAdded(char[] currentWord, char[] pastWord) {
        // current 가 1개 많음
        int diffCount = 0;
        int j = 0;
        for (int i = 0; i < mstrlen(currentWord); i++) {
            if (currentWord[i] != pastWord[j]) {
                diffCount++;
                continue;
            }
            j++;
        }
        return diffCount == 1;
    }

    private boolean isTypoInput(Log currentLog, Log pastLog) {
        // 과거 time 이 0 이면 false
        // 과거 time 이 0 이 아니고, 입력 시간이 10초 이내면 수정
        return pastLog.time != 0 && currentLog.time - pastLog.time <= 10;
    }

    public static class Log {
        int time;
        char[] word;

        public Log(int time, char[] word) {
            this.time = time;
            this.word = word;
        }
    }
}
