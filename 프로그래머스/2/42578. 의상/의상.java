import java.util.*;

class Solution {
    public int solution(String[][] clothes) {
        int answer = 1;
        HashMap<String, Integer> map = new HashMap<>();

        for (String[] cloth : clothes) {
            map.put(cloth[1], map.getOrDefault(cloth[1], 0) + 1);
        }

        for (String s : map.keySet()) {
            int num = map.get(s);
            answer *= (num + 1);
        }
        answer--;

        return answer;
    }
}