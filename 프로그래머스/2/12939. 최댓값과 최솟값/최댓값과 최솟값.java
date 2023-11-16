import java.util.Arrays;

class Solution {
    public String solution(String s) {
        String answer = "";
        
        String[] sArr = s.split(" ");
        int[] arr = new int[sArr.length];

        for (int i = 0; i < sArr.length; i++) {
            arr[i] = Integer.parseInt(sArr[i]);
        }
        Arrays.sort(arr);

        answer =  arr[0] + " " + arr[arr.length - 1];
        
        return answer;
    }
}