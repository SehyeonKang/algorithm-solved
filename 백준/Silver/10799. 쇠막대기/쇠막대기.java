import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        ArrayDeque<Character> stack = new ArrayDeque<>();
        String s = br.readLine();
        int answer = 0;

        boolean laserCheck = true;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.push(c);
                laserCheck = true;
            } else if (c == ')') {
                stack.pop();
                if (laserCheck) {
                    answer += stack.size();
                    laserCheck = false;
                } else {
                    answer++;
                }
            }
        }

        System.out.println(answer);
    }

}
