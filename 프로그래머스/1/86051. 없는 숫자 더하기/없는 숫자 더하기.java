class Solution {
    public int solution(int[] numbers) {
        int answer = 0;
        boolean[] checkArr = new boolean[10];
        for(int n : numbers) {
            checkArr[n] = true;
        }
        for(int i = 0 ; i < 10; i++) {
            if(!checkArr[i]) {
                answer += i;
            }
        }
        return answer;
    }
}