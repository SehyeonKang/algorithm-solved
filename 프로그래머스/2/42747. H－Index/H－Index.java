import java.util.*;

class Solution {
    public int solution(int[] citations) {
        int answer = 0;
        Integer[] citationsArr = new Integer[citations.length];

        for (int i = 0; i < citations.length; i++) {
            citationsArr[i] = citations[i];
        }
        Arrays.sort(citationsArr, Collections.reverseOrder());

        int count = 1;
        for (int citation : citationsArr) {
            if (count > citation) {
                answer = count - 1;
                break;
            }
            count++;
        }
        
        if (answer == 0) {
            answer = count - 1;    
        }

        return answer;
    }
}