import java.util.*;

class Solution {
    /*
        1: 1
        2: 2
        3: 3
        4: 5
        5: 8
    */
    public long solution(int n) {
        long answer = 0;
        
        if (n == 1) {
            return 1;
        } else if (n == 2) {
            return 2;
        }
        
        int[] arr = new int[n];
        arr[0] = 1;
        arr[1] = 2;
        for (int i = 2; i < n; i++) {
            arr[i] = (arr[i - 1] + arr[i - 2]) % 1234567;
        }
        answer = arr[n - 1];
        return answer;
    }
}