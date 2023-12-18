import java.util.*;

class Solution {
    public int solution(int[] priorities, int location) {
        int answer = 1;
        Queue<Integer> processQueue = new LinkedList<>();
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Collections.reverseOrder());

        for (int priority : priorities) {
            processQueue.add(priority);
            priorityQueue.add(priority);
        }

        while (true) {
            if (priorityQueue.peek().equals(processQueue.peek())) {
                if (location == 0) {
                    break;
                } else {
                    processQueue.poll();
                    priorityQueue.poll();
                    answer++;
                }
            } else {
                processQueue.offer(processQueue.poll());
                priorityQueue.offer(priorityQueue.poll());
            }

            if (location > 0) {
                location--;
            } else {
                location = processQueue.size() - 1;
            } 
        }

        return answer;
    }
}