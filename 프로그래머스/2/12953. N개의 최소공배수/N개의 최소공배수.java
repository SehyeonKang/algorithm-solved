class Solution {
    public int solution(int[] arr) {
        int answer = 1;
        while (true) {
            boolean flag = true;
            
            for (int num : arr) {
                if (answer % num != 0) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                break;
            }
            answer++;
        }
        
        return answer;
    }
}