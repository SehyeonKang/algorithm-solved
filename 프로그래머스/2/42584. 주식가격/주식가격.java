import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        Queue<Integer> priceQueue = new LinkedList<>();

        for (int price : prices) {
            priceQueue.offer(price);
        }

        int index = 0;
        while (!priceQueue.isEmpty()) {
            int price = priceQueue.poll();
            int count = 0;

            for (int n : priceQueue) {
                count++;
                if (price > n) {
                    break;
                }
            }

            answer[index++] = count;
        }

        return answer;
    }
}