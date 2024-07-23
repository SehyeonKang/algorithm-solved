import java.util.*;

class Solution {
    public int solution(int[] elements) {
        int answer = 0;
        HashSet<Integer> set = new HashSet<>();
        
        int[] arr = new int[elements.length * 2];
        for (int i = 0; i < arr.length; i++) {
            int eIdx = i % elements.length;
            arr[i] = elements[eIdx];
        }
        
        int[] sum = new int[arr.length];
        sum[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            sum[i] = sum[i - 1] + arr[i];
        }
        
        for (int i = 1; i <= elements.length; i++) {
            for (int j = i; j < elements.length + i; j++) {
                int partSum = sum[j] - sum[j - i];
                set.add(partSum);
            }
        }
        answer = set.size();
        return answer;
    }
}