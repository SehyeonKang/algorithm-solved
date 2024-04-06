import java.util.*;

class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        
        int dIdx = n - 1;
        int pIdx = n - 1;
        
        while (dIdx >= 0 || pIdx >= 0) {
            int maxDistance = 0;
            int dCnt = cap;
            int pCnt = cap;
            boolean dFlag = false;
            boolean pFlag = false;
            
            while (dIdx >= 0) {
                if (deliveries[dIdx] > 0 && !dFlag) {
                    dFlag = true;
                    maxDistance = dIdx + 1;
                }
                
                if (dCnt >= deliveries[dIdx]) {
                    dCnt -= deliveries[dIdx--];
                } else {
                    deliveries[dIdx] -= dCnt;
                    break;
                }
            }
            
            while (pIdx >= 0) {
                if (pickups[pIdx] > 0 && !pFlag) {
                    pFlag = true;
                    maxDistance = Math.max(maxDistance, pIdx + 1);
                }
                
                if (pCnt >= pickups[pIdx]) {
                    pCnt -= pickups[pIdx--];
                } else {
                    pickups[pIdx] -= pCnt;
                    break;
                }
            }
            
            answer += maxDistance * 2;
        }
        
        return answer;
    }
}