import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;
        Arrays.sort(people);
        
        int lt = 0;
        int rt = people.length - 1;
        while (lt < rt) {
            int n1 = people[lt];
            int n2 = people[rt];
            
            if (n1 + n2 <= limit) {
                lt++;
                rt--;
                answer++;
            } else {
                rt--;
                answer++;
            }
        }
        
        if (lt == rt) {
            answer++;
        }
        
        return answer;
    }
}