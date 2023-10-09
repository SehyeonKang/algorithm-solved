import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		
		int newA = a%10*100 + a/10%10*10 + a/100;
		int newB = b%10*100 + b/10%10*10 + b/100;

		if(newA > newB) {
			bw.write(newA + "");
		} else
			bw.write(newB + "");
		
		bw.flush();
		bw.close();
		br.close();
	}
}