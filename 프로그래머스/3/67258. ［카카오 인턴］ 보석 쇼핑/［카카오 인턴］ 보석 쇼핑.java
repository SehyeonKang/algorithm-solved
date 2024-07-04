import java.util.*;

class Solution {
    /*
        처음에 HashSet에 보석 이름들 저장
        HashMap에 저장해가며 투포인터로 탐색
        HashMap의 크기와 HashSet의 크기가 같은 경우 모든 보석을 가졌으므로
        정답과 최솟값 비교하고 lt 이동
    */
    public int[] solution(String[] gems) {
        int[] answer = new int[2];
        HashSet<String> set = new HashSet<>();
        HashMap<String, Integer> map = new HashMap<>();
        int lt = 0;
        int rt = 0;
        
        for (String gem : gems) {
            set.add(gem);
        }
        
        int min = 1000000;
        int setSize = set.size();
        int gemCount = 0;
        while (rt < gems.length) {
            if (!map.containsKey(gems[rt]) || map.get(gems[rt]) == 0) {
                gemCount++;
            }
            map.put(gems[rt], map.getOrDefault(gems[rt], 0) + 1);
            rt++;
            
            if (gemCount == setSize) {
                while (gemCount == setSize) {
                    map.put(gems[lt], map.get(gems[lt]) - 1);
                    if (map.get(gems[lt]) == 0) {
                        gemCount--;
                    }
                    lt++;
                }
                
                if (min > rt - lt) {
                    min = rt - lt;
                    answer[0] = lt;
                    answer[1] = rt;
                }
            }
        }
        
        return answer;
    }
}