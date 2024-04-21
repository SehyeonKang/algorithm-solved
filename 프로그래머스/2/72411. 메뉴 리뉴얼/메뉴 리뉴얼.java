import java.util.*;

class Solution {
    
    static TreeMap<String, Integer>[] ordersMap;
    static char[] pickOrders;
    
    public String[] solution(String[] orders, int[] course) {
        String[] answer;
        ArrayList<String> answerList = new ArrayList<>();
        ordersMap = new TreeMap[11];
        
        for (int i = 0; i < orders.length; i++) {
            StringBuilder sb = new StringBuilder();
            char[] orderArr = orders[i].toCharArray();
            Arrays.sort(orderArr);
            for (char orderItem : orderArr) {
                sb.append(orderItem);
            }
            orders[i] = sb.toString();
        }
        
        for (int courseCnt : course) {
            ordersMap[courseCnt] = new TreeMap<>();
            for (String order : orders) {
                if (order.length() >= courseCnt) {
                    pickOrders = new char[courseCnt];
                    recur(0, 0, order, courseCnt);
                }
            }
        }
        
        for (int courseCnt : course) {
            int max = 2;
            ArrayList<String> maxCourses = new ArrayList<>();
            TreeMap<String, Integer> map = ordersMap[courseCnt];
            for (String courseOrders : map.keySet()) {
                int cnt = map.get(courseOrders);
                
                if (max == cnt) {
                    maxCourses.add(courseOrders);
                } else if (max < cnt) {
                    maxCourses.clear();
                    maxCourses.add(courseOrders);
                    max = cnt;
                }
            }
            
            for (String maxCourse : maxCourses) {
                answerList.add(maxCourse);
            }
        }
        
        Collections.sort(answerList);
        answer = answerList.toArray(new String[answerList.size()]);
        
        return answer;
    }
    
    private void recur(int cnt, int start, String order, int courseCnt) {
        if (cnt == courseCnt) {
            TreeMap<String, Integer> map = ordersMap[courseCnt];
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < courseCnt; i++) {
                sb.append(pickOrders[i]);
            }
            String course = sb.toString();
            
            if (map.containsKey(course)) {
                int orderCnt = map.get(course);
                map.remove(course);
                map.put(course, orderCnt + 1);
            } else {
                map.put(course, 1);
            }
            return;
        }
        
        for (int i = start; i < order.length(); i++) {
            pickOrders[cnt] = order.charAt(i);
            recur(cnt + 1, i + 1, order, courseCnt);
        }
    }
}