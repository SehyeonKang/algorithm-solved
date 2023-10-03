import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.io.IOException;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(bf.readLine());
		int arr[] = new int[testCase*2];
		
		for(int i=0; i<testCase; i++) {
			String num = bf.readLine();
			StringTokenizer st = new StringTokenizer(num);
			arr[i*2] = Integer.parseInt(st.nextToken());
			arr[i*2+1] = Integer.parseInt(st.nextToken());
		}
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for(int i=0; i<testCase; i++) {
			bw.write("Case #" + (i+1) + ": " + arr[i*2] + " + " + arr[i*2+1] + " = "+ (arr[i*2] + arr[i*2+1]) + "\n");
		}
		
		bw.flush();
		bw.close();
	}
}