import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        Stack<Character> stack = new Stack<>();
        char[] arr = br.readLine().toCharArray();
        String pattern = br.readLine();
        
        for (int i = 0; i < arr.length; i++) {
        	stack.push(arr[i]);
        	while (stack.size() >= pattern.length()) {
        		boolean flag = true;
        		for (int j = 0; j < pattern.length(); j++) {
        			if (stack.elementAt(stack.size() - pattern.length() + j) != pattern.charAt(j)) {
        				flag = false;
        				break;
        			}
        		}
        		// 문자열이 동일한 경우
        		if (flag) {
        			for (int j = 0; j < pattern.length(); j++) {
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
        	while (!stack.isEmpty()) {
        		sb.append(stack.pop());
        	}
        	sb.reverse();
        	System.out.println(sb);
        }
    }
}