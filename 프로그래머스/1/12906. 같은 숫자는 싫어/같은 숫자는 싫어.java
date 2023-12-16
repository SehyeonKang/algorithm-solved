import java.util.*;

public class Solution {
    public int[] solution(int[] arr) {
        int[] answer;
        int[] tmpArr = new int[arr.length];
        int tmp = arr[0];
        int count = 1;
        tmpArr[0] = arr[0];
        
        for (int i = 1; i < arr.length; i++) {
            if(tmp == arr[i])
                continue;
            
            tmp = arr[i];
            tmpArr[count++] = arr[i];
        }
        
        answer = new int[count];
        for (int i = 0; i < count; i++) {
            answer[i] = tmpArr[i];
        }

        return answer;
    }
}