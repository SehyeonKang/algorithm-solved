import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        String s = br.readLine();
        String bomb = br.readLine();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            stack.push(s.charAt(i));

            while (stack.size() >= bomb.length()) {
                boolean check = true;
                for (int j = stack.size() - 1; j >= stack.size() - bomb.length(); j--) {
                    if (stack.elementAt(j) != bomb.charAt(j - stack.size() + bomb.length())) {
                        check = false;
                        break;
                    }
                }

                if (check) {
                    for (int j = 0; j < bomb.length(); j++) {
                        stack.pop();
                    }
                } else {
                    break;
                }
            }
        }

        if (stack.isEmpty()) {
            System.out.println("FRULA");
        } else {
            int stackSize = stack.size();
            for (int i = 0; i < stackSize; i++) {
                sb.append(stack.pop());
            }
            System.out.println(sb.reverse());
        }

    }

}
