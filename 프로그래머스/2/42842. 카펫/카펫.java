import java.util.*;

class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        int lengthSum = (brown - 4) / 2;

        for (int i = yellow; i > 0; i--) {
            if (yellow % i == 0 && i + yellow / i == lengthSum) {
                answer[0] = i + 2;
                answer[1] = yellow / i + 2;
                break;
            }
        }

        return answer;
    }
}