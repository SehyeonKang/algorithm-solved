import java.util.*;

class Solution {
    public long solution(long n) {
        long answer = 0;
        double sqrt = Math.sqrt(n);
        int tmp = 0;
        tmp = (int) sqrt;
        if (tmp == sqrt) {
            answer = (long) (tmp + 1) * (tmp + 1);
        } else {
            answer = -1;
        }
        return answer;
    }
}