class Solution {
    boolean solution(String s) {
        boolean answer = true;
        int pCount = 0;
        int yCount = 0;

        for (char c : s.toCharArray()) {
            char ch = Character.toLowerCase(c);
            if (ch == 'p') {
                pCount++;
            } else if (ch == 'y') {
                yCount++;
            }
        }
        
        if (pCount != yCount)
            answer = false;
        
        return answer;
    }
}