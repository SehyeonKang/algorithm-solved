import java.util.*;

class Solution {
    public int[] solution(int[] arr, int divisor) {
        int[] answer;
        int[] tmp = new int[arr.length];
        int count = 0;
        for(int i = 0; i < arr.length; i++) {
           if(arr[i] % divisor == 0) {
               tmp[count++] = arr[i];
           } 
        }
        
        if(count == 0) {
            answer = new int[1];
            answer[0] = -1;
        } else {
            answer = new int[count];
            for(int i = 0; i < count; i++) {
                answer[i] = tmp[i];
            }
            Arrays.sort(answer);   
        }
        return answer;
    }
}