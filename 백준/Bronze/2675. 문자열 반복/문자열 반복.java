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
		StringTokenizer st;
		int testCase = Integer.parseInt(br.readLine());
		String result[] = new String[testCase];
		for(int i=0; i<testCase; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			String s = st.nextToken();
			result[i] = "";
			for(int j=0; j<s.length(); j++) {
				for(int k=0; k<r; k++) {
					result[i] = result[i].concat(Character.toString(s.charAt(j)));
				}
			}
		}
			
		for(int i=0; i<testCase; i++)
			bw.write(result[i] + "\n");
		
		bw.flush();
		bw.close();
		br.close();
	}
}