import java.util.*;

class Solution {
    
    boolean[] visited;
    ArrayList<String> results;
    
    public String[] solution(String[][] tickets) {
        String[] answer = {};
        visited = new boolean[tickets.length];
        results = new ArrayList<>();
        
        dfs("ICN", "ICN", tickets, 0);
        
        Collections.sort(results);
        answer = results.get(0).split(" ");
        
        return answer;
    }
    
    private void dfs(String start, String route, String[][] tickets, int cnt) {
        if (cnt == tickets.length) {
            results.add(route);
            return;
        }
        
        for (int i = 0; i < tickets.length; i++) {
            if (!visited[i] && start.equals(tickets[i][0])) {
                visited[i] = true;
                dfs(tickets[i][1], route + " " + tickets[i][1], tickets, cnt + 1);
                visited[i] = false;
            }
        }
    }
}