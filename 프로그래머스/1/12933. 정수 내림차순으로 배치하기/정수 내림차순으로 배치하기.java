import java.util.*;

class Solution {
    public long solution(long n) {
        long answer = 0;
        String s = String.valueOf(n);
        int[] arr = new int[s.length()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Character.getNumericValue(s.charAt(i));
        }
        Arrays.sort(arr);

        for (int i = 0; i < arr.length; i++) {
            answer += arr[i] * Math.pow(10, i);
        }
        return answer;
    }
}