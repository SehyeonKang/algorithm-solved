class Solution {
    public int[] solution(int[] answers) {
        int[] answer;
        int[] solved = new int[3];
        int[] two = {2, 1, 2, 3, 2, 4, 2, 5};
        int[] three = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
        
        for(int i = 0 ; i < answers.length; i++) {
            if(answers[i] == i % 5 + 1) {
                solved[0]++;
            }
            
            if(answers[i] == two[i % 8]) {
                solved[1]++;
            }
            
            if(answers[i] == three[i % 10]) {
                solved[2]++;
            }
        }
        
        int max = Math.max(solved[0], solved[1]);
        max = Math.max(max, solved[2]);
        
        int count = 0;
        for(int n : solved) {
            if(max == n)
                count++;
        }
        answer = new int[count];
        count = 0;
        for(int i = 0; i < 3; i++) {
            if(solved[i] == max) {
                answer[count++] = i + 1;
            }
        }
        return answer;
    }
}