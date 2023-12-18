import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        int[] answer;
        Queue<Integer> progressesQueue = new LinkedList<>();
        Queue<Integer> speedsQueue = new LinkedList<>();
        ArrayList<Integer> answerList = new ArrayList<>();

        for (int i = 0; i < progresses.length; i++) {
            progressesQueue.offer(progresses[i]);
            speedsQueue.offer(speeds[i]);
        }
        
        while (!progressesQueue.isEmpty()) {
            int count = 0;
            for (int i = 0; i < progressesQueue.size(); i++) {
                int progress = progressesQueue.poll();
                int speed = speedsQueue.poll();

                progressesQueue.add(progress + speed);
                speedsQueue.add(speed);
            }

            while (progressesQueue.peek() >= 100) {
                progressesQueue.poll();
                speedsQueue.poll();
                count++;
                
                if (progressesQueue.isEmpty()) {
                    break;
                }
            }

            if (count > 0) {
                answerList.add(count);
            }
        }

        answer = new int[answerList.size()];
        for (int i = 0; i < answerList.size(); i++) {
            answer[i] = answerList.get(i);
        }

        return answer;
    }
}