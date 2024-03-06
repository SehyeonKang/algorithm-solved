class Solution {
    public int solution(int n, int k) {
        int answer = 0;
        StringBuilder sb = new StringBuilder();
        String convertedNumber = "";
        
        while (n > 0) {
            int mod = n % k;
            sb.append(mod);
            n /= k;
        }
        sb.reverse();
        convertedNumber = sb.toString();
        
        sb = new StringBuilder();
        for (int i = 0; i < convertedNumber.length(); i++) {
            if (convertedNumber.charAt(i) - '0' > 0) {
                sb.append(convertedNumber.charAt(i));
            } else {
                if (sb.length() > 0) {
                    long number = Long.parseLong(sb.toString());
                    if (check(number)) {
                        answer++;
                    }
                    sb = new StringBuilder();
                }
            }
        }
        if (sb.length() > 0) {
            long number = Long.parseLong(sb.toString());
            if (check(number)) {
                answer++;
            }
        }
        return answer;
    }
    
    private boolean check(long n) {
        if (n > 1) {
            for (long i = 2; i <= Math.sqrt(n); i++) {
                if (n % i == 0) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}