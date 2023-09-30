import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(br.readLine());
		int result = 666;
		int count = 0;
		while(true) {
			String str = String.valueOf(result);
			if(str.contains("666")) 
				count++;
			
			if(count == n)
				break;
			
			result++;
		}
		
		bw.write(result + "");
		
		bw.flush();
		bw.close();
		br.close();
	}
}