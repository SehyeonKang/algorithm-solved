import java.util.*;

class Solution {
    public int solution(int[] stones, int k) {
        int answer = 0;
        int lt = 0;
        int rt = -1;
        
        for (int stone : stones) {
            rt = Math.max(rt, stone);
        }
        
        while (lt <= rt) {
            int mid = (lt + rt) / 2;
            
            int sequence = 0;
            int maxSequence = 0;
            
            for (int stone : stones) {
                if (stone <= mid) {
                    sequence++;
                    maxSequence = Math.max(maxSequence, sequence);
                } else {
                    sequence = 0;
                }
            }
            
            if (maxSequence + 1 > k) {
                rt = mid - 1;
            } else {
                lt = mid + 1;
            }
        }
        
        return lt;
    }
}