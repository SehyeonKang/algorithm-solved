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
		String s = br.readLine();
		int n = Integer.parseInt(s);
		StringTokenizer st = new StringTokenizer(br.readLine());
		double arr[] = new double[n];
		double max = -1;
		double avg = 0;
		String result;
		
		for(int i = 0; i < n; i++) {
			arr[i] = Double.parseDouble(st.nextToken());
			if(max < arr[i])
				max = arr[i];
		}
		
		for(int i = 0; i < n; i++) {
			arr[i] = arr[i] / max * 100;
			avg += arr[i];
		}
		
		avg /= n;
		result = String.format("%.6f", avg);
		bw.write(result + "");

		bw.flush();
		bw.close();
		br.close();
	}
}