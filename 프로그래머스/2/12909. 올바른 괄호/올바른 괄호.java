class Solution {
    boolean solution(String s) {
        boolean answer = true;
        int stack = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(') {
                stack++;
            } else {
                if (stack == 0) {
                    answer = false;
                    break;
                }

                stack--;
            }
        }

        if (stack != 0)
            answer = false;

        return answer;
    }
}