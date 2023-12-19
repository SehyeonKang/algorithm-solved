import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;
        Queue<Integer> truckQueue = new LinkedList<>();
        Queue<Integer> bridgeTruckWeightQueue = new LinkedList<>();
        Queue<Integer> bridgeTruckTimeQueue = new LinkedList<>();
        for (int truckWeight : truck_weights) {
            truckQueue.offer(truckWeight);
        }

        int currentLength = 0;
        int currentWeight = 0;
        while (!truckQueue.isEmpty()) {
            if (currentLength < bridge_length && currentWeight + truckQueue.peek() <= weight) {

                for (int i = 0; i < bridgeTruckTimeQueue.size(); i++) {
                    bridgeTruckTimeQueue.offer(bridgeTruckTimeQueue.poll() - 1);
                }

                int truckWeight = truckQueue.poll();
                bridgeTruckWeightQueue.offer(truckWeight);
                bridgeTruckTimeQueue.offer(bridge_length);
                currentLength++;
                currentWeight += truckWeight;
                
                if (bridgeTruckTimeQueue.peek() == 0) {
                    bridgeTruckTimeQueue.poll();
                    int bridgeTruckWeight = bridgeTruckWeightQueue.poll();
                    currentLength--;
                    currentWeight -= bridgeTruckWeight;
                }
                
                answer++;
            } else {
                while (bridgeTruckTimeQueue.peek() > 0) {
                    for (int i = 0; i < bridgeTruckTimeQueue.size(); i++) {
                        bridgeTruckTimeQueue.offer(bridgeTruckTimeQueue.poll() - 1);
                    }
                    answer++;
                }

                bridgeTruckTimeQueue.poll();
                int bridgeTruckWeight = bridgeTruckWeightQueue.poll();
                currentLength--;
                currentWeight -= bridgeTruckWeight;

                if (currentWeight + truckQueue.peek() <= weight) {
                    int truckWeight = truckQueue.poll();
                    bridgeTruckWeightQueue.offer(truckWeight);
                    bridgeTruckTimeQueue.offer(bridge_length);
                    currentLength++;
                    currentWeight += truckWeight;
                }
            }
        }
        answer += bridge_length;

        return answer;
    }
}