import java.util.*;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        int answerCount = 0;
        
        for (int index = 0; index < commands.length; index++) {
            int i = commands[index][0];
            int j = commands[index][1];
            int k = commands[index][2];
            
            int[] tmp = new int[j - i + 1];
            int count = 0;
            for (int tmpIdx = i - 1; tmpIdx < j; tmpIdx++) {
                tmp[count++] = array[tmpIdx];
            }
            Arrays.sort(tmp);
            answer[answerCount++] = tmp[k - 1];
        }
        return answer;
    }
}