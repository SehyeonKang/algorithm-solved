import java.util.*;

class Solution {
    public int solution(String s) {
        int answer = 0;
        boolean flag = false;
        
        for (int i = 0; i < s.length(); i++) {
            if (flag) {
                s = s.substring(1, s.length()) + s.charAt(0);
                flag = false;
                continue;
            }
            
            if (checkCorrect(s)) {
                answer++;
                flag = true;
            }
            
            s = s.substring(1, s.length()) + s.charAt(0);
        }
        
        return answer;
    }
    
    private boolean checkCorrect(String s) {
        ArrayDeque<Character> stack = new ArrayDeque<>();
        
        for (int i = 0; i < s.length(); i++) {
            if (stack.isEmpty()) {
                stack.push(s.charAt(i));
                continue;
            }
            
            char pre = stack.peek();
            char cur = s.charAt(i);
            
            if ((cur == ')' && pre == '(')
               || (cur == '}' && pre == '{')
               || (cur == ']' && pre == '[')) {
                stack.pop();
            } else {
                stack.push(cur);
            }
        }
        
        return stack.isEmpty();
    }
}