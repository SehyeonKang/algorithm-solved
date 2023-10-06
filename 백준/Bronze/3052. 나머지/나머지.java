import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int arr[] = new int[42];
		int cnt = 0;
		
		for(int i = 0; i < 10; i++) {
			int n = Integer.parseInt(br.readLine());
			int test = n % 42;
			
			if(arr[test] != 1) {
				arr[test]++;
				cnt++;
			}
		}
		
		bw.write(cnt+"\n");

		bw.flush();
		bw.close();
		br.close();
	}
}