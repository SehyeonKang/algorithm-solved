import java.util.*;

class Solution
{
    public int solution(String s)
    {
        int answer;
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (stack.isEmpty() || stack.peek() != c) {
                stack.push(c);
            } else {
                stack.pop();
            }
        }

        if (stack.isEmpty()) {
            answer = 1;
        } else {
            answer = 0;
        }

        return answer;
    }
}