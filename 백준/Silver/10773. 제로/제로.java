import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	public static int[] stack;
	public static int size = 0;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int K = Integer.parseInt(br.readLine());
		stack = new int[K];
		
		for(int i = 0; i < K; i++) {
			int n = Integer.parseInt(br.readLine());
			
			if(n == 0) {
				pop();
			} else {
				push(n);
			}
		}
		
		int sum = 0;
		
		for(int i = 0; i < size; i++) {
			sum += stack[i];
		}
		
		sb.append(sum).append("\n");
		
		System.out.print(sb);
		
		br.close();
	}
	
	public static void push(int item) {
		stack[size] = item;
		size++;
	}
	
	public static int pop() {
		if(size == 0) {
			return -1;
		} else {
			int tmp = stack[size - 1];
			stack[size - 1] = 0;
			size--;
			return tmp;
		}
	}
	
	public static int top() {
		if(size == 0) {
			return -1;
		} else
			return stack[size - 1];
	}
}