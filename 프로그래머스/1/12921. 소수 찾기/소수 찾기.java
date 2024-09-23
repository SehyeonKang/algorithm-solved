import java.util.*;
import java.io.*;

class Solution {
    public int solution(int n) {
        int answer = 0;
        boolean[] arr = new boolean[n + 1];
        
        for (int i = 2; i <= n; i++) {
            if (!arr[i]) {
                answer++;
                for (int j = i; j <= n; j += i) {
                    arr[j] = true;
                }
            }
        }
        
        return answer;
    }
}