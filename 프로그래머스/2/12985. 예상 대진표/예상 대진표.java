class Solution
{
    public int solution(int n, int a, int b)
    {
        int answer = 1;
        
        if (a > b) {
            int tmp = b;
            b = a;
            a = tmp;
        }

        while (true) {
            if (Math.abs(a - b) == 1 && a % 2 == 1) {
                break;
            }
            
            if (a % 2 == 0) {
                a /= 2;
            } else {
                a = (a + 1) / 2;
            }
            
            if (b % 2 == 0) {
                b /= 2;
            } else {
                b = (b + 1) / 2;
            }
            
            answer++;
        }

        return answer;
    }
}