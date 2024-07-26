import java.util.*;

class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;
        int totalCount = want.length;
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < want.length; i++) {
            map.put(want[i], number[i]);
        }
        
        int count = 0;
        for (int i = 0; i < 10; i++) {
            if (map.containsKey(discount[i])) {
                if (map.get(discount[i]) == 0) {
                    count--;
                }
                map.put(discount[i], map.get(discount[i]) - 1);
                if (map.get(discount[i]) == 0) {
                    count++;
                }
            }
        }
        if (count == totalCount) {
            answer++;
        }
        
        int lt = 0;
        int rt = 10;
        while (rt < discount.length) {
            String ls = discount[lt];
            String rs = discount[rt];
            
            if (map.containsKey(ls)) {
                if (map.get(ls) == 0) {
                    count--;
                }
                map.put(ls, map.get(ls) + 1);
                if (map.get(ls) == 0) {
                    count++;
                }
            }
            
            if (map.containsKey(rs)) {
                if (map.get(rs) == 0) {
                    count--;
                }
                map.put(rs, map.get(rs) - 1);
                if (map.get(rs) == 0) {
                    count++;
                }
            }
            
            if (count == totalCount) {
                answer++;
            }
            
            lt++;
            rt++;
        }
        
        return answer;
    }
}