import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        int answer = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            
            if (i == 0 && y == 0) {
                continue;
            }
            
            if (stack.isEmpty()) {
                stack.push(y);
                continue;
            }

            while (!stack.isEmpty() && stack.peek() > y) {
                stack.pop();
                answer++;
            }

            if (y > 0 && (stack.isEmpty() || stack.peek() < y)) {
                stack.push(y);
            }
        }

        answer += stack.size();
        System.out.println(answer);
    }
}
