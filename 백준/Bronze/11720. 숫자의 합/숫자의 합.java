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
		String s = br.readLine();
		int sum = 0;
		
		for(int i=0; i<n; i++) 
			sum += Character.getNumericValue(s.charAt(i));

		bw.write(sum + "\n");
		
		bw.flush();
		bw.close();
	}
}