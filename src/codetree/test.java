package codetree;

import java.util.HashMap;
import java.util.Map;

public class test {

    public static void main(String[] args) {
        Map<String, String> testMap = new HashMap<>();
        final String result = testMap.compute("keyA", (k, v) -> "valueA");
        System.out.println("result = " + result);
        System.out.println("testMap.get(\"keyA\") = " + testMap.get("keyA"));
    }

}
