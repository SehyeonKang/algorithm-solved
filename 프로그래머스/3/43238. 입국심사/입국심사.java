import java.util.*;

class Solution {
    
    /*
        1. 이분 탐색
        2. 시작 범위 mid: 1 ~ n * Math.max(times)
        3. sum(mid / times) <= n 이면 lt = mid + 1
        4. sum(mid / times) > n 이면 rt = mid - 1
    */
    public long solution(int n, int[] times) {
        long lt = 0;
        long rt = -1;
        for (int time : times) {
            rt = Math.max(rt, time);
        }
        rt *= n;
        
        while (lt < rt) {
            long mid = (lt + rt) / 2;
            long sum = 0;
            for (int time : times) {
                sum += mid / time;
            }
            
            if (sum < n) {
                lt = mid + 1;
            } else {
                rt = mid;
            }
        }
        
        return lt;
    }
}