class Solution {
    public int solution(int num) {
        int answer = 0;
        int count = 0;
        long n = num;
        while (count < 500) {
            if (n == 1) {
                break;
            }
            
            if (n % 2 == 0) {
                n /= 2;
            } else {
                n = n * 3 + 1;
            }
            count++;
        }
        
        if (count == 500) {
            answer = -1;
        } else {
            answer = count;
        }
        return answer;
    }
}