import java.util.*;

class Solution {
    public int solution(int[] A, int[] B) {
        int answer = 0;
        Arrays.sort(A);
        Arrays.sort(B);
        
        int lt = 0;
        int rt = 0;
        while (lt < A.length && rt < B.length) {
            if (A[lt] < B[rt]) {
                answer++;
                lt++;
                rt++;
            } else {
                rt++;
            }
        }
        
        return answer;
    }
}