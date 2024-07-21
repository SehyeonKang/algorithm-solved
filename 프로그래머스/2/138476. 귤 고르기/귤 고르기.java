import java.util.*;

class Solution {
    public int solution(int k, int[] tangerine) {
        int answer = 1;
        Map<Integer, Integer> map = new HashMap<>();
        for (int orange : tangerine) {
            map.put(orange, map.getOrDefault(orange, 0) + 1);
        }
        
        List<Integer> keySet = new ArrayList<>(map.keySet());
        keySet.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return map.get(o2).compareTo(map.get(o1));
            }
        });
        
        int sum = 0;
        for (int key : keySet) {
            int cnt = map.get(key);
            if (sum + cnt >= k) {
                break;
            } else {
                sum += cnt;
                answer++;
            }
        }
        
        return answer;
    }
}