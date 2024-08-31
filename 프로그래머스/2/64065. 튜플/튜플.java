import java.util.*;

class Solution {
    public int[] solution(String s) {
        int[] answer;
        s = s.substring(2, s.length() - 2);
        String[] arr = s.split("},\\{");
        answer = new int[arr.length];
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            list.add(new ArrayList<>());
        }
        
        for (int i = 0; i < arr.length; i++) {
            String[] sArr = arr[i].split(",");
            int idx = sArr.length - 1;
            for (String str : sArr) {
                list.get(idx).add(Integer.parseInt(str));
            }
        }
        
        for (int i = 0; i < arr.length; i++) {
            int num = list.get(i).get(0);
            answer[i] = num;
            for (int j = i + 1; j < arr.length; j++) {
                list.get(j).remove(Integer.valueOf(num));
            }
        }
        
        return answer;
    }
}